from math import gcd, isqrt
from sympy import mod_inverse

# Function to check if a number is prime
def is_prime(num):
    if num <= 1:
        return False
    for i in range(2, isqrt(num) + 1):
        if num % i == 0:
            return False
    return True

# Input two prime numbers
p = int(input("Enter the first prime number: "))
q = int(input("Enter the second prime number: "))

# Check for primality
if not is_prime(p) or not is_prime(q):
    print("Both numbers must be prime numbers")
    exit()

print(f"Entered prime numbers are {p} and {q}")

n = p * q
print(f"The value of N (p*q) is: {n}")

phi = (p - 1) * (q - 1)
print(f"The value of Φ(N) is: {phi}")

# Find e such that gcd(e, phi) == 1
e = 3
while gcd(e, phi) != 1:
    e += 2  # Try next odd number

print(f"The value of public exponent e is: {e}")

# Find private key d such that (d * e) % phi == 1
d = mod_inverse(e, phi)

print(f"The value of private key d is: {d}")
print(f"Public Key: ({e}, {n})")
print(f"Private Key: ({d}, {n})")
