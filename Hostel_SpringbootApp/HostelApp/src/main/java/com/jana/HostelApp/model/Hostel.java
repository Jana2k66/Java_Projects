package com.jana.HostelApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.stereotype.Component;

@Component
@Entity
public class Hostel {
    private String hostelName;
    private String wardenName;
    private String watchmanName;
    @Id
    private int year;
    private int rooms;

    public Hostel() {
    }

    public String getHostelName() {
        return hostelName;
    }

    public void setHostelName(String hostelName) {
        this.hostelName = hostelName;
    }

    public String getWardenName() {
        return wardenName;
    }

    public void setWardenName(String wardenName) {
        this.wardenName = wardenName;
    }



    public String getWatchmanName() {
        return watchmanName;
    }

    public void setWatchmanName(String watchmanName) {
        this.watchmanName = watchmanName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    public int getRooms() {
        return rooms;
    }
    public void setRooms(int rooms) {
        this.rooms = rooms;
    }
}
