#First, you'll need to install the pycryptodome library. You can do this using pip
#pip install pycryptodome

from Crypto.Cipher import DES
from Crypto.Util.Padding import pad, unpad
from Crypto.Random import get_random_bytes

def encrypt_des(plain_text, key):
    # Create a DES cipher object with the given key
    cipher = DES.new(key, DES.MODE_CBC)

    # Pad the plain text to make sure its length is a multiple of DES block size (8 bytes)
    padded_text = pad(plain_text.encode(), DES.block_size)

    # Encrypt the padded text
    cipher_text = cipher.encrypt(padded_text)

    # Return both the ciphertext and the IV (initialization vector)
    return cipher_text, cipher.iv

def decrypt_des(cipher_text, key, iv):
    # Create a DES cipher object with the given key and IV
    cipher = DES.new(key, DES.MODE_CBC, iv)

    # Decrypt the ciphertext
    decrypted_text = cipher.decrypt(cipher_text)

    # Unpad the decrypted text
    return unpad(decrypted_text, DES.block_size).decode()

def main():
    # 8-byte DES key (must be 8 bytes long for DES)
    key = b'12345678'

    # Input the plain text
    plain_text = input("Enter the plain text: ")

    # Encrypt the message
    cipher_text, iv = encrypt_des(plain_text, key)
    print("Cipher Text (Hex):", cipher_text.hex())

    # Decrypt the message
    decrypted_text = decrypt_des(cipher_text, key, iv)
    print("Decrypted Text:", decrypted_text)

if __name__ == "__main__":
    main()