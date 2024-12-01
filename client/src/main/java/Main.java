import java.util.List;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.protobuf.CodedOutputStream;

import data.Item;
import data.Compras;
import data.CItem;
import data.CCompras;
import data.LowData.PItem;
import data.LowData.PCompras;

import data.Contato;
import data.Agenda;
import data.CContato;
import data.CAgenda;
import data.BigData.PContato;
import data.BigData.PAgenda;

public class Main {
    public static void main(String[] args) {

        ////////////////////////////////
        // Serialization Client Low Data
        ////////////////////////////////
        Item[] itens = {
            new Item("Leite", 2, "litros"),
            new Item("Pão", 1, "unidade"),
            new Item("Ovos", 12, "unidades"),
            new Item("Maçãs", 6, "unidades"),
            new Item("Peito de Frango", 1, "kg"),
        };
        Compras compras = new Compras(itens);
        
        Socket clientSocket;
        try {
            System.out.println("Serialization Low Data Client");
            clientSocket = new Socket("localhost", 5100);
            ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
            outToServer.writeObject(compras);
            System.out.println("Sended");
            clientSocket.close();
            Thread.sleep(1000); // Sleep for 1 second
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        ///////////////////////////////////////
        // Custom Serialization Client Low Data
        ///////////////////////////////////////
        CItem[] citens = {
            new CItem("Leite", 2, "litros"),
            new CItem("Pão", 1, "unidade"),
            new CItem("Ovos", 12, "unidades"),
            new CItem("Maçãs", 6, "unidades"),
            new CItem("Peito de Frango", 1, "kg"),
        };

        CCompras ccompras = new CCompras(citens);
        
        try {
            System.out.println("Custom Serialization Low Data Client");
            clientSocket = new Socket("localhost", 5101);
            System.out.println("Low data Custom Obj");
            ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
            outToServer.writeObject(ccompras);
            System.out.println("Sended");
            clientSocket.close();
            Thread.sleep(1000); // Sleep for 1 second
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        ///////////////////////
        // Json Client Low Data
        ///////////////////////
        String json;
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        
        try {
            System.out.println("Json Low Data Client");
            File file = new File("/home/davi/Documents/UFC/SISTEMAS DISTRIBUIDOS/trabalhos/atividade_01/client/src/main/java/data/low_data.json");
            json = new Scanner(file).useDelimiter("\\Z").next().replaceAll("\n", "");
            clientSocket = new Socket("localhost", 5102);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            outToServer.writeBytes(json + '\n');
            System.out.println("Sended");
            clientSocket.close();
            Thread.sleep(1000); // Sleep for 1 second
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        //////////////////////
        // XML Client Low Data
        //////////////////////
        String xml;
        inFromUser = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("XML Low Data Client");
            File file = new File("/home/davi/Documents/UFC/SISTEMAS DISTRIBUIDOS/trabalhos/atividade_01/client/src/main/java/data/low_data.xml");
            xml = new Scanner(file).useDelimiter("\\Z").next().replaceAll("\n", "");
            clientSocket = new Socket("localhost", 5103);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            outToServer.writeBytes(xml + '\n');
            System.out.println("Sended");
            clientSocket.close();
            Thread.sleep(1000); // Sleep for 1 second
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        ///////////////////////////
        // Protobuf Client Low Data
        ///////////////////////////

        List<PItem> pitems = new ArrayList<PItem>();
        pitems.add(PItem.newBuilder().setNome("Leite").setQuantidade(2).setUnidade("litros").build());
        pitems.add(PItem.newBuilder().setNome("Pão").setQuantidade(1).setUnidade("unidade").build());
        pitems.add(PItem.newBuilder().setNome("Ovos").setQuantidade(12).setUnidade("unidades").build());
        pitems.add(PItem.newBuilder().setNome("Maçãs").setQuantidade(6).setUnidade("unidades").build());
        pitems.add(PItem.newBuilder().setNome("Peito de Frango").setQuantidade(1).setUnidade("kg").build());
        PCompras pcompras = PCompras.newBuilder().addAllListaCompras(pitems).build();
        
        try {
            System.out.println("Protocol Buffers Low Data Client");
            clientSocket = new Socket("localhost", 5104);
            
            CodedOutputStream out = CodedOutputStream.newInstance(clientSocket.getOutputStream());
            pcompras.writeTo(out);
            out.flush();
            System.out.println("Sended");
            clientSocket.close();
            Thread.sleep(3000); // Sleep for 1 second
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


        ////////////////////////////////

        // Read string from file data/phot.txt
        String photoData = "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";
        
        ////////////////////////////////
        // Serialization Client Big Data
        ////////////////////////////////

        Contato[] contatos = {
            new Contato("Alice Johnson", "555-1234", "alice.johnson@example.com", photoData),
            new Contato("Bob Smith", "555-5678", "bob.smith@example.com", photoData),
            new Contato("Carol White", "555-8765", "carol.white@example.com", photoData),
            new Contato("David Brown", "555-4321", "david.brown@example.com", photoData),
            new Contato("Eve Black", "555-6789", "eve.black@example.com", photoData),
            new Contato("Frank Green", "555-9876", "frank.green@example.com", photoData),
            new Contato("Grace Blue", "555-3456", "grace.blue@example.com", photoData),
            new Contato("Hank Red", "555-6543", "hank.red@example.com", photoData),
            new Contato("Ivy Yellow", "555-7890", "ivy.yellow@example.com", photoData),
            new Contato("Jack Purple", "555-0987", "jack.purple@example.com", photoData),
            new Contato("Karen Orange", "555-2345", "karen.orange@example.com", photoData),
            new Contato("Leo Pink", "555-5432", "leo.pink@example.com", photoData),
            new Contato("Mia Gray", "555-8901", "mia.gray@example.com", photoData),
            new Contato("Nina Brown", "555-1098", "nina.brown@example.com", photoData),
            new Contato("Oscar White", "555-3456", "oscar.white@example.com", photoData),
            new Contato("Paul Black", "555-6543", "paul.black@example.com", photoData),
            new Contato("Quincy Green", "555-7890", "quincy.green@example.com", photoData),
            new Contato("Rachel Blue", "555-0987", "rachel.blue@example.com", photoData),
            new Contato("Steve Red", "555-2345", "steve.red@example.com", photoData),
            new Contato("Tina Yellow", "555-5432", "tina.yellow@example.com", photoData)
        };


        
        Agenda agenda = new Agenda(contatos);
        
        try {
            System.out.println("Serialization Big Data Client");
            clientSocket = new Socket("localhost", 5200);
            ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
            outToServer.writeObject(agenda);
            System.out.println("Sended");
            clientSocket.close();
            Thread.sleep(3000); // Sleep for 1 second
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        ///////////////////////////////////////
        // Custom Serialization Client Big Data
        ///////////////////////////////////////

        CContato[] ccontatos = {
            new CContato("Alice Johnson", "555-1234", "alice.johnson@example.com", photoData),
            new CContato("Bob Smith", "555-5678", "bob.smith@example.com", photoData),
            new CContato("Carol White", "555-8765", "carol.white@example.com", photoData),
            new CContato("David Brown", "555-4321", "david.brown@example.com", photoData),
            new CContato("Eve Black", "555-6789", "eve.black@example.com", photoData),
            new CContato("Frank Green", "555-9876", "frank.green@example.com", photoData),
            new CContato("Grace Blue", "555-3456", "grace.blue@example.com", photoData),
            new CContato("Hank Red", "555-6543", "hank.red@example.com", photoData),
            new CContato("Ivy Yellow", "555-7890", "ivy.yellow@example.com", photoData),
            new CContato("Jack Purple", "555-0987", "jack.purple@example.com", photoData),
            new CContato("Karen Orange", "555-2345", "karen.orange@example.com", photoData),
            new CContato("Leo Pink", "555-5432", "leo.pink@example.com", photoData),
            new CContato("Mia Gray", "555-8901", "mia.gray@example.com", photoData),
            new CContato("Nina Brown", "555-1098", "nina.brown@example.com", photoData),
            new CContato("Oscar White", "555-3456", "oscar.white@example.com", photoData),
            new CContato("Paul Black", "555-6543", "paul.black@example.com", photoData),
            new CContato("Quincy Green", "555-7890", "quincy.green@example.com", photoData),
            new CContato("Rachel Blue", "555-0987", "rachel.blue@example.com", photoData),
            new CContato("Steve Red", "555-2345", "steve.red@example.com", photoData),
            new CContato("Tina Yellow", "555-5432", "tina.yellow@example.com", photoData)
        };

        CAgenda cagenda = new CAgenda(ccontatos);
        
        try {
            System.out.println("Custom Serialization Big Data Client");
            clientSocket = new Socket("localhost", 5201);
            ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
            outToServer.writeObject(cagenda);
            System.out.println("Sended");
            clientSocket.close();
            Thread.sleep(7000); // Sleep for 1 second
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        ///////////////////////
        // Json Client Big Data
        ///////////////////////
        String bigJson;
        try {
            System.out.println("Json Big Data Client");
            File file = new File("/home/davi/Documents/UFC/SISTEMAS DISTRIBUIDOS/trabalhos/atividade_01/client/src/main/java/data/big_data.json");
            bigJson = new Scanner(file).useDelimiter("\\Z").next().replaceAll("\n", "");
            clientSocket = new Socket("localhost", 5202);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            outToServer.writeBytes(bigJson + '\n');
            System.out.println("Sended");
            clientSocket.close();
            Thread.sleep(1000); // Sleep for 1 second
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        //////////////////////
        // XML Client Big Data
        //////////////////////
        String bigXml;
        try {
            System.out.println("XML Big Data Client");
            File file = new File("/home/davi/Documents/UFC/SISTEMAS DISTRIBUIDOS/trabalhos/atividade_01/client/src/main/java/data/big_data.xml");
            bigXml = new Scanner(file).useDelimiter("\\Z").next().replaceAll("\n", "");
            clientSocket = new Socket("localhost", 5203);
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            outToServer.writeBytes(bigXml + '\n');
            System.out.println("Sended");
            clientSocket.close();
            Thread.sleep(1000); // Sleep for 1 second
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        ///////////////////////////
        // Protobuf Client Big Data
        ///////////////////////////

        List<PContato> pcontatos = new ArrayList<PContato>();
        pcontatos.add(PContato.newBuilder().setNome("Alice Johnson").setTelefone("555-1234").setEmail("alice.johnson@example.com").setFoto(photoData).build());
        pcontatos.add(PContato.newBuilder().setNome("Bob Smith").setTelefone("555-5678").setEmail("bob.smith@example.com").setFoto(photoData).build());
        pcontatos.add(PContato.newBuilder().setNome("Carol White").setTelefone("555-8765").setEmail("carol.white@example.com").setFoto(photoData).build());
        pcontatos.add(PContato.newBuilder().setNome("David Brown").setTelefone("555-4321").setEmail("david.brown@example.com").setFoto(photoData).build());
        pcontatos.add(PContato.newBuilder().setNome("Eve Black").setTelefone("555-6789").setEmail("eve.black@example.com").setFoto(photoData).build());
        pcontatos.add(PContato.newBuilder().setNome("Frank Green").setTelefone("555-9876").setEmail("frank.green@example.com").setFoto(photoData).build());
        pcontatos.add(PContato.newBuilder().setNome("Grace Blue").setTelefone("555-3456").setEmail("grace.blue@example.com").setFoto(photoData).build());
        pcontatos.add(PContato.newBuilder().setNome("Hank Red").setTelefone("555-6543").setEmail("hank.red@example.com").setFoto(photoData).build());
        pcontatos.add(PContato.newBuilder().setNome("Ivy Yellow").setTelefone("555-7890").setEmail("ivy.yellow@example.com").setFoto(photoData).build());
        pcontatos.add(PContato.newBuilder().setNome("Jack Purple").setTelefone("555-0987").setEmail("jack.purple@example.com").setFoto(photoData).build());
        pcontatos.add(PContato.newBuilder().setNome("Karen Orange").setTelefone("555-2345").setEmail("karen.orange@example.com").setFoto(photoData).build());
        pcontatos.add(PContato.newBuilder().setNome("Leo Pink").setTelefone("555-5432").setEmail("leo.pink@example.com").setFoto(photoData).build());
        pcontatos.add(PContato.newBuilder().setNome("Mia Gray").setTelefone("555-8901").setEmail("mia.gray@example.com").setFoto(photoData).build());
        pcontatos.add(PContato.newBuilder().setNome("Nina Brown").setTelefone("555-1098").setEmail("nina.brown@example.com").setFoto(photoData).build());
        pcontatos.add(PContato.newBuilder().setNome("Oscar White").setTelefone("555-3456").setEmail("oscar.white@example.com").setFoto(photoData).build());
        pcontatos.add(PContato.newBuilder().setNome("Paul Black").setTelefone("555-6543").setEmail("paul.black@example.com").setFoto(photoData).build());
        pcontatos.add(PContato.newBuilder().setNome("Quincy Green").setTelefone("555-7890").setEmail("quincy.green@example.com").setFoto(photoData).build());
        pcontatos.add(PContato.newBuilder().setNome("Rachel Blue").setTelefone("555-0987").setEmail("rachel.blue@example.com").setFoto(photoData).build());
        pcontatos.add(PContato.newBuilder().setNome("Steve Red").setTelefone("555-2345").setEmail("steve.red@example.com").setFoto(photoData).build());
        pcontatos.add(PContato.newBuilder().setNome("Tina Yellow").setTelefone("555-5432").setEmail("tina.yellow@example.com").setFoto(photoData).build());

        PAgenda pagenda = PAgenda.newBuilder().addAllContatos(pcontatos).build();
        
        try {
            System.out.println("Protocol Buffers Big Data Client");
            clientSocket = new Socket("localhost", 5204);
            
            CodedOutputStream out = CodedOutputStream.newInstance(clientSocket.getOutputStream());
            pagenda.writeTo(out);
            out.flush();
            System.out.println("Sended");
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
