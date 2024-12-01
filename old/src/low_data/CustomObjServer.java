package low_data;

import java.io.*;
import java.net.*;

class CustomObjServer {
	public static void main(String argv[]) {
		ServerSocket listenSocket;
		try {
			listenSocket = new ServerSocket(5100);
			while (true) {
				Socket connectionSocket = listenSocket.accept();
				ObjectInputStream inFromClient = new ObjectInputStream(connectionSocket.getInputStream());
				inFromClient.readObject();
				System.out.println("Received");
                connectionSocket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}