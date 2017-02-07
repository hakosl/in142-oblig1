/**
 * Created by hakon on 07.02.2017.
 */
import java.io.*;
import java.net.*;
import java.util.Arrays;

public class server {
    private static int port = 6789;
    public static void main(String[] args) throws Exception {

        System.out.println(Arrays.toString(args));

        ServerSocket socket = new ServerSocket(port);

        while(true) {
            Socket connectionSocket = socket.accept();

            Thread thread = new Thread(new ServerThread(connectionSocket));
            thread.start();
        }
    }
}


