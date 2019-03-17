import org.apache.xmlrpc.AsyncCallback;

import java.net.URL;

public class BankCallback implements AsyncCallback {
    private BankClient client;

    public BankCallback(BankClient client) {
        this.client = client;
    }

    @Override
    public void handleResult(Object o, URL url, String s) {
        if (s.contains("register")) System.out.println("Account successfully created: " + o);
        else if (s.contains("transfer")) System.out.println("transfer successfully finished: " + o);
        else if (s.contains("accountBalance")) System.out.println("Amount of money: " + o);
        else if (s.contains("login")) client.handleLoggedIn((Boolean)o);
        else if (s.contains("options")) System.out.println(o);
        else {
            System.out.println("Not handled method: " + o);
            client.options(this);
        }
    }

    @Override
    public void handleError(Exception e, URL url, String s) {

    }
}
