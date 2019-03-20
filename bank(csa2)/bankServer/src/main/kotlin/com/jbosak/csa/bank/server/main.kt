package com.jbosak.csa.bank.server



import org.apache.xmlrpc.common.XmlRpcHttpRequestConfig
import org.apache.xmlrpc.server.AbstractReflectiveHandlerMapping.AuthenticationHandler
import org.apache.xmlrpc.server.PropertyHandlerMapping
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl
import org.apache.xmlrpc.webserver.WebServer

const val SERVER_PORT = 5059

fun main(){
    print("xd")

    val webServer = WebServer(5059)
    val xmlRpcServer = webServer.xmlRpcServer

    val phm = PropertyHandlerMapping()

    val sessionManager = SessionManager()
    phm.authenticationHandler = AuthenticationHandler {
        val config = it.config as XmlRpcHttpRequestConfig
        return@AuthenticationHandler isAuthenticated(config.basicUserName, config.basicPassword, it.methodName)
    }

    phm.addHandler("Bank", sessionManager::class.java)
    xmlRpcServer.handlerMapping = phm
    val config: XmlRpcServerConfigImpl = xmlRpcServer.config as XmlRpcServerConfigImpl
    config.isEnabledForExceptions = true
    config.isContentLengthOptional = true
    config.isEnabledForExtensions = true
    println(config.isEnabledForExceptions)
    xmlRpcServer.setConfig(config)
    println(phm.javaClass)
    println(phm.getMethodSignature(phm.listMethods[1]))

    phm.listMethods.forEach { println(it) }

    webServer.start()

    System.out.println("Server started successfully");
    System.out.println("Listening on port: $SERVER_PORT")

}

fun isAuthenticated(basicUserName: String?, basicPassword: String?, methodName: String): Boolean {
    println(methodName)
    println("$basicUserName $basicPassword")
    if (methods.containsKey(methodName) && !methods[methodName]!!){
        return true
    }
    users.forEach {
        if (it.password == basicPassword && it.username == basicUserName){
            return  true
        }
    }
    return false
}
