package com.jana.HostelApp.controller;

import com.jana.HostelApp.model.Hostel;
import com.jana.HostelApp.model.Student;
import com.jana.HostelApp.service.HostelService;
import com.jana.HostelApp.service.RoomService;
import com.jana.HostelApp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.util.List;

@Component
public class HostelController {
    @Autowired
    HostelService hostelService;
    @Autowired
    StudentService studentService;
    @Autowired
    RoomService roomService;
    public void hostelDetails(JFrame frame){
        String choice = JOptionPane.showInputDialog("Enter choice from the above options:\n" +
                "1. Ganga Illam - 1st Year Hostel\n" +
                "2. Bhavani Illam - 2nd Year Hostel\n" +
                "3. Vaigai Illam - 3rd Year Hostel\n" +
                "4. Yamuna Illam - 4th Year Hostel\n" +
                "5. All Hostels");
        try {
            int ch = Integer.parseInt(choice);
            switch (ch) {
                case 1, 2, 3, 4:
                    showHostelDetail(frame,ch);
                    break;
                case 5:
                    showHostelList(frame);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "You had entered an invalid choice");
                    break;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Enter correct choice " ,"Alert",JOptionPane.WARNING_MESSAGE);
        }
    }

    private void showHostelDetail(JFrame frame,int ch) {
        Hostel hostel=getHostelByYear(ch);
        String hostelInfo =
                "           " + hostel.getHostelName() + "  Hostel\n" +
                "Warden Name        : " + hostel.getWardenName() + "\n" +
                "Watchman Name      : " + hostel.getWatchmanName() + "\n" +
                "Year               : " + hostel.getYear() + "\n" +
                "Total Rooms        : " + hostel.getRooms() + "\n\n";
        int option =JOptionPane.showConfirmDialog(frame, hostelInfo+ "\nPress 'OK' for Room Information or 'Cancel' to exit: "  , "Hostel Information", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String roomNum =JOptionPane.showInputDialog("Enter room number: ");
            if(roomNum.isEmpty()){
                JOptionPane.showMessageDialog(null, "Enter Room Number!","Miss Information",JOptionPane.WARNING_MESSAGE);
                return;
            }
            if( Integer.parseInt(roomNum)> hostel.getRooms() || Integer.parseInt(roomNum)<1){
                JOptionPane.showMessageDialog(null, "Room not exist!","Wrong Information",JOptionPane.WARNING_MESSAGE);
                return;
            }
            int capacity = roomService.getCapacity(ch, Integer.parseInt(roomNum));
            int available = roomService.getAvailable(ch, Integer.parseInt(roomNum));
            displayRoomInfoGUI(frame,hostelService.getHostelNameByYear(ch),ch,roomNum, capacity, available);
        }
    }

    private void displayRoomInfoGUI(JFrame frame,String hostelNameByYear, int year,String roomNum, int capacity, int available) {
        StringBuilder roomInfo=new StringBuilder();
        roomInfo.append("Hostel Name        : ").append(hostelNameByYear).append("\n");
        roomInfo.append("Year               : ").append(year).append("\n");
        roomInfo.append("Room Number        : ").append(roomNum).append("\n");
        roomInfo.append("Capacity           : ").append(capacity).append("\n");
        roomInfo.append("Student Available  : ").append(available).append("\n");
        List<Student> students=getStudentsByRoomNum(year,roomNum);
        roomInfo.append("Students :").append("\n\n");
        if(!students.isEmpty()) {
            for (Student student : students) {
                roomInfo.append("Student Name        : ").append(student.getStudName()).append("\n");
                roomInfo.append("Register Number     : ").append(student.getRegNo()).append("\n\n");
            }
        }
        else roomInfo.append("No one is Present ").append("\n");
        JOptionPane.showMessageDialog(frame, roomInfo, "Room Information", JOptionPane.INFORMATION_MESSAGE);
    }
    private List<Student> getStudentsByRoomNum(int year, String roomNum) {
        return studentService.getStudentByNum(year,roomNum);
    }

    public Hostel getHostelByYear(int y){
        return hostelService.getHostelByYear(y);

    }
    public List<Hostel> getAllHostel(){
        return hostelService.gelAllHostels();
    }
    public void showHostelList(JFrame frame) {
        List<Hostel> hostels = getAllHostel();
        StringBuilder hostelInfo = new StringBuilder();
        for(Hostel hostel:hostels) {
            hostelInfo.append("           ").append(hostel.getHostelName()).append("  Hostel\n");
            hostelInfo.append("Warden Name        : ").append(hostel.getWardenName()).append("\n");
            hostelInfo.append("Watchman Name      : ").append(hostel.getWatchmanName()).append("\n");
            hostelInfo.append("Year               : ").append(hostel.getYear()).append("\n");
            hostelInfo.append("Total Rooms        : ").append(hostel.getRooms()).append("\n");
        }
        JOptionPane.showMessageDialog(frame, hostelInfo, "Hostel Information", JOptionPane.INFORMATION_MESSAGE);
    }
}
