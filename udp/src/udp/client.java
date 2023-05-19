package udp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class client {

    // initialize socket and input output streams
    private Socket socket            = null;
    private DataInputStream  input   = null;
    private DataOutputStream out     = null;
    private int tcp_port = 1234;
    private int udp_port = 2234;  
	private DatagramSocket datagramSocket;
	
	private InetAddress inetAddress;
	
	private byte[] buffer;
	
	public client (DatagramSocket datagramSocket, InetAddress inetAddress) throws UnknownHostException, IOException { 
		this.datagramSocket = datagramSocket;
		this.inetAddress = inetAddress;
//		System.out.println("Address of client :"+inetAddress.getHostAddress()); 
		socket = new Socket(inetAddress.getHostAddress(), tcp_port);
        System.out.println("TCP Connection started from client for sending,\n1.filename\n2.file length\n3.Initial sequence number\n4.UDP Port");
	}
	@SuppressWarnings("deprecation")
	public void TCP_connection_initiates() throws IOException {
        // takes input from terminal
        input  = new DataInputStream(System.in);

        // sends output to the socket
        out    = new DataOutputStream(socket.getOutputStream());
        // string to read message from input
        String line = "";
 
        // keep reading until "Over" is input
        while (!line.equals("Over"))
        {
            try
            {
                line = input.readLine();
                out.writeUTF(line);
            }
            catch(IOException i)
            {
                System.out.println(i);
            }
        }
        // close the connection
        try
        {
            input.close();
            out.close();
            socket.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
	}
	public void sendThenReceive() {
		
		
		try (Scanner scanner = new Scanner(System.in)) {
			while (true) {
			
				try {
				
				String messageToSend = scanner.nextLine();
				
				buffer = messageToSend.getBytes();
				DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress,1234);
				datagramSocket.send(datagramPacket);
				datagramSocket.receive(datagramPacket); 
//				System.out.println("The server says you said:" + messageFromServer);
				String messageFronServer = new String (datagramPacket.getData(),0, datagramPacket.getLength());
				System.out.println("The server says you said : "+messageFronServer);
				} catch (IOException e) {
					e.printStackTrace();
					break;
				}
			}
		}
	}
	public static void main(String[] args) throws IOException {
		DatagramSocket datagramSocket = new DatagramSocket();
		InetAddress inetAddress = InetAddress.getByName("localhost");
		client client = new client(datagramSocket,inetAddress);
		client.TCP_connection_initiates();
		System.out.println("Send datagram packets to a server.");
//		client.sendThenReceive();
	}
}

