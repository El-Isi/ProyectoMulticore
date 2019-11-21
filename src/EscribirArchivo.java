import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class EscribirArchivo {

    EscribirArchivo(String mensaje, String mensaje_cifrado) throws IOException {

        String ruta = "C:\\Users\\el_re\\Desktop\\Contraseñas.log";
        File archivo = new File(ruta);

        if (archivo.exists()) {

            try {

                FileWriter fstream = new FileWriter(ruta, true);
                BufferedWriter outp = new BufferedWriter(fstream);
                Date fecha = new Date();
                outp.append("\r\n");
                outp.write(fecha + "\t" + mensaje + "\t" + mensaje_cifrado);
                outp.append("\r\n");
                outp.close();

            } catch (IOException ex) {
                System.out.println("Error: " + ex.getMessage());
            }

        } else {
            archivo.createNewFile();
            try {
                FileWriter fstream = new FileWriter(ruta, true);
                BufferedWriter outp = new BufferedWriter(fstream);
                Date fecha = new Date();
                outp.write("Formato: Fecha - Palabra Enviada - Palabra Recibida");
                outp.append("\r\n");
                outp.write(fecha + "\t" + mensaje + "\t" + mensaje_cifrado);
                outp.append("\r\n");
                outp.close();
            } catch (IOException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }
}
