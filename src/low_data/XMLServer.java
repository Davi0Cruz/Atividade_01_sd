package low_data;

import java.io.*;
import java.net.*;

class XMLServer {
	public static void main(String argv[]) {

		ServerSocket listenSocket;
		try {
			listenSocket = new ServerSocket(6789);
			while (true) {
				Socket connectionSocket = listenSocket.accept();
				BufferedReader inFromClient = new BufferedReader(
						new InputStreamReader(connectionSocket.getInputStream()));
				
                inFromClient.readLine();
				System.out.println("Received");
                connectionSocket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}