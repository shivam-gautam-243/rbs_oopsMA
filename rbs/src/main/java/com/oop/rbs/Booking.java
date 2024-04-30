package com.oop.rbs;
import com.oop.rbs.*;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(name = "bookings")
public class Booking {


    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    private Integer bookingID;

    @Column
    private int userID;

    @Column
    private int roomID;

    @Column
    private LocalDate dateOfBooking;

    @Column
    private LocalTime timeFrom;

    @Column
    private LocalTime timeTo;

    @Column
    private String purpose;

    public Booking() {
    }

    public Booking(int userID, int roomID, LocalDate dateOfBooking, LocalTime timeFrom, LocalTime timeTo, String purpose) {
        this.userID = userID;
        this.roomID = roomID;
        this.dateOfBooking = dateOfBooking;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.purpose = purpose;
    }
    public void setBooking(int userID, int roomID, LocalDate dateOfBooking, LocalTime timeFrom, LocalTime timeTo, String purpose) {
        this.userID = userID;
        this.roomID = roomID;
        this.dateOfBooking = dateOfBooking;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.purpose = purpose;
    }

    public void setUserID(int userID) {
        this.userID=userID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID=roomID;
    }

    public LocalDate getDateOfBooking() {
        return dateOfBooking;
    }

    public void setTimeFrom(LocalTime timeFrom) {
        this.timeFrom = timeFrom;
    }

    public LocalTime getTimeTo() {
        return timeTo;
    }

    public void setDateOfBooking(LocalDate dateOfBooking) {
        this.dateOfBooking = dateOfBooking;
    }

    public LocalTime getTimeFrom() {
        return timeFrom;
    }


    public void setTimeTo(LocalTime timeTo) {
        this.timeTo = timeTo;
    }

    public Integer getBookingID() {
        return bookingID;
    }

    public int getUserID() {
        return userID;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}
