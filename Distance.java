import java.util.Scanner;
class Dis_cal{
    //1m=100cm, 1cm=10mm
    float mm( float value, String unit){
        //filter the value
        switch(unit.toLowerCase())
        {
            case"m" :return value *1000;
            case"cm" :return value *10;
            case "mm": return value;
            default: return 0;
        }}
        void adddistances(float val1,String unit1,float val2, String unit2){
            float suminmm= mm(val1,unit1)+ mm(val2,unit2);
            System.out.println(".........Result.........");
            System.out.println("total in mm"+(suminmm)+"mm");
            System.out.println("total in cm"+(suminmm/10)+"cm");
            System.out.println("total in m"+(suminmm/100)+"m");

        

    }

}
public class Distance{
    public static void main (String[] args){
        Scanner sc=new Scanner(System.in);
        //first distance
        System.out.println("enter first value of distance:");
        Float a=sc.nextFloat();
        System.out.println("enter unit(m,cm,mm)");
        String unitA =sc.next().toLowerCase();
        //second distance
        System.out.println("enter second value of distance:");
        Float b=sc.nextFloat();
        System.out.println("enter unit(m,cm,mm)");
        String unitB=sc.next().toLowerCase();
        Dis_cal calc =new Dis_cal();
        calc.adddistances(a,unitA,b,unitB);
        sc.close();
    }
}