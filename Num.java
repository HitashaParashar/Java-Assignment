import java.util.Scanner;
    class Number{
        //method add
        void add(int a, int b){
            System.out.println("Add" +(a+b));
        }
        //method subtract
        void subtract(int a, int b){
            System.out.println("subtract"+ (a-b));
        }
        //method multiply
        void multiply(int a, int b){
            System.out.println("multiply"+(a*b));
        }  
        //method division
        void division(int a, int b){
            if(b==0){
                System.out.println("not divisible");
            }
            else{
                System.out.println("division"+ (a/b));
            }
        }  
    
    }
public class Num{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        //first we tell user what to do then take input  
        System.out.println("enter value of a:");
        int a=sc.nextInt();
        
        System.out.println("enter the value of b:");
        int b=sc.nextInt();
        //make a oject of class Number
        Number o=new Number();
        //calling methods of class Number
        //we write a,b because it want data to excecute the method
        o.add(a,b);
        o.subtract(a,b);
        o.multiply(a,b);
        o.division(a,b);
        sc.close();

    }
}    

