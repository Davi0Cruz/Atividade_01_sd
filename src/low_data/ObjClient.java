package low_data;

import java.io.*;
import java.net.*;
import low_data.Item;

class ObjClient {
	public static void main(String argv[]) {

		Item[] compras = {
			new Item("Leite", 2, "litros"),
			new Item("Pão", 1, "unidade"),
			new Item("Ovos", 12, "unidades"),
			new Item("Maçãs", 6, "unidades"),
			new Item("Peito de Frango", 1, "kg"),
		};
		
		System.out.println("Low Case Obj: " + compras.toString());
		Socket clientSocket;
		try {
			clientSocket = new Socket("localhost", 5100);
			ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
			outToServer.writeObject(compras);
			System.out.println("Sended");
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}