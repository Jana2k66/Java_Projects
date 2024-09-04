package com.jana.HostelApp.controller;

import com.jana.HostelApp.HostelApp;
import com.jana.HostelApp.model.Hostel;
import com.jana.HostelApp.model.Student;
import com.jana.HostelApp.service.HostelService;
import com.jana.HostelApp.service.RoomService;
import com.jana.HostelApp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

@Component
public class StudentController {

    private final StudentService studentService;
    @Autowired
    private HostelService hostelService;
    @Autowired
    private RoomService roomService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    public  void newAdmission(JFrame frame) {
        String year = JOptionPane.showInputDialog( "Enter Year:");
        String roomNumber = JOptionPane.showInputDialog("Enter Room Number:");
        if(year.isEmpty() || roomNumber.isEmpty())   {
            JOptionPane.showMessageDialog(null, "Enter All Details!","Missing Details",JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(Integer.parseInt(year)<=0 || Integer.parseInt(year)>=5  || Integer.parseInt(roomNumber)>hostelService.getTotalRooms(Integer.parseInt(year)) || Integer.parseInt(roomNumber)<=0){
            JOptionPane.showMessageDialog(null, "Room not exist!","Wrong Information",JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(roomService.getAvailable(Integer.parseInt(year),Integer.parseInt(roomNumber))>=roomService.getCapacity(Integer.parseInt(year),Integer.parseInt(roomNumber))){
            JOptionPane.showMessageDialog(null, "Room is Full!","Alert",JOptionPane.WARNING_MESSAGE);
        }
        else {
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
                String regNo =regNoField.getText();
                String dept = deptField.getText();
                if (name.isEmpty() || regNo.isEmpty() || dept.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter all details", "Missing Information", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                addStudent(name, regNo.trim().toLowerCase(), Integer.parseInt(year), dept, roomNumber);
                JOptionPane.showMessageDialog(frame, "Student added successfully!");
            }
        }
    }

    public void addStudent(String name, String rno, int year, String dept, String roomNumber) {
        studentService.saveStudent(new Student(name,rno.toLowerCase(),year,dept,roomNumber));
    }

    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    public void deleteStudent(JFrame frame,String id) {
        int result=studentService.deleteStudent(id);
        if(result==0) JOptionPane.showMessageDialog(frame, "Student deleted successfully!","Alert",JOptionPane.INFORMATION_MESSAGE);
        else if(result==-1) JOptionPane.showMessageDialog(frame, "Register number is not found!","Alert",JOptionPane.WARNING_MESSAGE);
    }
    public void showStudentDetail( JFrame frame,String rno ) {
        Student student=getStudentById(rno);
        if(student.getRegNo()==null){
            JOptionPane.showMessageDialog(frame, "Student is not present\n\t Enter valid register number", "Student Information", JOptionPane.WARNING_MESSAGE);
        }
        else{
            String studentInfo =
                    "Student Name        : " + student.getStudName() + "\n" +
                    "Register Number     : " + student.getRegNo() + "\n" +
                    "Year                : " + student.getStudYear() + "\n" +
                    "Department          : " + student.getStudDept() + "\n" +
                    "Hostel Name         : " + hostelService.getHostelNameByYear(student.getStudYear()) + "\n" +
                    "Room Number         : " + student.getRoomNumber() + "\n";
            JOptionPane.showMessageDialog(frame, studentInfo, "Student Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private Student getStudentById(String rno) {
        return studentService.getStudentById(rno);
    }
}
