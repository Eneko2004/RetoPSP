package controlador;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class VentanaRetoController {

    @FXML 
    private TextField txtIp;
    @FXML
    private TextField txtPuerto;
    @FXML
    private TextArea areaChat;
    @FXML 
    private TextField txtMensaje;
    @FXML 
    private Button btnConectar;


    private Socket socket;
    private PrintWriter salida;
    private boolean conectado = false;


    @FXML 
    public void gestionarConexion() {
        if(!conectado) {
            try {
                String ip = txtIp.getText();
                int puerto = Integer.parseInt(txtPuerto.getText());

                socket = new Socket(ip, puerto);
                salida  = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                conectado = true;
                areaChat.appendText("Conectado al servidor con exito!");
                btnConectar.setText("Desconectar");

                new Thread(() -> {
                    try{
                        String mensajeServidor;
                        while (conectado && (mensajeServidor = entrada.readLine()) != null) {
                            
                        }
                    }
                });

            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }
}
