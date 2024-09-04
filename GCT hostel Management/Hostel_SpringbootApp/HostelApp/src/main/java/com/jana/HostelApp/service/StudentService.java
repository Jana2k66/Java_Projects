package com.jana.HostelApp.service;

import com.jana.HostelApp.model.Student;
import com.jana.HostelApp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public int deleteStudent(String id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return 0; // Success
        } else {
            return -1; // ID does not exist
        }
    }
    public Student getStudentById(String rno) {
        return studentRepository.findById(rno).orElse(new Student());
    }

    public List<Student> getStudentByNum(int stud_year, String roomNumber) {
        return studentRepository.findStudentsByYearAndRoomNumber(stud_year,roomNumber);
    }
}
