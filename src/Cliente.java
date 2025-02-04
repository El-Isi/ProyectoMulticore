import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {

    public static void main(String[] args) {

        //Host del servidor
        final String HOST = "127.0.0.1"; // Aqui la ip de mi server

        //Puerto del servidor
        final int PUERTO = 5000;
        DataInputStream in;
        DataOutputStream out;

        //Llave para encriptar
        final String secretKey = "hide";

        try {

            System.out.println("Introduzca una palabra:");
            Scanner scan = new Scanner(System.in);
            String palabra = scan.nextLine();

            //Creo el socket para conectarme con el cliente
            Socket sc = new Socket(HOST, PUERTO);

            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());

            String palabra_cifrada = AES.encrypt(palabra, secretKey);

            //Envio mensajes al cliente
            out.writeUTF(palabra);
            out.writeUTF(palabra_cifrada);
            out.writeUTF(secretKey);

            //Recibo si el servidor pudo decifrar y lo impromo
            String mensaje = in.readUTF();
            System.out.println("El servidor retorno su clave: " + mensaje);

            sc.close();

        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
