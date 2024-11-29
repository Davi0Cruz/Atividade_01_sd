import java.io.*;
import java.net.*;

class SimpleUDPClient {
	public static void main(String args[]) {
		DatagramSocket clientSocket = null;
		try {
			clientSocket = new DatagramSocket(10000);
			byte[] sendArray = args[0].getBytes();
			InetAddress IpServidor = InetAddress.getByName(args[1]);
			int port = 6789;

			DatagramPacket sendPacket = new DatagramPacket(sendArray, sendArray.length, IpServidor, port);
			clientSocket.send(sendPacket);

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (clientSocket != null) clientSocket.close();
		}
	}
}