import org.apache.ws.commons.util.NamespaceContextImpl
import org.apache.xmlrpc.common.TypeFactoryImpl
import org.apache.xmlrpc.common.XmlRpcController
import org.apache.xmlrpc.common.XmlRpcStreamConfig
import org.apache.xmlrpc.parser.SerializableParser
import org.apache.xmlrpc.parser.TypeParser


class SessionFactory(pController: XmlRpcController?) : TypeFactoryImpl(pController) {

    override fun getParser(pConfig: XmlRpcStreamConfig,
                           pContext: NamespaceContextImpl,
                           pURI: String, pLocalName: String): TypeParser = when (pLocalName){
        "serializable" -> SerializableParser() as TypeParser
        else -> super.getParser(pConfig, pContext, pURI, pLocalName)
    }
}