import com.jbosak.csa.bank.server.UserManager
import java.lang.reflect.Method

import java.util.Scanner

object Main {

    @JvmStatic
    fun main(args: Array<String>) {

        val client = BankClient()

        val scanner = Scanner(System.`in`)
        var input = scanner.next()
        while (input != "exit") {
            printParams(input)
            if (input == "login") client.login(scanner)
            if (input == "register") client.register(scanner)
            if (input == "accountBalance") client.accountBalance()
            if (input == "transfer") client.transfer(scanner)
            if (input == "logout") client.logout()
            if (input == "options") client.options()
            input = scanner.next()
        }
    }

    fun printParams(methodName: String){
        if(methodName == "transfer"){
            println("params: ")
            println( "String, int,")
        } else
        if(methodName != "accountBalance"){
            val method:List<Method?> = UserManager::class.java.methods
                    .filter { it.name == methodName}

            if (method.isNotEmpty() && method[0]?.parameters?.isNotEmpty()!!){
                println("params: ")

                method[0]!!.parameters
                        .map { it.type.simpleName }
                        .forEach { p -> print("$p, ") }
                println()
            }
        }

    }
}
