
import com.jbosak.csa.bank.server.User;
import org.apache.xmlrpc.XmlRpcRequest;
import org.apache.xmlrpc.client.AsyncCallback;

public class BankCallback implements AsyncCallback {
    private BankClient client;

    public BankCallback(BankClient client) {
        this.client = client;
    }




    @Override
    public void handleResult(XmlRpcRequest xmlRpcRequest, Object o) {
        System.out.println("AsyncXmlRpcRequest: " + xmlRpcRequest.getMethodName());
        System.out.println("AsyncXmlRpcRequest: " + xmlRpcRequest.getConfig());
        System.out.println("AsyncXmlRpcRequest: " + xmlRpcRequest.getParameterCount());
        System.out.println("AsyncHandleResult: " + o);

        if (xmlRpcRequest.getMethodName().contains("register")) System.out.println("Account successfully created: " + o);
        else if (xmlRpcRequest.getMethodName().contains("transfer")) System.out.println("transfer successfully finished: " + o);
        else if (xmlRpcRequest.getMethodName().contains("accountBalance")) System.out.println("Amount of money: " + o);
        else if (xmlRpcRequest.getMethodName().contains("login")) client.handleLoggedIn((User) o);
        else if (xmlRpcRequest.getMethodName().contains("options")) System.out.println(o);
        else if(xmlRpcRequest.getMethodName().contains("logout")) client.handleLogout((Boolean) o);
        else {
            System.out.println("Not handled method: " + o);

//            try {
//                client.options(this);
//            } catch (XmlRpcException e) {
//                e.printStackTrace();
//            }

        }
    }

    @Override
    public void handleError(XmlRpcRequest xmlRpcRequest, Throwable throwable) {

    }
}
