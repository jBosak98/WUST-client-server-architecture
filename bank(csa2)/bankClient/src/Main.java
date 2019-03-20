import org.apache.xmlrpc.XmlRpcException;

import java.net.MalformedURLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try{

            BankClient client = new BankClient();

            BankCallback callback = new BankCallback(client);
            client.options(callback);

            Scanner scanner = new Scanner(System.in);
            String input = scanner.next();
            while (!input.equals("exit")){

                if (input.equals("login"))  client.login(scanner,callback);
                if (input.equals("register"))   client.register(scanner, callback);
                if (input.equals("accountBalance")) client.accountBalance(callback);
                if (input.equals("transfer"))   client.transfer(scanner, callback);
                if (input.equals("logout"))     client.logout(callback);
                if (input.equals("another")) client.anotherMethod(scanner);
                if (input.equals("options")) client.options(callback);
//                client.options(callback);
                input = scanner.next();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (XmlRpcException e) {
            e.printStackTrace();
        }
    }
}
