package com.jbosak.csa.bank.server

import org.apache.xmlrpc.common.XmlRpcHttpRequestConfig
import org.apache.xmlrpc.server.AbstractReflectiveHandlerMapping.AuthenticationHandler

import org.apache.xmlrpc.server.PropertyHandlerMapping
import org.apache.xmlrpc.server.XmlRpcServerConfigImpl
import org.apache.xmlrpc.webserver.WebServer

class BankServer(port: Int) : WebServer(port) {

    private val phm = PropertyHandlerMapping()


    init {
        var config: XmlRpcHttpRequestConfig
        phm.authenticationHandler = AuthenticationHandler {
            config = it.config as XmlRpcHttpRequestConfig
            return@AuthenticationHandler isAuthenticated(config.basicUserName, config.basicPassword, it.methodName)
        }
        phm.addHandler("UserManager", UserManagerImpl::class.java)
        phm.isVoidMethodEnabled = true
        super.getXmlRpcServer().handlerMapping = phm
        xmlRpcServer.handlerMapping = phm

    }

    fun setConfig(extensions: Boolean, contentLengthOptional:Boolean){
        val xmlRpcServer = super.getXmlRpcServer()
        val config: XmlRpcServerConfigImpl = xmlRpcServer.config as XmlRpcServerConfigImpl
        config.isEnabledForExtensions = extensions
        config.isContentLengthOptional = contentLengthOptional
        xmlRpcServer.setConfig(config)
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



}