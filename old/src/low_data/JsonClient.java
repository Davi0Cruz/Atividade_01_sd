package low_data;

import java.io.*;
import java.net.*;
import java.util.Scanner;

class JsonClient {
	public static void main(String argv[]) {
		String json;
		BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
		Socket clientSocket;
		try {
            System.out.println("Low Case Json Client");
            File file = new File("./src/low_data/low_data.json");
			json = new Scanner(file).useDelimiter("\\Z").next().replaceAll("\n", "");
            clientSocket = new Socket("localhost", 5100);
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			outToServer.writeBytes(json + '\n');
			System.out.println("Sended");
            clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}