import java.util.Scanner;

class Time {
    void add(String time1, String time2) {
        // Split "2:45:30" into [2, 45, 30]
        String[] parts1 = time1.split(":");
        String[] parts2 = time2.split(":");
        
        // Convert to numbers
        int hr1 = Integer.parseInt(parts1[0]);
        int min1 = Integer.parseInt(parts1[1]);
        int sec1 = parts1.length > 2 ? Integer.parseInt(parts1[2]) : 0;
        
        int hr2 = Integer.parseInt(parts2[0]);
        int min2 = Integer.parseInt(parts2[1]);
        int sec2 = parts2.length > 2 ? Integer.parseInt(parts2[2]) : 0;
        
        // Add everything
        int totalHr = hr1 + hr2;
        int totalMin = min1 + min2;
        int totalSec = sec1 + sec2;
        
        // Carry over
        totalMin = totalMin + (totalSec / 60);
        totalSec = totalSec % 60;
        
        totalHr = totalHr + (totalMin / 60);
        totalMin = totalMin % 60;
        
        // Print result
        System.out.println("Total Time: " + totalHr + ":" + totalMin + ":" + totalSec);
    }
}

public class Watch {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter time 1 (hr:min:sec or hr:min): ");
        String time1 = sc.nextLine();
        
        System.out.print("Enter time 2 (hr:min:sec or hr:min): ");
        String time2 = sc.nextLine();
        
        Time obj = new Time();
        obj.add(time1, time2);
        
        sc.close();
    }
}