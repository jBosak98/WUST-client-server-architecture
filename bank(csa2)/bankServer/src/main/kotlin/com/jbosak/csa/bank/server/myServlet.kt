package com.jbosak.csa.bank.server

import org.apache.xmlrpc.server.AbstractReflectiveHandlerMapping
import org.apache.xmlrpc.webserver.XmlRpcServlet

class MyServlet : XmlRpcServlet() {
    override fun getAuthenticationHandler(): AbstractReflectiveHandlerMapping.AuthenticationHandler {
        return super.getAuthenticationHandler()
    }
}