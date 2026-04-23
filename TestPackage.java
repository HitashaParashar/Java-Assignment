import mypackage.*;

public class TestPackage {
    public static void main(String[] args) {
        System.out.println("=== Testing Package with 5 Classes ===\n");
        
        // Test Person class
        Person person = new Person("John Doe", 30);
        System.out.println("Person:");
        person.display();
        
        // Test Student class
        Student student = new Student("Alice Smith", 20, "S12345");
        System.out.println("\nStudent:");
        student.display();
        student.study();
        
        // Test Teacher class
        Teacher teacher = new Teacher("Mr. Johnson", 45, "Mathematics");
        System.out.println("\nTeacher:");
        teacher.display();
        teacher.teach();
        
        // Test MathUtils
        System.out.println("\nMathUtils:");
        System.out.println("10 + 20 = " + MathUtils.add(10, 20));
        System.out.println("10 * 20 = " + MathUtils.multiply(10, 20));
        
        // Test StringUtils
        System.out.println("\nStringUtils:");
        String test = "racecar";
        System.out.println("Reverse of 'hello': " + StringUtils.reverse("hello"));
        System.out.println("Is 'racecar' palindrome? " + StringUtils.isPalindrome(test));
    }
}