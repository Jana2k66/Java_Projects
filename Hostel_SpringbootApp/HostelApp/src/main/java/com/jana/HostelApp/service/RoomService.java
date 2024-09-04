package com.jana.HostelApp.service;

import com.jana.HostelApp.model.Room;
import com.jana.HostelApp.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepository;

    public int getCapacity(int ch, int roomNum) {
        return roomRepository.getCapacityOrZero(ch,roomNum);
    }

    public int getAvailable(int ch, int roomNum) {
        return roomRepository.getAvailableOrZero(ch,roomNum);
    }
}

