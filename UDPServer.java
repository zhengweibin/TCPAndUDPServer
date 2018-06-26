import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhengwb on 2018/4/8.
 */
public class UDPServer {
    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(9876);
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String sentence = new String(receivePacket.getData(),0,receivePacket.getLength());
                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();
                String capitalizedSentence = sentence.toUpperCase();
                sendData = capitalizedSentence.getBytes();
                DatagramPacket sendPacket =
                        new DatagramPacket(sendData, sendData.length, IPAddress, port);
                System.out.println(format.format(new Date()) + "  " + IPAddress.getHostAddress() + ":" + port  + " -> received " + sentence);
                serverSocket.send(sendPacket);
            }

        } catch (Exception ex) {
            System.out.println("error : " + ex.getMessage());
            ex.printStackTrace();

        }finally {

        }
    }
}
