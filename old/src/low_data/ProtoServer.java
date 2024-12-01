package low_data;

import java.io.*;
import java.net.*;

import com.google.protobuf.CodedInputStream;

import low_data.LowData.Compras;

class SimpleProtobufTCPServer {
	public static void main(String argv[]) {
		ServerSocket listenSocket;
		try {
			listenSocket = new ServerSocket(6789);
			while (true) {
				Socket connectionSocket = listenSocket.accept();
				CodedInputStream in = CodedInputStream.newInstance(connectionSocket.getInputStream());
				int size = in.readInt32();
				Compras compras = Compras.parseFrom(in);
                System.out.println("Received");
				// Checar o uso de parseDelimitedFrom()
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}