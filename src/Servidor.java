import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ForkJoinPool;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    public static void main(String[] args) {

        ServerSocket servidor = null;
        Socket sc = null;
        DataInputStream in;
        DataInputStream in_cifrada;
        DataInputStream clave;
        DataOutputStream out;
        ForkJoinPool fuerzaBruta = new ForkJoinPool(4);

        //puerto de nuestro servidor
        final int PUERTO = 5000;

        try {
            //Creamos el socket del servidor
            servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor iniciado");

            //Siempre estara escuchando peticiones
            while (true) {

                //Espero a que un cliente se conecte
                sc = servidor.accept();

                in = new DataInputStream(sc.getInputStream());
                in_cifrada = new DataInputStream(sc.getInputStream());
                clave = new DataInputStream(sc.getInputStream());
                out = new DataOutputStream(sc.getOutputStream());

                //Leo el mensaje que me envia
                String mensaje = in.readUTF();
                String mensaje_cifrado = in_cifrada.readUTF();
                String clav = clave.readUTF();

                //Creamos archivos
                new EscribirArchivo(mensaje, mensaje_cifrado);

                System.out.println(mensaje);

                FuerzaBruta algoritmo_decifrado = new FuerzaBruta(mensaje_cifrado, mensaje, clav,0, 358800);
                fuerzaBruta.invoke(algoritmo_decifrado);

                //Retorno cuando mi algoritmo decifro el mensaje
                //out.writeUTF(String.valueOf(output));

                //Cierro el socket
                sc.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}