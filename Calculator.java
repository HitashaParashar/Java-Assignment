import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter a number");
        int n1 = sc.nextInt();
        System.out.println("enter another number");
        int n2 = sc.nextInt();
        System.out.println("1 for add");
        System.out.println("2 for sub");
        System.out.println("3 for div");
        System.out.println("4 for mul");
        System.out.println("5 for mod");
        System.out.println("enter choice from(1,2,3,4,5)");
        int n = sc.nextInt();
        if (n == 1) {
            double add = n1 + n2;
            System.out.println("result is" + add);
        } else if (n == 2) {
            double sub = n1 - n2;
            System.out.println("result is:" + sub);
        } else if (n == 3) {
            if(n2==0){
               System.out.println("not divisible");}
            else
                { double div = n1 / n2;
            System.out.println("result is:" + div);}
        }

         else if (n == 4) {
            double mul = n1 * n2;
            System.out.println("result is:" + mul);
        } else if (n == 5) {
            double mod = n1 % n2;
            System.out.println("result is:" + mod);
        } else {
            System.out.println("invalid");
        }
        sc.close();
    }}