import java.util.Scanner;

public class Students {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter your obtained marks :");
        int marks = sc.nextInt();
        if (marks < 0 || marks > 500) {
            System.out.println("invalid");
        } else {
            double percentage = ((marks / 500.0) * 100);
            System.out.println("percentage:" + percentage + "%");
            if (marks == 500 &&marks >= 480) {
                System.out.println("A+");
            } else if (marks >= 460) {
                System.out.println("A");
            } else if (marks >= 400) {
                System.out.println("B+");
            } else if (marks >= 350) {
                System.out.println("B");
            } else if (marks >= 300) {
                System.out.println("C");
            } else if (marks >= 250) {
                System.out.println("D");
            } else if (marks>= 150) {
                System.out.println("E");
            } else {
                System.out.println("fail");
            }
            sc.close();

        }
    }
}
