import java.util.*;

// Interface
interface Drawable {
    void draw();
    String getColor();
}

// Abstract Class
abstract class Shape implements Drawable {
    protected String color;
    protected double area;
    
    public Shape(String color) {
        this.color = color;
    }
    
    public abstract double calculateArea();
    
    public void displayInfo() {
        System.out.println("Shape Color: " + color);
        System.out.println("Shape Area: " + calculateArea());
    }
}

// Concrete Class 1
class Circle extends Shape {
    private double radius;
    
    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }
    
    @Override
    public double calculateArea() {
        area = Math.PI * radius * radius;
        return area;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing a " + color + " circle with radius " + radius);
    }
    
    @Override
    public String getColor() {
        return color;
    }
}

// Concrete Class 2
class Rectangle extends Shape {
    private double length;
    private double width;
    
    public Rectangle(String color, double length, double width) {
        super(color);
        this.length = length;
        this.width = width;
    }
    
    @Override
    public double calculateArea() {
        area = length * width;
        return area;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing a " + color + " rectangle " + length + "x" + width);
    }
    
    @Override
    public String getColor() {
        return color;
    }
}

// Concrete Class 3
class Triangle extends Shape {
    private double base;
    private double height;
    
    public Triangle(String color, double base, double height) {
        super(color);
        this.base = base;
        this.height = height;
    }
    
    @Override
    public double calculateArea() {
        area = 0.5 * base * height;
        return area;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing a " + color + " triangle with base " + base + " and height " + height);
    }
    
    @Override
    public String getColor() {
        return color;
    }
}

// Inheritance - Vehicle Hierarchy
class Vehicle {
    protected String brand;
    protected String model;
    protected int year;
    
    public Vehicle(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }
    
    public void start() {
        System.out.println(brand + " " + model + " is starting...");
    }
    
    public void stop() {
        System.out.println(brand + " " + model + " is stopping...");
    }
    
    public void displayInfo() {
        System.out.println("Brand: " + brand + ", Model: " + model + ", Year: " + year);
    }
}

class Car extends Vehicle {
    private int numberOfDoors;
    
    public Car(String brand, String model, int year, int numberOfDoors) {
        super(brand, model, year);
        this.numberOfDoors = numberOfDoors;
    }
    
    @Override
    public void start() {
        System.out.println(brand + " " + model + " car is starting with key...");
    }
    
    public void honk() {
        System.out.println(brand + " " + model + " says: Beep Beep!");
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Doors: " + numberOfDoors);
    }
}

class Motorcycle extends Vehicle {
    private boolean hasSidecar;
    
    public Motorcycle(String brand, String model, int year, boolean hasSidecar) {
        super(brand, model, year);
        this.hasSidecar = hasSidecar;
    }
    
    @Override
    public void start() {
        System.out.println(brand + " " + model + " motorcycle is starting with kick...");
    }
    
    public void wheelie() {
        System.out.println(brand + " " + model + " is doing a wheelie!");
    }
    
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Has Sidecar: " + (hasSidecar ? "Yes" : "No"));
    }
}

// Main Class
public class OOPDemo {
    public static void main(String[] args) {
        System.out.println("=== Inheritance, Interface & Abstract Classes Demo ===\n");
        
        // Part 1: Abstract Class and Interface Demo
        System.out.println("--- Shapes (Abstract Class + Interface) ---");
        
        Shape[] shapes = {
            new Circle("Red", 5.0),
            new Rectangle("Blue", 4.0, 6.0),
            new Triangle("Green", 3.0, 4.0)
        };
        
        for (Shape shape : shapes) {
            shape.draw();
            System.out.println("Color from interface: " + shape.getColor());
            System.out.printf("Area: %.2f\n", shape.calculateArea());
            shape.displayInfo();
            System.out.println();
        }
        
        // Part 2: Inheritance Demo
        System.out.println("--- Vehicles (Inheritance) ---");
        
        Car car = new Car("Toyota", "Camry", 2022, 4);
        Motorcycle bike = new Motorcycle("Harley-Davidson", "Street 750", 2021, false);
        
        System.out.println("Car Details:");
        car.displayInfo();
        car.start();
        car.honk();
        car.stop();
        
        System.out.println("\nMotorcycle Details:");
        bike.displayInfo();
        bike.start();
        bike.wheelie();
        bike.stop();
        
        // Polymorphism Demo
        System.out.println("\n--- Polymorphism Demo ---");
        Vehicle[] vehicles = {car, bike};
        
        for (Vehicle vehicle : vehicles) {
            vehicle.start();
            if (vehicle instanceof Car) {
                ((Car) vehicle).honk();
            } else if (vehicle instanceof Motorcycle) {
                ((Motorcycle) vehicle).wheelie();
            }
        }
        
        // Interface polymorphism
        System.out.println("\n--- Interface Polymorphism ---");
        List<Drawable> drawables = new ArrayList<>();
        drawables.add(new Circle("Purple", 3.0));
        drawables.add(new Rectangle("Yellow", 5.0, 7.0));
        
        for (Drawable d : drawables) {
            d.draw();
            System.out.println("Color: " + d.getColor());
        }
        
        System.out.println("\n=== Demo Completed ===");
    }
}
