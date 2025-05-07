from Crypto.Cipher import DES
from Crypto.Random import get_random_bytes
import base64

# Pad text to be multiple of 8 bytes
def pad(text):
    while len(text) % 8 != 0:
        text += ' '
    return text

# Generate a 8-byte DES key
key = get_random_bytes(8)
print("DES Key (Hex):", key.hex())

# Get user input
text = input("Enter message to encrypt: ")
padded_text = pad(text)

# Encrypt
cipher = DES.new(key, DES.MODE_ECB)
encrypted = cipher.encrypt(padded_text.encode())
print("Encrypted (Base64):", base64.b64encode(encrypted).decode())

# Decrypt
decrypted = cipher.decrypt(encrypted).decode().strip()
print("Decrypted message:", decrypted)
