package com.jana.HostelApp.repository;

import com.jana.HostelApp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    @Query("SELECT s FROM Student s WHERE s.studYear = :year AND s.roomNumber = :roomNo")
    List<Student> findStudentsByYearAndRoomNumber(@Param("year") int studYear, @Param("roomNo") String roomNumber);
}
