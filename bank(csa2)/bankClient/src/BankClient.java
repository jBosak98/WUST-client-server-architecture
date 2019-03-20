import com.jbosak.csa.bank.server.Session;
import com.jbosak.csa.bank.server.User;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfig;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.Vector;

class BankClient {
    private static final String SERVER_NAME = "Bank";
    private static final String HOST_URL = "http://localhost:5059";

    private XmlRpcClient client;
    private Session userSession;

    BankClient() throws MalformedURLException {
        this.client = new XmlRpcClient();


        XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();
        config.setEnabledForExceptions(true);
        config.setEnabledForExtensions(true);

        client.setTypeFactory(new SessionFactory(client));
        client.setConfig(config);
        config.setServerURL(new URL(HOST_URL));
    }

    void options(BankCallback callback) throws XmlRpcException {
        Vector<Object> params  = new Vector<Object>();
//        if (userSession != null){
//            params.add(userSession.getUser().getUsername());
//            params.add(userSession.getUser().getPassword());
//
//        }
                client.executeAsync(SERVER_NAME + ".options", params, callback);
    }

    void login(Scanner scanner, BankCallback callback) throws XmlRpcException {
        Vector<String> user  = new Vector<String>();
        user.add(scanner.next());
        user.add(scanner.next());

        client.executeAsync(SERVER_NAME + ".login", user, callback);
    }

    public void register(Scanner scanner, BankCallback callback) throws XmlRpcException {
        System.out.println("register");
        Vector<Object> user  = new Vector<Object>();
        user.add(scanner.next());
        user.add(scanner.next());
        user.add(Integer.valueOf(scanner.next()));
        client.executeAsync(SERVER_NAME + ".register", user, callback);
    }

    public void handleLoggedIn(User user) {
        if (user != null){
            XmlRpcClientConfigImpl config = (XmlRpcClientConfigImpl) client.getConfig();
            config.setBasicPassword(user.getPassword());
            config.setBasicUserName(user.getUsername());
            client.setConfig(config);
            System.out.println("Logged in successfully");
        }else{
            userSession = null;
            System.out.println("Wrong login or password");
        }

    }

    public void accountBalance(BankCallback callback) throws XmlRpcException {
        Vector<Object> params  = new Vector<Object>();
        params.add(((XmlRpcClientConfigImpl)client.getConfig()).getBasicUserName());
        client.executeAsync(SERVER_NAME + ".accountBalance", params, callback);
        System.out.println("AccountBalance");
    }

    public void transfer(Scanner scanner, BankCallback callback) throws XmlRpcException {
        Vector<Object> transfer  = new Vector<Object>();
        transfer.add(scanner.next());
        transfer.add(Integer.valueOf(scanner.next()));
        client.executeAsync(SERVER_NAME + ".transfer", transfer, callback);
    }

    public void logout(BankCallback callback) throws XmlRpcException {
        Vector<Object> params  = new Vector<Object>();
        client.executeAsync(SERVER_NAME + ".logout", params, callback);

    }

    public void anotherMethod(Scanner scanner) {
        Vector<User> emptyVector = new Vector<User>();
        User user = new User("xd","aa");
        System.out.println(user);
//        emptyVector.add(user);
        User response = null;
        try {
//            response = (User)
            System.out.println(client.execute(SERVER_NAME + "." + scanner.next(), emptyVector));
        } catch (XmlRpcException e) {
            System.out.println(e);
            e.printStackTrace();
        }

//        assert response != null;
//        System.out.println(response);
    }

    public void handleLogout(Boolean success) {
        if (success){
            XmlRpcClientConfigImpl config = (XmlRpcClientConfigImpl) client.getConfig();
            config.setBasicPassword(null);
            config.setBasicUserName(null);
            client.setConfig(config);
            System.out.println("Logged out successfully");

        }
    }
}
