package TCPUDP;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class Udp_client {

	private DatagramSocket datagramSocket;
	
	private InetAddress inetAddress;
	
	private byte[] buffer;
	
	public Udp_client (DatagramSocket datagramSocket, InetAddress inetAddress) { 
		this.datagramSocket = datagramSocket;
		this.inetAddress = inetAddress;
	}
	public void send() {
		
	}
	public void sendThenReceive(String filename,int port) throws IOException {
//		File filee = new File(".");
//		for(String fileNames : filee.list()) System.out.println(fileNames);
//		System.out.println(filename);
		File file = new File(filename);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String st;
			FtpSegment ftpsegment = new FtpSegment();
			byte[] payload = new byte[1000];
			
			while ((st = br.readLine()) != null) {
			    // Print the string
			    System.out.println(st);
			    payload = st.getBytes();
			    FtpSegment seg1 = new FtpSegment(1, payload);
			    DatagramPacket pkt = FtpSegment.makePacket(seg1, InetAddress.getLoopbackAddress(), port);
			    datagramSocket.send(pkt);
			    datagramSocket.receive(pkt); 
			    String messageFronServer = new String (pkt.getData(),0, pkt.getLength());
				System.out.println("The server says : "+messageFronServer);
			}
		}
	}

}
