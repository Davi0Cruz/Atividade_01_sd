package low_data;

import java.util.List;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

import com.google.protobuf.CodedOutputStream;

import low_data.CaseLowData.Compras;
import low_data.CaseLowData.Item;

class ProtoClient {
	public static void main(String argv[]) {
		Socket clientSocket;
        List<Item> items = new ArrayList<Item>();
        items.add(Item.newBuilder().setNome("Leite").setQuantidade(2).setUnidade("litros").build());
        items.add(Item.newBuilder().setNome("Pão").setQuantidade(1).setUnidade("unidade").build());
        items.add(Item.newBuilder().setNome("Ovos").setQuantidade(12).setUnidade("unidades").build());
        items.add(Item.newBuilder().setNome("Maçãs").setQuantidade(6).setUnidade("unidades").build());
        items.add(Item.newBuilder().setNome("Peito de Frango").setQuantidade(1).setUnidade("kg").build());
	    Compras compras = Compras.newBuilder().addAllItem(items).build();
		
		try {
            System.out.println("Low Case Proto Client");
			clientSocket = new Socket("localhost", 5100);
			
			CodedOutputStream out = CodedOutputStream.newInstance(clientSocket.getOutputStream());
			out.writeInt32NoTag(compras.getSerializedSize());
			Compras.writeTo(out);
			out.flush();
            System.out.println("Sended");
			clientSocket.close();
			
			//Checar o uso de writeDelimitedTo()
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}