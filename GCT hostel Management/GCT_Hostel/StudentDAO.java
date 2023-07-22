package GCT_Hostel;

import java.sql.*;

public class StudentDAO {
    public void displayStudentInfo(int rno) throws SQLException {
        String query = "Select * from student where regno=? ";
        Connection con = DbConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, rno);
        ResultSet rs = pst.executeQuery();
            try{rs.next();
            System.out.println("Details of the student : ");
            System.out.println("Student Name    : " + rs.getString(1));
            System.out.println("Register number : " + rs.getInt(2));
            System.out.println("Year            : " + rs.getInt(3));
            System.out.println("Department      : " + rs.getString(4));
            System.out.println("Hostel Name     : " + rs.getString(5));
            System.out.println("Room number     : " + rs.getInt(6));
            System.out.println("");
            System.out.println("------------------------------------------------------------");
        }
            catch(Exception e)
            {
                System.out.println("Register number is not found in the hostel ");
            }
    }

    public void displayRoomInfo(String hname,int rno,int cap,int available) throws SQLException {
        String query = "Select student_name,regno from student where hostel_name=? and room_no=?";
        Connection con = DbConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, hname);
        pst.setInt(2, rno);
        ResultSet rs = pst.executeQuery();
        int i = 1;
        System.out.println("Student list of room : " + rno);
        System.out.println("Capacity             : "+cap);
        System.out.println("Student available    : "+available);
        System.out.println();
        try {
            while (rs.next()) {

                System.out.println(i + ". Name of the student  : " + rs.getString(1));
                System.out.println("   Register number      : " + rs.getInt(2));
                System.out.println("");
                i++;
            }
            System.out.println("------------------------------------------------------------");
        }
        catch(Exception e) {
            System.out.println("Nobody present in the room "+rno);
        }
    }
    public void allotroom(int y,int r,int n)throws SQLException
    {
        int flag=IsAvailable(y,r,n);
        if(flag==1)
        {
            for(int i=0;i<n;i++) {
                Student student = new Student(y, r);
                StudentDAO add=new StudentDAO();
                int a=add.newStudent(student);
                if(a==1)
                {
                    System.out.println("Room is alloted for "+r);
                }
                else {
                    System.out.println("Enter the details correctly . . .");
                }
            }

        }
        else
        {
            System.out.println("Sorry room is full . . . ");
            System.out.println("Try Another room");
        }
    }
    public void deleteStudent(int r,int y)throws SQLException
    {

            String query = "delete from student where regno=? and student_year=?";
            Connection con = DbConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1,r);
            pst.setInt(2, y);
            int n=pst.executeUpdate();
            if(n==1)
                System.out.println(r+" is vacated from hostel");
            else
                System.out.println("Details mismatch . . .");

    }
    public int newStudent(Student student) throws SQLException
    {
        String query = "Insert into student values(?,?,?,?,?,?)";
        Connection con = DbConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, student.Student_name);
        pst.setInt(2, student.RegNo);
        pst.setInt(3, student.Year);
        pst.setString(4, student.Dept);
        pst.setString(5, student.Hostel_name);
        pst.setInt(6, student.Room_no);
        int n=pst.executeUpdate();
        if(n==1)
            return 1;
        return 0;
    }

    public int IsAvailable(int y,int r,int n) throws SQLException
    {
        String query = "Select student_available,capacity from room where hostel_year=? and room_no=? ";
        Connection con = DbConnection.getConnection();
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, y);
        pst.setInt(2, r);
        ResultSet rs = pst.executeQuery();
        rs.next();
        int student_available=rs.getInt(1);
        int count=rs.getInt(2);
        student_available+=n;
        if(student_available<=count)
            return 1;
        return 0;
    }
}