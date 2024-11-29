package low_data;

import java.io.*;
import java.net.*;

class ObjServer {
	public static void main(String argv[]) {
		ServerSocket listenSocket;
        System.out.println("Low Case Obj Server");
		try {
			listenSocket = new ServerSocket(5100);
			while (true) {
				Socket connectionSocket = listenSocket.accept();
				ObjectInputStream inFromClient = new ObjectInputStream(connectionSocket.getInputStream());
				inFromClient.readObject();
				System.out.println("Received");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}