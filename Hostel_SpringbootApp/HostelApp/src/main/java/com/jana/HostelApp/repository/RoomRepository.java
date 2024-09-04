package com.jana.HostelApp.repository;

import com.jana.HostelApp.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    @Query("SELECT COALESCE(r.capacity, 0) FROM Room r WHERE r.year = :year AND r.roomNo = :roomNo")
    Optional<Integer> getCapacity(@Param("year") int year, @Param("roomNo") int rno);

    default int getCapacityOrZero(int year, int rno) {
        return getCapacity(year, rno).orElse(4);
    }
    @Query("SELECT COALESCE(r.studentAvailable, 0) FROM Room r WHERE r.year = :year AND r.roomNo = :roomNo")
    Optional<Integer> getAvailable(@Param("year") int year, @Param("roomNo") int rno);

    default int getAvailableOrZero(int year, int rno) {
        return getAvailable(year, rno).orElse(0);
    }

}
