import org.apache.xmlrpc.XmlRpcClient;
import org.apache.xmlrpc.XmlRpcException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Scanner;
import java.util.Vector;

class BankClient {
    private static final String SERVER_NAME = "Bank";
    private static final String HOST_URL = "http://localhost:5059";

    private XmlRpcClient client;
    private Vector<String> auth;

    BankClient() throws MalformedURLException {
        this.client = new XmlRpcClient(HOST_URL);
        this.auth = new Vector<String>();
    }

    void options(BankCallback callback) {
                client.executeAsync(SERVER_NAME + ".options", auth, callback);
    }

    void asyncLogin(Scanner scanner, BankCallback callback) {
        Vector<String> user  = new Vector<String>();
        user.add(scanner.next());
        user.add(scanner.next());
        auth = new Vector<String>();
        auth.add(user.get(0));
        auth.add(user.get(1));
        client.executeAsync(SERVER_NAME + ".login", user, callback);
    }

    public void asyncRegister(Scanner scanner, BankCallback callback) {
        Vector<Object> user  = new Vector<Object>();
        user.add(scanner.next());
        user.add(scanner.next());
        user.add(Integer.valueOf(scanner.next()));
        client.executeAsync(SERVER_NAME + ".register", user, callback);
    }

    public void handleLoggedIn(Boolean o) {
        System.out.println(o);
        if (o){
            System.out.println("Logged in successfully");
        }else {
            auth = new Vector<String>();
            System.out.println("Wrong login or password");
        }
    }

    public void asyncAccountBalance(BankCallback callback) {
        if (auth.isEmpty()){
            System.out.println("you're not logged in!");
            return;
        }
        client.executeAsync(SERVER_NAME + ".accountBalance", auth, callback);
    }

    public void asyncTransfer(Scanner scanner, BankCallback callback) {
        if (auth.isEmpty()){
            System.out.println("you're not logged in!");
            return;
        }
        Vector<Object> transfer  = new Vector<Object>();
        transfer.add(auth.get(0));
        transfer.add(auth.get(0));
        transfer.add(scanner.next());
        transfer.add(Integer.valueOf(scanner.next()));
        client.executeAsync(SERVER_NAME + ".transfer", transfer, callback);
    }

    public void asyncLogout(BankCallback callback) {
        client.executeAsync(SERVER_NAME + ".logout", auth, callback);
        auth = new Vector<String>();
    }

    public void anotherMethod(Scanner scanner) throws XmlRpcException, IOException {
        Vector<User> emptyVector = new Vector<User>();
        User user = new User("xd","aa");
        emptyVector.add(user);
        User response = (User) client.execute(SERVER_NAME + "." + scanner.next(), emptyVector );

        System.out.println(response.toString());
    }
}
