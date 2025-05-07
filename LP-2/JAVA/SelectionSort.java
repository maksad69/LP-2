import java.util.Scanner;

public class SelectionSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get array size from user
        System.out.print("Enter the size of the array: ");
        int size = getValidSize(scanner);

        // Create and input array elements
        int[] arr = new int[size];
        System.out.println("Enter " + size + " elements:");

        for (int i = 0; i < size; i++) {
            System.out.print("Element " + (i + 1) + ": ");
            arr[i] = getValidNumber(scanner);
        }

        // Print original array
        System.out.println("\nOriginal array:");
        printArray(arr);

        // Perform selection sort with step-by-step visualization
        selectionSort(arr);

        // Print final sorted array
        System.out.println("\nFinal sorted array:");
        printArray(arr);

        scanner.close();
    }

    public static void selectionSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            // Find minimum element in unsorted part
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap minimum element with first element of unsorted part
            if (minIndex != i) {
                System.out.println("\nStep " + (i + 1) + ":");
                System.out.println("Swapping " + arr[i] + " with " + arr[minIndex]);

                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;

                printArray(arr);
            } else {
                System.out.println("\nStep " + (i + 1) + ":");
                System.out.println("Element " + arr[i] + " is already in correct position");
                printArray(arr);
            }
        }
    }

    public static void printArray(int[] arr) {
        System.out.print("[ ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println(" ]");
    }

    public static int getValidSize(Scanner scanner) {
        int size;
        while (true) {
            try {
                size = Integer.parseInt(scanner.nextLine().trim());
                if (size <= 0) {
                    System.out.print("Please enter a positive number: ");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
        return size;
    }

    public static int getValidNumber(Scanner scanner) {
        int number;
        while (true) {
            try {
                number = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
        return number;
    }
}