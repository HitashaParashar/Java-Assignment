import java.util.Scanner;

/**
 * ╔══════════════════════════════════════════════════════╗
 * ║         C Programs Runner - Pure Java Version        ║
 * ║  No GCC needed! Runs directly with: java CProgramsRunner ║
 * ╚══════════════════════════════════════════════════════╝
 *
 * HOW TO RUN (in VS Code terminal or any terminal):
 *   javac CProgramsRunner.java
 *   java CProgramsRunner
 */
public class CProgramsRunner {

    static Scanner sc = new Scanner(System.in);

    // ══════════════════════════════════════════════════════
    //  PROGRAM 1: FACTORIAL
    // ══════════════════════════════════════════════════════
    static void factorial() {
        System.out.println("\n==============================");
        System.out.println("   PROGRAM 1: FACTORIAL");
        System.out.println("==============================");

        System.out.print("Enter a positive integer: ");
        int n = sc.nextInt();

        if (n < 0) {
            System.out.println("Error! Factorial of a negative number doesn't exist.");
        } else {
            long fact = 1;
            for (int i = 1; i <= n; i++) {
                fact *= i;
            }
            System.out.println("Factorial of " + n + " = " + fact);
        }
    }

    // ══════════════════════════════════════════════════════
    //  PROGRAM 2: ARMSTRONG NUMBER
    // ══════════════════════════════════════════════════════
    static void armstrong() {
        System.out.println("\n==============================");
        System.out.println("   PROGRAM 2: ARMSTRONG NUMBER");
        System.out.println("==============================");
        System.out.println("(e.g. 153 = 1^3 + 5^3 + 3^3 = 153 ✓)");

        System.out.print("Enter an integer: ");
        int num = sc.nextInt();

        int original = num;
        int digits   = 0;
        int temp     = num;

        // Count digits
        while (temp != 0) {
            temp /= 10;
            digits++;
        }

        // Sum of each digit raised to power of total digits
        double result = 0;
        temp = num;
        while (temp != 0) {
            int remainder = temp % 10;
            result += Math.pow(remainder, digits);
            temp /= 10;
        }

        if ((int) result == original) {
            System.out.println(original + " IS an Armstrong number. ✓");
        } else {
            System.out.println(original + " is NOT an Armstrong number. ✗");
        }
    }

    // ══════════════════════════════════════════════════════
    //  PROGRAM 3: PALINDROME NUMBER
    // ══════════════════════════════════════════════════════
    static void palindrome() {
        System.out.println("\n==============================");
        System.out.println("   PROGRAM 3: PALINDROME NUMBER");
        System.out.println("==============================");
        System.out.println("(e.g. 121 reversed = 121 ✓)");

        System.out.print("Enter an integer: ");
        int num      = sc.nextInt();
        int original = num;
        int reversed = 0;

        while (num != 0) {
            int remainder = num % 10;
            reversed = reversed * 10 + remainder;
            num /= 10;
        }

        if (original == reversed) {
            System.out.println(original + " IS a Palindrome. ✓");
        } else {
            System.out.println(original + " is NOT a Palindrome. ✗");
        }
    }

    // ══════════════════════════════════════════════════════
    //  PROGRAM 4: FIBONACCI SERIES
    // ══════════════════════════════════════════════════════
    static void fibonacci() {
        System.out.println("\n==============================");
        System.out.println("   PROGRAM 4: FIBONACCI SERIES");
        System.out.println("==============================");
        System.out.println("(Each number = sum of previous two)");

        System.out.print("Enter number of terms: ");
        int n = sc.nextInt();

        int t1 = 0, t2 = 1;

        System.out.print("Fibonacci Series: ");
        for (int i = 1; i <= n; i++) {
            System.out.print(t1);
            if (i < n) System.out.print(", ");
            int next = t1 + t2;
            t1 = t2;
            t2 = next;
        }
        System.out.println();
    }

    // ══════════════════════════════════════════════════════
    //  PROGRAM 5: STAR PATTERN (Pyramid)
    // ══════════════════════════════════════════════════════
    static void pattern() {
        System.out.println("\n==============================");
        System.out.println("   PROGRAM 5: STAR PATTERN");
        System.out.println("==============================");
        System.out.println("(Full Pyramid Pattern)");

        System.out.print("Enter number of rows: ");
        int rows = sc.nextInt();

        System.out.println();
        for (int i = 1; i <= rows; i++) {
            // Print spaces
            for (int space = 1; space <= rows - i; space++) {
                System.out.print(" ");
            }
            // Print stars
            for (int j = 1; j <= 2 * i - 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    // ══════════════════════════════════════════════════════
    //  MAIN MENU
    // ══════════════════════════════════════════════════════
    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║     5 C Programs - Running in Java       ║");
        System.out.println("║     No GCC Required!                     ║");
        System.out.println("╚══════════════════════════════════════════╝");

        boolean running = true;

        while (running) {
            System.out.println("\n------------------------------------------");
            System.out.println("  SELECT A PROGRAM TO RUN:");
            System.out.println("------------------------------------------");
            System.out.println("  1. Factorial");
            System.out.println("  2. Armstrong Number");
            System.out.println("  3. Palindrome Number");
            System.out.println("  4. Fibonacci Series");
            System.out.println("  5. Star Pattern (Pyramid)");
            System.out.println("  6. Run ALL Programs");
            System.out.println("  0. Exit");
            System.out.println("------------------------------------------");
            System.out.print("  Enter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1: factorial();  break;
                case 2: armstrong();  break;
                case 3: palindrome(); break;
                case 4: fibonacci();  break;
                case 5: pattern();    break;
                case 6:
                    factorial();
                    armstrong();
                    palindrome();
                    fibonacci();
                    pattern();
                    break;
                case 0:
                    System.out.println("\nGoodbye! Happy Coding! 👋");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please enter 0-6.");
            }
        }

        sc.close();
    }
}