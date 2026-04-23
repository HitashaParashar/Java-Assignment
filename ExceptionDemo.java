public class ExceptionDemo {
    public static void main(String[] args) {
        System.out.println("=== Exception Handling Demo ===\n");
        
        // Array Index Out of Bounds Exception
        System.out.println("1. ArrayIndexOutOfBoundsException Demo:");
        try {
            int[] numbers = new int[5];
            System.out.println("Created array of size 5");
            
            // Initialize array
            for (int i = 0; i < numbers.length; i++) {
                numbers[i] = i * 10;
                System.out.println("numbers[" + i + "] = " + numbers[i]);
            }
            
            // This will cause ArrayIndexOutOfBoundsException
            System.out.println("\nTrying to access index 10...");
            int value = numbers[10];
            System.out.println("Value: " + value);
            
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("CAUGHT EXCEPTION: " + e.getClass().getSimpleName());
            System.out.println("Message: " + e.getMessage());
            System.out.println("Proper Message: Cannot access array index beyond array size!");
            System.out.println("Array size is 5, but tried to access index 10");
        }
        
        System.out.println("\n" + "=".repeat(50) + "\n");
        
        // Arithmetic Exception
        System.out.println("2. ArithmeticException Demo:");
        try {
            int a = 10;
            int b = 0;
            System.out.println("Trying to divide " + a + " by " + b);
            
            int result = a / b;
            System.out.println("Result: " + result);
            
        } catch (ArithmeticException e) {
            System.out.println("CAUGHT EXCEPTION: " + e.getClass().getSimpleName());
            System.out.println("Message: " + e.getMessage());
            System.out.println("Proper Message: Cannot divide by zero! Division by zero is undefined.");
            System.out.println("Please ensure the denominator is not zero.");
        }
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("\nProgram continues after handling exceptions");
        System.out.println("Exception handling demonstrated successfully!");
    }
}
