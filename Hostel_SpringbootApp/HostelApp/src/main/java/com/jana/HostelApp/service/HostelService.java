package com.jana.HostelApp.service;

import com.jana.HostelApp.model.Hostel;
import com.jana.HostelApp.model.Student;
import com.jana.HostelApp.repository.HostelRepository;
import com.jana.HostelApp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HostelService {
    @Autowired
    HostelRepository hostelRepository;


    public List<Hostel> gelAllHostels() {
        return hostelRepository.findAll();
    }

    public Hostel getHostelByYear(int y) {
        return hostelRepository.findById(y).orElse(new Hostel());
    }

    public String getHostelNameByYear(int studYear) {
        Hostel hostel=getHostelByYear(studYear);
        return hostel.getHostelName();
    }
    public int getTotalRooms(int year){
        return hostelRepository.getRooms(year);
    }

}
