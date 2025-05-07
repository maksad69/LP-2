def main():
    s = input("Enter the string: ")
    print("The string is:", s)
    for c in s:
        and_result = 127 & ord(c)
        xor_result = 127 ^ ord(c)
        print(f"The AND of the character '{c}' is: {and_result}")
        print(f"The XOR of the character '{c}' is: {xor_result}")

if __name__ == "__main__":
    main()
