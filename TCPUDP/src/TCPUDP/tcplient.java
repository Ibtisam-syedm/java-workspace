package TCPUDP;

import java.net.*;
import java.io.*;
 
public class tcplient
{
    // initialize socket and input output streams
    private Socket socket            = null;
    private DataInputStream  input   = null;
    private DataOutputStream out     = null;
 
    // constructor to put ip address and port
	public tcplient(String address, int port,String fileName,Long length) throws IOException
    {
        // establish a connection
        try
        {
            socket = new Socket(address, port);
            System.out.println("Connected");
 
            // takes input from terminal
            input  = new DataInputStream(System.in);
 
            // sends output to the socket
            out    = new DataOutputStream(socket.getOutputStream());
        }
        catch(UnknownHostException u)
        {
            System.out.println(u);
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
        out.writeInt(port);
        out.writeUTF(fileName);
        out.writeLong(length);
        out.flush();
//        byte[] udpPort = null;
//        input.read(udpPort);
//        for(int i=0; i< udpPort.length ; i++) {
//            System.out.print(udpPort[i] +" ");
//         }
//        BufferedReader bis = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        char[] buffer = new char[1024];
//		bis.read(buffer);
//		System.out.println(buffer);
        // string to read message from input
//        String line = "";
// 
//        // keep reading until "Over" is input
//        while (!line.equals("Over"))
//        {
//            try
//            {
//                line = input.readLine();
//                out.writeUTF(line);
//            }
//            catch(IOException i)
//            {
//                System.out.println(i);
//            }
//        }
 
        // close the connection
//        try
//        {
//            input.close();
//            out.close();
//            socket.close();
//        }
//        catch(IOException i)
//        {
//            System.out.println(i);
//        }
    }
 
    public static void main(String args[])
    {
//    	tcplient client = new tcplient("127.0.0.1", 5000);
    }
}