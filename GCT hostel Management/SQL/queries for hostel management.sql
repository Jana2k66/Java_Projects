Queries for hostel management:

 -- Database name
create database hostel;

 -- use the database
use hostel;

 -- create table with the name of hostels
create table hostels(
hostel_name varchar(100),
warden_name varchar(100), 
mess_incharge_name varchar(100), 
watchman_name varchar(100), 
student_year int, 
total_rooms int , 
room_capacity int);

 -- Insert values to the table hostels

 -- create table wth the name of student
create table student(
student_name varchar(100),
regno int,
student_year int,
dept varchar(100),
hostel_name varchar(100),
room_no int );

 -- Insert values to the table student

 -- create table wth the name of room
create table room(
hostel_name varchar(100),
room_no int primary key,
hostel_year int,
capacity int,
student_available int );

 -- Insert values to the table room 

/*
create trigger to increse the count of 
student_available if new record is 
inserted into the table student 
*/
delimiter //
create trigger countIncrese
after insert on student
for each row 
begin
update room set student_available=
student_available+1 
where student_available<room.capacity and 
room_no=new.room_no and
hostel_name=new.hostel_name;
end //

/*
create trigger to decrease the count of 
student_available if record is
deleted from the table student 
*/
delimiter //
create trigger countDecrease
after delete on student
for each row 
begin
update room set student_available=
student_available-1 
where student_available>0 and 
room_no=old.room_no and
hostel_name=old.hostel_name;
end //
