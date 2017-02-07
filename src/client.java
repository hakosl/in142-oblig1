/**
 * Created by hakon on 07.02.2017.
 */
import javax.xml.crypto.Data;
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class client {
    private static int port = 6789;
    private static String ip = "localhost";
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception{
        System.out.println("Enter the server ip: ");
        ip = sc.nextLine();


        Socket clientSocket = new Socket(ip, port);

        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


        String responseString;
        String message;

        System.out.println("Connection established with: " + clientSocket.getRemoteSocketAddress());

        do {
            System.out.println("enter your command: ");
            message = sc.nextLine();

            outToServer.writeBytes(message + "\n");
            outToServer.flush();

            responseString = inFromServer.readLine();
            System.out.println("the server responded with: " + responseString);

        } while (!responseString.equals("CLOSE"));
        clientSocket.close();
    }
}
