import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by hakon on 07.02.2017.
 */

public class ServerThread implements Runnable {
    private static Socket clientSocket;

    public ServerThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public void run() {
        try {
            Thread.currentThread().sleep(1000);

            System.out.println("Connection received from: " + clientSocket.getRemoteSocketAddress());
            BufferedReader inFromClient = null;
            DataOutputStream outToClient = null;
            inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            outToClient = new DataOutputStream(clientSocket.getOutputStream());

            String command = null;

            do {
                command = inFromClient.readLine().toUpperCase();

                String response = null;

                if (command.equals("FULL")) {
                    response = new SimpleDateFormat("MMMM yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
                } else if (command.equals("DATE")) {
                    response = new SimpleDateFormat("MMMM yyyy").format(Calendar.getInstance().getTime());
                } else if (command.equals("TIME")) {
                    response = new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime());
                } else if (command.equals("CLOSE")) {
                    response = "CLOSE";
                }
                System.out.println("Command: " + command + " |Client: " + clientSocket.getRemoteSocketAddress() + " |response: " + response);

                outToClient.writeBytes(response + "\n");


            } while (!command.equals("CLOSE"));
            Thread.currentThread().sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}