//@file:Suppress("UNCHECKED_CAST")

import org.apache.xmlrpc.client.XmlRpcClient

import org.apache.xmlrpc.client.XmlRpcClientConfigImpl
import java.lang.reflect.Proxy

import java.net.URL
class ClientProxy(private val url: URL) {


    var config: XmlRpcClientConfigImpl = XmlRpcClientConfigImpl()

     val xmlRpcClient = XmlRpcClient()

    init {
        this.config.serverURL = url
        this.config.isEnabledForExceptions = true
        this.config.isEnabledForExtensions = true
        this.config.isContentLengthOptional = true
        this.xmlRpcClient.setConfig(config)
    }

     fun <T> wrap(handlerType: Class<T>, handlerName: String): T {
         xmlRpcClient.setConfig(config)

        return Proxy.newProxyInstance(handlerType.classLoader, arrayOf<Class<*>>(handlerType)) {
            proxy, method, args ->
            xmlRpcClient.execute(handlerName + "." + method.name, args)
        } as T
    }
}
