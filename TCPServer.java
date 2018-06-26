import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhengwb on 2018/4/8.
 */
public class TCPServer {
    public static void main(String argv[]) {
        try {
            String clientSentence;
            String capitalizedSentence;
            ServerSocket welcomeSocket = new ServerSocket(6780);
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            while (true) {
                Socket connectionSocket = welcomeSocket.accept();
                BufferedReader inFromClient =
                        new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                clientSentence = inFromClient.readLine();
                System.out.println(format.format(new Date()) + " -> received " + clientSentence);
                capitalizedSentence = clientSentence != null && clientSentence.length() > 0 ? clientSentence.toUpperCase() + '\n' : "";
                outToClient.writeBytes(capitalizedSentence);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
