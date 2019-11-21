import java.util.concurrent.RecursiveAction;

public class FuerzaBruta extends RecursiveAction {

    private String mensaje_cifrado;
    private String mensaje_desifrado;
    private String clave;
    private final int bloque = 358800/8;
    private int start, end, ipermuta = 0;
    private static String desifrado;

    FuerzaBruta(String mensaje_cifrado, String mensaje_desifrado, String clave, int start, int end){
        this.mensaje_cifrado = mensaje_cifrado;
        this.mensaje_desifrado = mensaje_desifrado;
        this.end = end;
        this.start = start;
        this.clave = clave;
    }

    @Override
    protected void compute(){
        int fpermuta = 0;
        if ((end - start) <= bloque) {
            if (start == 0){ipermuta = 97; fpermuta = 101;}
            if (start == 44850){ipermuta = 100; fpermuta = 104;}
            if (start == 89700){ipermuta = 103; fpermuta = 107;}
            if (start == 134550){ipermuta = 106; fpermuta = 110;}
            if (start == 179400){ipermuta = 109; fpermuta = 113;}
            if (start == 224250){ipermuta = 112; fpermuta = 117;}
            if (start == 269100){ipermuta = 115; fpermuta = 120;}
            if (start == 313950){ipermuta = 119; fpermuta = 123;}
            mainLoop:
            for (int j = ipermuta; j < fpermuta; j++){
                for (int k = 97; k < 123; k++) {
                    for (int l = 97; l < 123; l++) {
                        for (int m = 97; m < 123; m++) {
                            String secretKey = String.valueOf((char) j) + String.valueOf((char) k) + String.valueOf((char) l) + String.valueOf((char) m);

                            if (clave.equals(secretKey)) {
                                System.out.println("su clave secreta es: " + secretKey);
                                try {
                                    finalize();
                                } catch (Throwable throwable) {
                                    throwable.printStackTrace();
                                }
                            }
                           /* desifrado = AES.decrypt(mensaje_cifrado, secretKey);

                            if (desifrado.equals(mensaje_desifrado)) {
                                System.out.println(desifrado + "   " + mensaje_desifrado);
                                System.out.println("su clave secreta es: " + secretKey);
                                try {
                                    break mainLoop;
                                } catch (Throwable throwable) {
                                    throwable.printStackTrace();
                                }
                            }*/
                        }
                    }
                }
            }

        } else {
            int middle = (start + end) / 2;
            FuerzaBruta subtaskA = new FuerzaBruta(mensaje_cifrado, mensaje_desifrado, clave, start, middle);
            FuerzaBruta subtaskB = new FuerzaBruta(mensaje_cifrado, mensaje_desifrado, clave,  middle, end);
            subtaskA.fork();
            subtaskB.fork();
        }
    }
}