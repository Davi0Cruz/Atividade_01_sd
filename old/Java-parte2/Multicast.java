import java.net.*;
import java.io.*;

public class Multicast {
	public static void main(String args[]) {
		MulticastSocket s = null;
		String msg = "Paulo....";
		try {
			//http://www.iana.org/assignments/multicast-addresses/multicast-addresses.xhtml
			//224.0.0.0 - 239.255.255.255	Organization-Local Scope	
			InetAddress group = InetAddress.getByName("228.0.0.8");
			s = new MulticastSocket(6789);
			s.joinGroup(group);
            //s.setinterface("eth0");
			byte[] m = msg.getBytes();
			DatagramPacket messageOut = new DatagramPacket(m, m.length, group, 6789);
			s.send(messageOut);

			byte[] buffer = new byte[1000];
			for (int i = 0; i < 10; i++) {
				DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);
				s.receive(messageIn);
				System.out.println("Received:" + new String(messageIn.getData(), 0, messageIn.getLength()));
			}
			s.leaveGroup(group);
		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		} finally {
			if (s != null)
				s.close();
		}
	}
}
