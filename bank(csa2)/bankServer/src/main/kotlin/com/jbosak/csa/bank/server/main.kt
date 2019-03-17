package com.jbosak.csa.bank.server



import org.apache.xmlrpc.server.PropertyHandlerMapping
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl
import org.apache.xmlrpc.webserver.WebServer




val SERVER_PORT = 5059
fun main(){
    print("xd")



    val myServlet = MyServlet()




    val webServer = WebServer(5059)
    val xmlRpcServer = webServer.xmlRpcServer

    val phm = PropertyHandlerMapping()

    phm.addHandler("Bank", SessionManager()::class.java)
    xmlRpcServer.handlerMapping = phm
    val config: XmlRpcServerConfigImpl = xmlRpcServer.config as XmlRpcServerConfigImpl
    config.isEnabledForExceptions = true
    config.isContentLengthOptional = true
    config.isEnabledForExtensions = true
    println(config.isEnabledForExceptions)
    xmlRpcServer.setConfig(config)

    webServer.start()

    System.out.println("Server started successfully");
    System.out.println("Listening on port: $SERVER_PORT")

//    val x : Class<BankServer> = BankServer::class.java
//    println(x.classes)
//    x.declaredMethods.forEach {
//        println(it.genericReturnType)
//        println(it.returnType)
//        it.parameters.forEach {
//            println(" ${it.type}   ${it.declaringExecutable}")
//        }
//        println("")
//        println()
//    }
}