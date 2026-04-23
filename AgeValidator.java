import java.util.Scanner;

// User-defined Exception Class
class AgeOutOfRangeException extends Exception {
    public AgeOutOfRangeException(String message) {
        super(message);
    }
}

// Student Class
class Student {
    private String name;
    private int age;
    
    public Student(String name, int age) throws AgeOutOfRangeException {
        if (age < 0 || age > 150) {
            throw new AgeOutOfRangeException("Invalid age: " + age + ". Age must be between 0 and 150.");
        }
        if (age < 5) {
            throw new AgeOutOfRangeException("Age " + age + " is too young for school. Minimum age is 5.");
        }
        if (age > 100) {
            throw new AgeOutOfRangeException("Age " + age + " is too old for school. Maximum age is 100.");
        }
        this.name = name;
        this.age = age;
    }
    
    public void display() {
        System.out.println("Student Name: " + name);
        System.out.println("Student Age: " + age);
        System.out.println("Status: Valid student age");
    }
}

public class AgeValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Student Age Range Validator ===\n");
        System.out.println("Valid age range: 5 to 100 years\n");
        
        while (true) {
            try {
                System.out.print("Enter student name (or 'quit' to exit): ");
                String name = scanner.nextLine();
                
                if (name.equalsIgnoreCase("quit")) {
                    System.out.println("Exiting program...");
                    break;
                }
                
                System.out.print("Enter student age: ");
                int age = Integer.parseInt(scanner.nextLine());
                
                Student student = new Student(name, age);
                System.out.println("\n✓ Student registered successfully!");
                student.display();
                
            } catch (AgeOutOfRangeException e) {
                System.out.println("\n✗ Age Validation Error: " + e.getMessage());
                System.out.println("Please enter a valid age between 5 and 100.\n");
                
            } catch (NumberFormatException e) {
                System.out.println("\n✗ Invalid input! Please enter a numeric value for age.\n");
            }
            System.out.println("-".repeat(50));
        }
        
        scanner.close();
        System.out.println("\nProgram completed!");
    }
}
