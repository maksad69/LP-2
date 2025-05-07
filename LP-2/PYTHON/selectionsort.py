def get_int(msg, cond=lambda x: True):
    while True:
        try:
            x = int(input(msg))
            if cond(x): return x
            print("Please enter a valid number.")
        except: print("Please enter a valid number.")

def print_array(arr):
    print("[ " + ", ".join(map(str, arr)) + " ]")

def selection_sort(arr):
    for i in range(len(arr) - 1):
        min_idx = min(range(i, len(arr)), key=arr.__getitem__)
        print(f"\nStep {i + 1}:")
        if min_idx != i:
            print(f"Swapping {arr[i]} with {arr[min_idx]}")
            arr[i], arr[min_idx] = arr[min_idx], arr[i]
        else:
            print(f"Element {arr[i]} is already in correct position")
        print_array(arr)

def main():
    size = get_int("Enter the size of the array: ", lambda x: x > 0)
    arr = [get_int(f"Element {i+1}: ") for i in range(size)]
    print("\nOriginal array:")
    print_array(arr)
    selection_sort(arr)
    print("\nFinal sorted array:")
    print_array(arr)

if __name__ == "__main__":
    main()