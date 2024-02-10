package GCT_Hostel_Management;

import javax.swing.*;
import java.sql.SQLException;

public class Student {
    private String studentName;
    private int regNo;
    private int year;
    private String department;
    private String hostelName;
    private int roomNo;

    public Student(int year, int roomNo) {
        try {
            HostelsDAO_GUI hostel = new HostelsDAO_GUI();

            JTextField nameField = new JTextField();
            JTextField regNoField = new JTextField();
            JTextField deptField = new JTextField();

            Object[] message = {
                    "Student Name:", nameField,
                    "Register Number:", regNoField,
                    "Department:", deptField
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Enter Student Details", JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION) {
                String name = nameField.getText();
                int regNo = Integer.parseInt(regNoField.getText());
                String dept = deptField.getText();

                this.studentName = name;
                this.regNo = regNo;
                this.year = year;
                this.department = dept;
                this.hostelName = hostel.getHostelName(year);
                this.roomNo = roomNo;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    // Getter methods
    public String getStudentName() {
        return studentName;
    }

    public int getRegNo() {
        return regNo;
    }

    public int getYear() {
        return year;
    }

    public String getDept() {
        return department;
    }

    public String getHostelName() {
        return hostelName;
    }

    public int getRoomNo() {
        return roomNo;
    }
}

