package GCT_Hostel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class Student {
    String Student_name;
    int RegNo;
    int Year;
    String Dept;
    String Hostel_name;
    int Room_no;
    public Student(int year,int room_no) throws SQLException {
        Scanner scan=new Scanner(System.in);
        hostelsDAO hostel=new hostelsDAO();
        System.out.println("Enter details of the student : ");
        System.out.println("Student name     : ");
        String name=scan.next();
        this.Student_name=name;
        System.out.println("Register number  : ");
        int rno=scan.nextInt();
        this.RegNo=rno;
        this.Year=year;
        System.out.println("Department       : ");
        String dpt=scan.next();
        this.Dept=dpt;
        this.Hostel_name=hostel.getHostelName(year);
        this.Room_no=room_no;
    }
}