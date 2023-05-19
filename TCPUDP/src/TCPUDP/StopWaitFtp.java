package TCPUDP;


import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;

import java.util.logging.Logger;

public class StopWaitFtp {
	
	private static final Logger logger = Logger.getLogger("StopWaitFtp"); // global logger	

	/**
	 * Constructor to initialize the program 
	 * 
	 * @param timeout		The time-out interval for the retransmission timer, in milli-seconds
	 */
	public StopWaitFtp(int timeout) {
	
	}


	/**
	 * Send the specified file to the remote server
	 * 
	 * @param serverName	Name of the remote server
	 * @param serverPort	Port number of the remote server
	 * @param fileName		Name of the file to be trasferred to the rmeote server
	 * @throws IOException 
	 */
	public void send(String serverName, int serverPort, String fileName) throws IOException {
		DatagramSocket datagramSocket = new DatagramSocket();
		InetAddress inetAddress = InetAddress.getByName(serverName);
		tcplient tcp_client = new tcplient(serverName, serverPort,fileName,10L);
		Udp_client udp_client = new Udp_client(datagramSocket,inetAddress);
		udp_client.sendThenReceive(fileName,60123);
		
	}

} // end of class