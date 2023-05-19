package udp;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class server {

    //initialize socket and input stream
    private Socket          socket   = null;
    private ServerSocket    server   = null;
    private DataInputStream in       =  null;
    private int tcp_port = 1234;  

	private DatagramSocket datagramSocket;

	private byte[] buffer = new byte[256];

	public server (DatagramSocket datagramSocket) throws IOException {

		this.datagramSocket = datagramSocket;
		server = new ServerSocket(tcp_port);
        System.out.println("TCP Server started");
	}
	public void TCP_connection_initiates_server() {
        try
        {
            System.out.println("Waiting for a client ...");
 
            socket = server.accept();
            System.out.println("Client accepted");
 
            // takes input from the client socket
            in = new DataInputStream(
                new BufferedInputStream(socket.getInputStream()));
 
            String line = "";
 
            // reads message from client until "Over" is sent
            while (!line.equals("Over"))
            {
                try
                {
                    line = in.readUTF();
                    System.out.println(line);
 
                }
                catch(IOException i)
                {
                    System.out.println(i);
                }
            }
            System.out.println("Closing connection");
 
            // close connection
            socket.close();
            in.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
	}
	public void receiveThenSend() {

		while (true) {
		
			try {
				DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length); 
				datagramSocket.receive(datagramPacket);
				InetAddress inetAddress = datagramPacket.getAddress(); 
				int port = datagramPacket.getPort();
				String messageFromClient = new String(datagramPacket.getData(), 0, datagramPacket.getLength());

				System.out.println("Message from client:" + messageFromClient);
				datagramPacket = new DatagramPacket(buffer, buffer.length, inetAddress, port);
				datagramSocket.send(datagramPacket);
		
			} catch (IOException e) {
		
				e.printStackTrace();
			}	
		}
	}
	public static void main(String[] args) throws IOException {
		DatagramSocket datagramSocket = new DatagramSocket(1234);
		server server = new server(datagramSocket);
		server.TCP_connection_initiates_server();
//		server.receiveThenSend();
	}
}
