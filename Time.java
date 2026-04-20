import java.util.Scanner;
class Watch{
    void add(int hr1,int min1,int sec1 , int hr2, int min2, int sec2)
    {
        int totalhr= hr1+hr2;
        int totalmin= min1+min2;
        int totalsec= sec1+sec2;
        totalmin +=totalsec/60;
        totalmin= totalsec%60;

        totalhr +=totalmin/60;
        totalhr =totalmin%60;
        System.out.println("Total of your Precious Time=" +totalhr+":"+totalmin+":"+totalsec);
        
    }
}
public class Time{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("enter hr1:");
        int hr1=sc.nextInt();
        System.out.println("enter min1:");
        int min1=sc.nextInt();
        System.out.println("enter sec1:");
        int sec1=sc.nextInt();
        System.out.println("enter hr2:");
        int hr2=sc.nextInt();
        System.out.println("enter min2:");
        int min2=sc.nextInt();
        System.out.println("enter sec2:");
        int sec2=sc.nextInt();
        Watch obj=new Watch();
        obj.add(hr1,min1,sec1,hr2,min2,sec2);
        sc.close();
        
    }
}