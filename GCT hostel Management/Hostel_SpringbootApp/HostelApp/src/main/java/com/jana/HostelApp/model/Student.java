package com.jana.HostelApp.model;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Entity
public class Student {
    private String studName;
    @Id
    private String regNo;
    private int studYear;
    private String studDept;

    private String roomNumber;

    public Student() {
    }

    public Student(String studName, String regNo, int studYear, String studDept, String roomNumber) {
        this.studName = studName;
        this.regNo = regNo;
        this.studYear = studYear;
        this.studDept = studDept;
        this.roomNumber = roomNumber;
    }

    public String getStudName() {
        return studName;
    }

    public void setStudName(String studName) {
        this.studName = studName;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public int getStudYear() {
        return studYear;
    }

    public void setStudYear(int studYear) {
        this.studYear = studYear;
    }

    public String getStudDept() {
        return studDept;
    }

    public void setStudDept(String studDept) {
        this.studDept = studDept;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
}