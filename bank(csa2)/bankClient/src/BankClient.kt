import com.jbosak.csa.bank.server.UserManager
import com.jbosak.csa.bank.server.model.User
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl
import java.lang.reflect.UndeclaredThrowableException
import java.net.MalformedURLException
import java.net.URL
import java.util.*


class BankClient {

    private var clientProxy: ClientProxy? = null

    private val userManager: UserManager

    private var config: XmlRpcClientConfigImpl

    init {

        try {
            clientProxy = ClientProxy(URL("$HOST_URL"))
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        }
        var client = clientProxy!!.xmlRpcClient

        config = XmlRpcClientConfigImpl()
        config.serverURL = URL("$HOST_URL/$SERVER_NAME")
        config.isEnabledForExtensions = true
        config.isContentLengthOptional = true
        config.isEnabledForExceptions = true

        clientProxy?.config = config
        userManager = clientProxy?.wrap(UserManager::class.java, SERVER_NAME)!!

        client.setConfig(config)

    }

    fun options() {
        UserManager::class.java.methods.forEach { print( it.name + ", ") }
        println()
    }

    fun login(scanner: Scanner) {
        val user = userManager.login(scanner.next(), scanner.next())
        if (user == null) println("Something went wrong")
        else {
            println("Logged in successfully")
            config.basicUserName = user.username
            config.basicPassword = user.password
        }
    }

    fun register(scanner: Scanner) {
        val user = User(scanner.next(),scanner.next(), Integer.valueOf(scanner.next()))
        val success = userManager.register(user)
        if (success) println("Account created")
        else println("User with username: ${user.username} already exists")
    }


    fun accountBalance() {
        var username  = config.basicUserName
        if (username == null) username = ""
        try {
            println("Your amount of money: " + userManager.accountBalance(username))

        }catch (e: UndeclaredThrowableException){
            println("You're not logged in!")
        }
    }

    fun transfer(scanner: Scanner) {

        val config:XmlRpcClientConfigImpl? = clientProxy?.xmlRpcClient?.config as XmlRpcClientConfigImpl
        if (config == null || config.basicUserName.isNullOrEmpty()){
            println("You're not logged in!")
            return
        }else{
            val success = userManager.transfer(config.basicUserName, scanner.next(), scanner.nextInt())
            if (success) println("transfer successfully finished")
            else println("Something went wrong")

        }

    }

    fun logout() {
        val config = clientProxy?.xmlRpcClient?.clientConfig as XmlRpcClientConfigImpl
        if (config.basicUserName.isNullOrEmpty()){
            println("You're not even logged in!")
            return
        }
        userManager.logout()
        println("logged out")
        config.basicUserName = null
        config.basicPassword = null

    }

    companion object {
        private const val SERVER_NAME = "UserManager"
        private const val HOST_URL = "http://localhost:5059"
    }
}
