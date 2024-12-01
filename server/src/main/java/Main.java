
import java.io.*;
import java.net.*;

import com.google.protobuf.CodedInputStream;
import data.LowData.PCompras;
import data.BigData.PAgenda;
import data.Compras;
import data.Agenda;
import data.CAgenda;

public class Main {
    public static void main(String[] args) {
        
        ////////////////////////////////
        // Serialization Server Low Data
        ////////////////////////////////
        ServerSocket listenSocket;
        try {
            System.out.println("Serialization Low Data Server");
            listenSocket = new ServerSocket(5100);
            Socket connectionSocket = listenSocket.accept();
            ObjectInputStream inFromClient = new ObjectInputStream(connectionSocket.getInputStream());
            inFromClient.readObject();
            System.out.println("Received");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

        ///////////////////////////////////////
        // Custom Serialization Server Low Data
        ///////////////////////////////////////
        try {
            System.out.println("Custom Serialization Low Data Server");
            listenSocket = new ServerSocket(5101);
            Socket connectionSocket = listenSocket.accept();
            ObjectInputStream inFromClient = new ObjectInputStream(connectionSocket.getInputStream());
            inFromClient.readObject();
            System.out.println("Received");
            connectionSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

        ///////////////////////
        // Json Server Low Data
        ///////////////////////
        try {
            System.out.println("Json Low Data Server");
            listenSocket = new ServerSocket(5102);
            Socket connectionSocket = listenSocket.accept();
            BufferedReader inFromClient = new BufferedReader(
                    new InputStreamReader(connectionSocket.getInputStream()));
            
            inFromClient.readLine();
            System.out.println("Received");
            connectionSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //////////////////////
        // XML Server Low Data
        //////////////////////
        try {
            System.out.println("XML Low Data Server");
            listenSocket = new ServerSocket(5103);
            Socket connectionSocket = listenSocket.accept();
            BufferedReader inFromClient = new BufferedReader(
                    new InputStreamReader(connectionSocket.getInputStream()));
            inFromClient.readLine();
            System.out.println("Received");
            connectionSocket.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        ////////////////////////////
        // Protobuf Server Low Data
        ////////////////////////////
        try {
            System.out.println("Protocol Buffers Low Data Server");
            listenSocket = new ServerSocket(5104);
            
            Socket connectionSocket = listenSocket.accept();
            CodedInputStream in = CodedInputStream.newInstance(connectionSocket.getInputStream());
            //int size = in.readInt32();
            PCompras compras = PCompras.parseFrom(in);
            System.out.println("Received");
        } catch (IOException e) {
            e.printStackTrace();
        }

        ////////////////////////////////

        ////////////////////////////////
        // Serialization Server Big Data
        ////////////////////////////////
            try {
                System.out.println("Serialization Big Data Server");
                listenSocket = new ServerSocket(5200);
                Socket connectionSocket = listenSocket.accept();
                ObjectInputStream inFromClient = new ObjectInputStream(connectionSocket.getInputStream());
                Agenda agenda = (Agenda) inFromClient.readObject();
                System.out.println(agenda.itens[0].foto);
                System.out.println("Received");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            ///////////////////////////////////////
            // Custom Serialization Server Big Data
            ///////////////////////////////////////
            try {
                System.out.println("Custom Serialization Big Data Server");
                listenSocket = new ServerSocket(5201);
                Socket connectionSocket = listenSocket.accept();
                ObjectInputStream inFromClient = new ObjectInputStream(connectionSocket.getInputStream());
                CAgenda cagenda = (CAgenda) inFromClient.readObject();
                System.out.println("Received");
                connectionSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            ///////////////////////
            // Json Server Big Data
            ///////////////////////
            try {
                System.out.println("Json Big Data Server");
                listenSocket = new ServerSocket(5202);
                Socket connectionSocket = listenSocket.accept();
                BufferedReader inFromClient = new BufferedReader(
                new InputStreamReader(connectionSocket.getInputStream()));
                
                inFromClient.readLine();
                System.out.println("Received");
                connectionSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //////////////////////
            // XML Server Big Data
            //////////////////////
            try {
                System.out.println("XML Big Data Server");
                listenSocket = new ServerSocket(5203);
                Socket connectionSocket = listenSocket.accept();
                BufferedReader inFromClient = new BufferedReader(
                new InputStreamReader(connectionSocket.getInputStream()));
                inFromClient.readLine();
                System.out.println("Received");
                connectionSocket.close();
                
            } catch (IOException e) {
                e.printStackTrace();
            }

            ////////////////////////////
            // Protobuf Server Big Data
            ////////////////////////////
            try {
                System.out.println("Protocol Buffers Big Data Server");
                listenSocket = new ServerSocket(5204);
                
                Socket connectionSocket = listenSocket.accept();
                CodedInputStream in = CodedInputStream.newInstance(connectionSocket.getInputStream());
                //int size = in.readInt32();
                PAgenda agenda = (PAgenda) PAgenda.parseFrom(in);
                System.out.println("Received");
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
