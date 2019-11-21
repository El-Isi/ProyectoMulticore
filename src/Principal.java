public class Principal {
    public static void main(String[] args)
    {
        final String secretKey = "hide";

        String originalString = "hola";
        String encryptedString = AES.encrypt(originalString, secretKey);

        String decryptedString1 = AES.decrypt(encryptedString, "adios");
        String decryptedString2 = AES.decrypt(encryptedString, "adios");
        String decryptedString3 = AES.decrypt(encryptedString, "adios");
        String decryptedString4 = AES.decrypt(encryptedString, "adios");
        String decryptedString5 = AES.decrypt(encryptedString, "adios");
        String decryptedString6 = AES.decrypt(encryptedString, "adios");

        String decryptedString = AES.decrypt(encryptedString, secretKey);

        System.out.println(decryptedString2);
        System.out.println(decryptedString3);
        System.out.println(decryptedString4);
        System.out.println(decryptedString5);
        System.out.println(decryptedString6);
        System.out.println(originalString);
        System.out.println(encryptedString);
        System.out.println(decryptedString);

    }
}