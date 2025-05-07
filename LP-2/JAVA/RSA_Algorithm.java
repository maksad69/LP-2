import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class RSA_Algorithm {

    // Method to check if a number is prime
    private static boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    // Method to calculate GCD (Greatest Common Divisor)
    private static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the two prime numbers:");

        int p = sc.nextInt();
        int q = sc.nextInt();

        // Check if both numbers are prime
        if (!isPrime(p) || !isPrime(q)) {
            System.out.println("Both numbers must be prime numbers");
            return;
        }

        System.out.println("Entered prime numbers are " + p + " and " + q);

        int n = p * q;
        System.out.println("The value of N (p*q) is: " + n);

        int phi = (p - 1) * (q - 1);
        System.out.println("The value of Φ(N) is: " + phi);

        // Choose public exponent 'e' such that 1 < e < phi and gcd(e, phi) = 1
        int e = 3;
        while (gcd(e, phi) != 1) {
            e += 2; // try next odd number
        }

        System.out.println("The value of public exponent e is: " + e);

        // Compute private key d such that (d * e) % phi == 1
        BigInteger E = BigInteger.valueOf(e);
        BigInteger PHI = BigInteger.valueOf(phi);
        BigInteger D = E.modInverse(PHI);

        System.out.println("The value of private key d is: " + D);
        System.out.println("Public Key: (" + e + ", " + n + ")");
        System.out.println("Private Key: (" + D + ", " + n + ")");
    }
}
