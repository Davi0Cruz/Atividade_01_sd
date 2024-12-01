package low_data;

import java.io.*;
import java.net.*;
import low_data.CustomItem;

class CustomObjClient {
	public static void main(String argv[]) {

		CustomItem[] compras = {
			new CustomItem("Leite", 2, "litros"),
			new CustomItem("Pão", 1, "unidade"),
			new CustomItem("Ovos", 12, "unidades"),
			new CustomItem("Maçãs", 6, "unidades"),
			new CustomItem("Peito de Frango", 1, "kg"),
		};
		
		Socket clientSocket;
		try {
			clientSocket = new Socket("localhost", 5100);
            System.out.println("Low data Custom Obj");
			ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
			outToServer.writeObject(compras);
            System.out.println("Sended");
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}