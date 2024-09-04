package com.jana.HostelApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Room {
    @Id
    private int roomNo;
    private int year;
    private int capacity;
    private int studentAvailable;

    public Room() {
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getStudentAvailable() {
        return studentAvailable;
    }

    public void setStudentAvailable(int studentAvailable) {
        this.studentAvailable = studentAvailable;
    }
}
