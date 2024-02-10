package GCT_Hostel_Management;
import javax.swing.*;
import java.sql.*;

public class StudentDAO_GUI {
    public void displayStudentInfoGUI(int rno) {
        try {
            String query = "Select * from student where regno=?";
            Connection con = DbConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, rno);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String studentName = rs.getString(1);
                int registerNumber = rs.getInt(2);
                int year = rs.getInt(3);
                String department = rs.getString(4);
                String hostelName = rs.getString(5);
                int roomNumber = rs.getInt(6);

                String studentInfo = "Details of the student:\n" +
                        "Student Name    : " + studentName + "\n" +
                        "Register number : " + registerNumber + "\n" +
                        "Year            : " + year + "\n" +
                        "Department      : " + department + "\n" +
                        "Hostel Name     : " + hostelName + "\n" +
                        "Room number     : " + roomNumber;

                JOptionPane.showMessageDialog(null, studentInfo, "Student Information", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Register number is not found in the hostel");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public void displayRoomInfoGUI(String hname, int rno, int cap, int available) {
        try {
            String query = "Select student_name,regno from student where hostel_name=? and room_no=?";
            Connection con = DbConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, hname);
            pst.setInt(2, rno);
            ResultSet rs = pst.executeQuery();

            StringBuilder roomInfo = new StringBuilder();
            int i = 1;
            roomInfo.append("Student list of room: ").append(rno).append("\n");
            roomInfo.append("Capacity: ").append(cap).append("\n");
            roomInfo.append("Student available: ").append(available).append("\n\n");

            while (rs.next()) {
                roomInfo.append(i).append(". Name of the student  : ").append(rs.getString(1)).append("\n");
                roomInfo.append("   Register number      : ").append(rs.getInt(2)).append("\n\n");
                i++;
            }

            if (i == 1) {
                JOptionPane.showMessageDialog(null, "Nobody present in the room " + rno, "Room Information", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, roomInfo.toString(), "Room Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public void allotRoomGUI(int y, int r, int n) {
        int flag = isAvailable(y, r, n);
        if (flag == 1) {
            for (int i = 0; i < n; i++) {
                Student student = new Student(y, r);
                int a = newStudent(student);
                if (a == 1) {
                    JOptionPane.showMessageDialog(null, "Room is allotted for " + r, "Room Allotment", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Enter the details correctly...", "Room Allotment", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Sorry, the room is full. Please try another room.", "Room Allotment", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void deleteStudentGUI(int r, int y) {
        try {
            String query = "delete from student where regno=? and student_year=?";
            Connection con = DbConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, r);
            pst.setInt(2, y);
            int n = pst.executeUpdate();

            if (n == 1) {
                JOptionPane.showMessageDialog(null, r + " is vacated from hostel", "Student Deletion", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Details mismatch...", "Student Deletion", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public int newStudent(Student student) {
        try {
            String query = "Insert into student values(?,?,?,?,?,?)";
            Connection con = DbConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, student.getStudentName());
            pst.setInt(2, student.getRegNo());
            pst.setInt(3, student.getYear());
            pst.setString(4, student.getDept());
            pst.setString(5, student.getHostelName());
            pst.setInt(6, student.getRoomNo());
            int n = pst.executeUpdate();

            if (n == 1)
                return 1;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return 0;
    }

    public int isAvailable(int y, int r, int n) {
        try {
            String query = "Select student_available,capacity from room where hostel_year=? and room_no=? ";
            Connection con = DbConnection.getConnection();
            PreparedStatement pst = con.prepareStatement(query);
            pst.setInt(1, y);
            pst.setInt(2, r);
            ResultSet rs = pst.executeQuery();
            rs.next();
            int studentAvailable = rs.getInt(1);
            int count = rs.getInt(2);
            studentAvailable += n;
            if (studentAvailable <= count)
                return 1;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return 0;
    }
}
