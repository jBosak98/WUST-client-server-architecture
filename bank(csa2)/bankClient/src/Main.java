import org.apache.xmlrpc.XmlRpcException;

import java.io.IOException;
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
                if (input.equals("login"))  client.asyncLogin(scanner,callback);
                if (input.equals("register"))   client.asyncRegister(scanner, callback);
                if (input.equals("accountBalance")) client.asyncAccountBalance(callback);
                if (input.equals("transfer"))   client.asyncTransfer(scanner, callback);
                if (input.equals("logout"))     client.asyncLogout(callback);
                if (input.equals("another")) client.anotherMethod(scanner);
                client.options(callback);
                input = scanner.next();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (XmlRpcException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
