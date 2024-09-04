package com.jana.HostelApp.repository;

import com.jana.HostelApp.model.Hostel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HostelRepository extends JpaRepository<Hostel,Integer> {
    @Query("SELECT h.rooms FROM Hostel h WHERE h.year= :year")
    public int getRooms(@Param("year") int year);
}
