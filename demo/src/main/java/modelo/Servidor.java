package modelo;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import java.io.*;
import java.net.*;
import java.util.*;

public class Servidor {
    private static final int PUERTO = 5000;
    private static final int MAX_CLIENTES = 5;

    // lista de clientes
    private static final List<PrintWriter> clientes = new ArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor encendido...");

            while (true) {
                Socket socket = servidor.accept();
                System.err.println("Alguien se ha conectado");

                new Thread(() -> Clientes(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void Clientes(Socket socket) {
            try{ 
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

            synchronized (clientes){
                clientes.add(salida);
            }

            String mensaje;
        
        
            // Bucle infinito para escuchar lo que este cliente nos manda
            while ((mensaje = entrada.readLine()) != null) {
                System.out.println("Mensaje recibido");

                synchronized (clientes) {
                    for(PrintWriter c : clientes) {
                        c.print(mensaje);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Un cliente se ha desconectado");
        }
    }
}