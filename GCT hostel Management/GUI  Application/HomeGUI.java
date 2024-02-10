package GCT_Hostel_Management;

import java.util.Scanner;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class HomeGUI {
    private static final Scanner scan = new Scanner(System.in);
    private static final HostelsDAO_GUI list = new HostelsDAO_GUI();
    private static final StudentDAO_GUI info = new StudentDAO_GUI();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Hostel Management System");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel titleLabel = new JLabel("GOVERNMENT COLLEGE OF TECHNOLOGY");
        titleLabel.setBounds(100, 20, 300, 20);
        frame.add(titleLabel);

        JLabel subTitleLabel = new JLabel("HOSTEL MANAGEMENT");
        subTitleLabel.setBounds(160, 40, 200, 20);
        frame.add(subTitleLabel);

        JLabel homePageLabel = new JLabel("HOME PAGE:");
        homePageLabel.setBounds(200, 70, 100, 20);
        frame.add(homePageLabel);

        JButton hostelInfoButton = new JButton("Hostel Information");
        hostelInfoButton.setBounds(50, 100, 200, 30);
        frame.add(hostelInfoButton);

        JButton studentInfoButton = new JButton("Student Information");
        studentInfoButton.setBounds(250, 100, 200, 30);
        frame.add(studentInfoButton);

        JButton newAdmissionButton = new JButton("New Admission");
        newAdmissionButton.setBounds(50, 150, 200, 30);
        frame.add(newAdmissionButton);

        JButton vacateHostelButton = new JButton("Vacate Hostel");
        vacateHostelButton.setBounds(250, 150, 200, 30);
        frame.add(vacateHostelButton);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(180, 200, 100, 30);
        frame.add(exitButton);

        hostelInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                            list.displayParticularHostelInfoGUI(ch);
                            break;
                        case 5:
                            list.displayHostelInfoGUI();
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "You had entered an invalid choice");
                            break;
                    }
                } catch (NumberFormatException | SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        studentInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String regNoInput = JOptionPane.showInputDialog("Enter register number of the student:");
                try {
                    int regNo = Integer.parseInt(regNoInput);
                    info.displayStudentInfoGUI(regNo);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        newAdmissionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String yearInput = JOptionPane.showInputDialog("Enter your Year:");
                String roomNoInput = JOptionPane.showInputDialog("Enter expected room number:");
                String numStudentsInput = JOptionPane.showInputDialog("How many students:");
                try {
                    int year = Integer.parseInt(yearInput);
                    int roomNo = Integer.parseInt(roomNoInput);
                    int numStudents = Integer.parseInt(numStudentsInput);
                    info.allotRoomGUI(year, roomNo, numStudents);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        vacateHostelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String regNoInput = JOptionPane.showInputDialog("Enter register number of the student:");
                String yearInput = JOptionPane.showInputDialog("Enter Year:");
                String confirm = JOptionPane.showInputDialog("Confirm to vacate from hostel (Yes/No):");
                try {
                    int regNo = Integer.parseInt(regNoInput);
                    int year = Integer.parseInt(yearInput);
                    if (confirm.equalsIgnoreCase("Yes")) {
                        info.deleteStudentGUI(regNo, year);
                    } else {
                        JOptionPane.showMessageDialog(null, "Okay...");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.setVisible(true);
    }
}
