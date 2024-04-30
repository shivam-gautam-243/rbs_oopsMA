package com.oop.rbs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Table
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    Booking findByBookingID(int bookingID);
    int countByRoomIDAndDateOfBookingAndTimeFromLessThanAndTimeToLessThanAndTimeToGreaterThan(int roomID, LocalDate dateOfBooking, LocalTime timeFrom1, LocalTime timeTo1, LocalTime timeFrom2);
    int countByRoomIDAndDateOfBookingAndTimeFromGreaterThanAndTimeToGreaterThanAndTimeFromLessThan(int roomID, LocalDate dateOfBooking, LocalTime timeFrom, LocalTime timeTo1, LocalTime timeTo2);
    int countByRoomIDAndDateOfBookingAndTimeFromLessThanEqualAndTimeToGreaterThanEqual(int roomID, LocalDate dateOfBooking, LocalTime timeFrom, LocalTime timeTo);
    int countByRoomIDAndDateOfBookingAndTimeFromGreaterThanEqualAndTimeToLessThanEqual(int roomID, LocalDate dateOfBooking, LocalTime timeFrom, LocalTime timeTo);
}
