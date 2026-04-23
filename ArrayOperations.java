import java.util.Scanner;

/**
 * ============================================
 *   1-Dimensional Array Operations in Java
 *   Methods: input, output1, output2, reverse
 * ============================================
 * HOW TO RUN in VS Code terminal:
 *   javac ArrayOperations.java
 *   java ArrayOperations
 */

class ArrayMethods {

    int[] arr;
    int size;

    // ─────────────────────────────────────────
    //  METHOD 1: INPUT
    //  Takes array elements from user
    // ─────────────────────────────────────────
    void input() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the size of array: ");
        size = sc.nextInt();

        arr = new int[size];

        System.out.println("Enter " + size + " elements:");
        for (int i = 0; i < size; i++) {
            System.out.print("  Element [" + i + "]: ");
            arr[i] = sc.nextInt();
        }

        System.out.println("✔ Array input done!");
    }

    // ─────────────────────────────────────────
    //  METHOD 2: OUTPUT 1
    //  Prints array in simple line format
    // ─────────────────────────────────────────
    void output1() {
        System.out.println("\n--- Output 1: Simple Format ---");
        System.out.print("Array: ");
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i]);
            if (i < size - 1) System.out.print(", ");
        }
        System.out.println();
    }

    // ─────────────────────────────────────────
    //  METHOD 3: OUTPUT 2
    //  Prints array in index-wise table format
    // ─────────────────────────────────────────
    void output2() {
        System.out.println("\n--- Output 2: Index-wise Format ---");
        System.out.println("+-------+---------+");
        System.out.println("| Index |  Value  |");
        System.out.println("+-------+---------+");
        for (int i = 0; i < size; i++) {
            System.out.printf("|   %d   |   %4d  |%n", i, arr[i]);
        }
        System.out.println("+-------+---------+");
    }

    // ─────────────────────────────────────────
    //  METHOD 4: REVERSE
    //  Reverses the array and prints it
    // ─────────────────────────────────────────
    void reverse() {
        System.out.println("\n--- Reverse of Array ---");

        // Original array
        System.out.print("Original : ");
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i]);
            if (i < size - 1) System.out.print(", ");
        }
        System.out.println();

        // Reverse logic
        int[] reversed = new int[size];
        for (int i = 0; i < size; i++) {
            reversed[i] = arr[size - 1 - i];
        }

        // Print reversed array
        System.out.print("Reversed : ");
        for (int i = 0; i < size; i++) {
            System.out.print(reversed[i]);
            if (i < size - 1) System.out.print(", ");
        }
        System.out.println();
    }
}

// ─────────────────────────────────────────────
//  MAIN CLASS
// ─────────────────────────────────────────────
public class ArrayOperations {

    public static void main(String[] args) {

        System.out.println("============================================");
        System.out.println("   1-Dimensional Array Operations in Java   ");
        System.out.println("============================================");

        // Create object of ArrayMethods class
        ArrayMethods obj = new ArrayMethods();

        // Call all 4 methods
        obj.input();     // Method 1: Take input
        obj.output1();   // Method 2: Simple output
        obj.output2();   // Method 3: Table output
        obj.reverse();   // Method 4: Reverse array

        System.out.println("\n============================================");
        System.out.println("         All 4 Methods Executed!");
        System.out.println("============================================");
        
    }
}
