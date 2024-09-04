package com.jana.HostelApp;

import com.jana.HostelApp.controller.HostelController;
import com.jana.HostelApp.controller.StudentController;
import com.jana.HostelApp.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

@SpringBootApplication
public class HostelApp extends JFrame {

	private final StudentController studentController;
	private final HostelController hostelController;

	@Autowired
	public HostelApp(StudentController studentController,HostelController hostelController) {
		this.studentController = studentController;
		this.hostelController = hostelController;
		initialize();
	}

	private void initialize() {
		setTitle("Hostel Management System");
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		JLabel titleLabel = new JLabel("GOVERNMENT COLLEGE OF TECHNOLOGY");
		titleLabel.setBounds(100, 20, 300, 20);
		add(titleLabel);

		JLabel subTitleLabel = new JLabel("HOSTEL MANAGEMENT");
		subTitleLabel.setBounds(160, 40, 200, 20);
		add(subTitleLabel);

		JLabel homePageLabel = new JLabel("HOME PAGE:");
		homePageLabel.setBounds(200, 70, 100, 20);
		add(homePageLabel);

		JButton hostelInfoButton = new JButton("Hostel Information");
		hostelInfoButton.setBounds(50, 100, 200, 30);
		add(hostelInfoButton);
		hostelInfoButton.addActionListener(e ->{
			hostelController.hostelDetails(this);
		});

		JButton addButton = new JButton("New Admission");
		addButton.setBounds(50, 150, 200, 30);
		add(addButton);
		addButton.addActionListener(e -> {
			studentController.newAdmission(this);
		});

		JButton listButton = new JButton("Student Information");
		listButton.setBounds(250, 100, 200, 30);
		add(listButton);

		listButton.addActionListener(e -> {
			String rno = JOptionPane.showInputDialog(this, "Enter Register Number :");
			if(rno.isEmpty()) JOptionPane.showMessageDialog(this, "You not entered register number", "Student Information", JOptionPane.WARNING_MESSAGE);
			else studentController.showStudentDetail(this,rno.trim().toLowerCase());
		});

		JButton deleteButton = new JButton("Vacate Hostel");
		deleteButton.setBounds(250, 150, 200, 30);
		add(deleteButton);
		deleteButton.addActionListener(e -> {
			String id = JOptionPane.showInputDialog(this, "Enter Student ID to Delete:","Vacate Hostel", JOptionPane.WARNING_MESSAGE);
			if(id.isEmpty())   {
				JOptionPane.showMessageDialog(null, "Enter Register Number!","Missing Details",JOptionPane.WARNING_MESSAGE);
			}
			else{
			studentController.deleteStudent(this,id.trim().toLowerCase());
			}
		});

		JButton exitButton = new JButton("Exit");
		exitButton.setBounds(180, 200, 100, 30);
		add(exitButton);
		exitButton.addActionListener(e -> System.exit(0));
	}
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(HostelApp.class, args);
		EventQueue.invokeLater(() -> {
			HostelApp frame = context.getBean(HostelApp.class);
			frame.setVisible(true);
		});
	}
}
