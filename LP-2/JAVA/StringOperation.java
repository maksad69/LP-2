import java.util.Scanner;

public class StringOperation{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the string:");
        String str = sc.next();
        System.out.println("The string is:"+str);
        for( int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            //Bitwise AND
            int and = 127 & c;
            System.out.println("The AND of the character '"+c+"' is:"+ and);

            //Bitwise OR
            int or = 127 ^ c;
            System.out.println("The XOR of the character '"+c+"' is:"+ or);
        }
    }
}