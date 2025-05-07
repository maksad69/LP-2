import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class KeyedTranspositionCipher {
    public static String encrypt(String text, String key) {
        int col = key.length();
        int row = (int) Math.ceil((double) text.length() / col);
        char[][] matrix = new char[row][col];
        // Fill matrix row-wise with plaintext
        int index = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (index < text.length()) {
                    matrix[i][j] = text.charAt(index++);
                } else {
                    matrix[i][j] = 'X'; // Padding character
                }
            }
        }
        // Determine column order based on sorted key
        Integer[] order = getColumnOrder(key);
        // Read matrix column-wise using key order
        StringBuilder cipherText = new StringBuilder();
        for (int i = 0; i < col; i++) {
            int colIndex = order[i];
            for (int j = 0; j < row; j++) {
                cipherText.append(matrix[j][colIndex]);
            }
        }
        return cipherText.toString();
    }
    public static String decrypt(String cipherText, String key) {
        int col = key.length();
        int row = (int) Math.ceil((double) cipherText.length() / col);
        char[][] matrix = new char[row][col];
        // Determine column order based on sorted key
        Integer[] order = getColumnOrder(key);
        // Fill matrix column-wise using key order
        int index = 0;
        for (int i = 0; i < col; i++) {
            int colIndex = order[i];
            for (int j = 0; j < row; j++) {
                matrix[j][colIndex] = cipherText.charAt(index++);
            }
        }
        // Read matrix row-wise for plaintext
        StringBuilder plainText = new StringBuilder();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                plainText.append(matrix[i][j]);
            }
        }
        return plainText.toString().replace("X", ""); // Remove padding
    }
    private static Integer[] getColumnOrder(String key) {
        Character[] keyArray = new Character[key.length()];
        for (int i = 0; i < key.length(); i++) {
            keyArray[i] = key.charAt(i);
        }
        Integer[] order = new Integer[key.length()];
        for (int i = 0; i < key.length(); i++) {
            order[i] = i;
        }
        Arrays.sort(order, Comparator.comparingInt(i -> keyArray[i]));
        return order;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the text to encrypt: ");
        String text = scanner.nextLine();
        System.out.print("Enter the key: ");
        String key = scanner.nextLine();

        String encrypted = encrypt(text, key);
        System.out.println("Encrypted: " + encrypted);
        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted: " + decrypted);
    }
}
