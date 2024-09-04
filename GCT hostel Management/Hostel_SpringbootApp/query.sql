DELIMITER //

CREATE TRIGGER countIncrease
AFTER INSERT ON student
FOR EACH ROW
BEGIN
    INSERT INTO room (room_no, year, student_available, capacity)
    VALUES (NEW.room_number, NEW.stud_year, 1, 4)
    ON DUPLICATE KEY UPDATE
        student_available = student_available + 1;
END //

DELIMITER ;


DELIMITER //

CREATE TRIGGER countDecrease
AFTER DELETE ON student
FOR EACH ROW
BEGIN
    UPDATE room
    SET student_available = student_available - 1
    WHERE student_available > 0
    AND room_no = OLD.room_number
    AND year = OLD.stud_year;
END //

DELIMITER ;
