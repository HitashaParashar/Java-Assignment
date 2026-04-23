import mypackage.Person;

public class Employee extends Person {
    private int empId;
    
    public Employee(String name, int age, int empId) {
        super(name, age);
        this.empId = empId;
    }
    
    public void displayEmployee() {
        System.out.println("Employee ID: " + empId);
    }
}