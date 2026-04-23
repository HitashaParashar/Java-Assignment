import mypackage.Person;
import mypackage.subpackage.Employee;

public class MainProgram {
    public static void main(String[] args) {
        System.out.println("=== Package and Sub-package Demo ===\n");

        // Using Person from main package
        Person p = new Person("John Doe", 25);
        System.out.print("Person: ");
        p.display();

        // Using Employee from subpackage
        Employee e = new Employee("Alice Smith", 30, 101);
        System.out.print("Employee: ");
        e.display();
        e.displayEmployee();

        System.out.println("\n✓ SUCCESS! Package and sub-package working!");
    }
}