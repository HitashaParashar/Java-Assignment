import java.util.Scanner;
public class Marks{
    public static void main(String[] args)
    {
        Scanner sc=new Scanner (System.in);
        System.out.println("enter your marks:");
        int m=sc.nextInt();
        if (m<0||m>100)
        {
            System.out.println("invalid");
        }
        else{
            double percentage=(m/100.0)*100;
            System.out.println("percentage:"+percentage+"%");

        }
        if(m==100||m>=90)
        {
            System.out.println("A+");
        }
        else if(m>=80){
            System.out.println("B+");
        }
        else{
            System.out.println("fail");
        }
        sc.close();

    }

}