import math

def get_column_order(key):
    return sorted(range(len(key)), key=lambda k: key[k])

def encrypt(text, key):
    col = len(key)
    row = math.ceil(len(text) / col)
    # Padding
    text += 'X' * (row * col - len(text))
    # Fill matrix row-wise
    matrix = [list(text[i * col:(i + 1) * col]) for i in range(row)]

    order = get_column_order(key)
    cipher = ''
    for idx in order:
        for r in range(row):
            cipher += matrix[r][idx]
    return cipher

def decrypt(cipher, key):
    col = len(key)
    row = math.ceil(len(cipher) / col)

    order = get_column_order(key)
    matrix = [[''] * col for _ in range(row)]

    index = 0
    for idx in order:
        for r in range(row):
            matrix[r][idx] = cipher[index]
            index += 1

    # Read row-wise
    plain = ''.join(matrix[r][c] for r in range(row) for c in range(col))
    return plain.rstrip('X')  # Remove padding

# Main program
if __name__ == "__main__":
    text = input("Enter the text to encrypt: ").replace(" ", "")
    key = input("Enter the key: ")

    encrypted = encrypt(text, key)
    print("Encrypted:", encrypted)

    decrypted = decrypt(encrypted, key)
    print("Decrypted:", decrypted)