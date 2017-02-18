/**
 * Created by hakon on 07.02.2017.
 */
import javax.xml.crypto.Data;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.stream.Stream;

public class client {
    private static int port = 6789;
    private static String ip = "localhost";
    private static Scanner sc = new Scanner(System.in);
    private static Socket clientSocket;
    private static BufferedReader inFromServer;

    public static void printSocket(Iterable<String> it) throws Exception{
        for(String line : it) {
            if(line.equals("END")) {return;}
            if(line.equals("CLOSE")) {
                clientSocket.close();
                System.exit(0);
            }
            System.out.println("Server response: " + line);
        }
    }

    public static void readlines() throws Exception{
        Stream<String> lines = inFromServer.lines();

        printSocket(lines::iterator);
    }

    public static void main(String[] args) throws Exception{
        /*System.out.println("Enter the server ip: ");
        ip = sc.nextLine();*/


        clientSocket = new Socket(ip, port);

        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());

        inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


        String responseString;
        String message;

        System.out.println("Connection established with: " + clientSocket.getRemoteSocketAddress());
        readlines();


        commandLoop:
        {
            do {
                System.out.println("enter your command: ");
                message = sc.nextLine();

                outToServer.writeBytes(message + "\n");
                outToServer.flush();
                readlines();
            /*responseString = inFromServer.readLine();
            System.out.println("the server responded with: " + responseString);*/

            } while (true);
        }
    }
}
