package GCT_Hostel;
import java.sql.SQLOutput;
import java.util.*;
public class Home {
    public static void main(String[] args) {
        Scanner scan=new Scanner(System.in);
        hostelsDAO list=new hostelsDAO();
        StudentDAO info=new StudentDAO();
        try{
            do {
                System.out.println();
                System.out.println("          GOVERNMENT COLLEGE OF TECHNOLOGY         ");
                System.out.println("                HOSTEL MANAGEMENT          ");
                System.out.println();
                System.out.println("HOME PAGE :   ");
                System.out.println("1. Hostel Information");
                System.out.println("2. Student Information");
                System.out.println("3. New Admission");
                System.out.println("4. Vacate Hostel");
                System.out.println("5. Exit");
                System.out.println("");
                System.out.println("Enter choice from the above option : ");
                int choice=scan.nextInt();
                switch (choice)
                {
                    case 1:
                        System.out.println("Hostel Information :");
                        System.out.println("1. Ganga Illam    - 1st Year Hostel ");
                        System.out.println("2. Bhavani Illam  - 2nd Year Hostel ");
                        System.out.println("3. Vaigai Illam   - 3rd Year Hostel ");
                        System.out.println("4. Yamuna Illam   - 4th Year Hostel ");
                        System.out.println();
                        System.out.println("To Know the details of hostel press corresponding choice  . . .");
                        System.out.println("If you need information about all hostel then press 5 ");
                        System.out.println("Enter choice : ");
                        int ch=scan.nextInt();
                        switch (ch)
                        {
                            case 1,2,3,4:
                                list.displayParticularHostelInfo(ch);
                                break;
                            case 5:
                                list.displayHostelInfo();
                                break;
                            default:
                                System.out.println("You had entered invalid choice ");
                                break;
                        }
                        break;
                    case 2:
                        System.out.println("Enter register number of the student : ");
                        int rno=scan.nextInt();
                        info.displayStudentInfo(rno);
                        break;
                    case 3:
                        System.out.println("Enter your Year : ");
                        int year=scan.nextInt();
                        System.out.println("Enter expected room number : ");
                        int room_no=scan.nextInt();
                        System.out.println("How many student : ");
                        int n=scan.nextInt();
                        info.allotroom(year,room_no,n);
                        break;
                    case 4:
                        System.out.println("Hostel Vacate : ");
                        System.out.println();
                        System.out.println("Register number : ");
                        int regno=scan.nextInt();
                        System.out.println("Year            : ");
                        int y=scan.nextInt();
                        System.out.println("Confirm to vacate from hostel (Yes|No) : ");
                        String s=scan.next();
                        if(s.equals("Yes") || s.equals("YES") || s.equals("yes") ) {
                            info.deleteStudent(regno, y);
                        }
                        else
                        {
                            System.out.println("Okay . . .");
                        }
                        break;
                    case 5:
                        System.out.println("Exit . . . ");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("You had entered invalid choice");
                        break;
                }
            }while(true);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }
}