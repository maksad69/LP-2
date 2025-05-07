import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Scanner;

public class DES_Encryption {

    // Method to generate a DES key
    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
        keyGen.init(56); // DES uses a 56-bit key
        return keyGen.generateKey();
    }

    // Method to encrypt plain text using DES
    public static String encrypt(String plainText, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes); // Convert to base64 for readability
    }

    // Method to decrypt cipher text using DES
    public static String decrypt(String cipherText, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(cipherText));
        return new String(decryptedBytes);
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter a message to encrypt: ");
            String message = sc.nextLine();

            // Generate DES key
            SecretKey key = generateKey();
            System.out.println("\nDES Secret Key (Hex): " + bytesToHex(key.getEncoded()));

            // Encrypt the message
            String encryptedMessage = encrypt(message, key);
            System.out.println("Encrypted Message (Base64): " + encryptedMessage);

            // Decrypt the message
            String decryptedMessage = decrypt(encryptedMessage, key);
            System.out.println("Decrypted Message: " + decryptedMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Utility method to convert bytes to hex string
    public static String bytesToHex(byte[] bytes) {
        StringBuilder hex = new StringBuilder();
        for (byte b : bytes) {
            hex.append(String.format("%02X", b));
        }
        return hex.toString();
    }
}
