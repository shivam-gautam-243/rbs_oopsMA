package com.oop.rbs;

import java.time.LocalDate;
import java.time.LocalTime;

public class AddBook {

    private int bookingID;
    private Integer roomID = null;
    private String roomName;
    private Integer userID = null;
    private LocalDate dateOfBooking = null;
    private LocalTime timeFrom = null;
    private LocalTime timeTo = null;
    private String purpose;

    public int getBookingID() {
        return bookingID;
    }
    public Integer getRoomID() {
        return roomID;
    }

    public Integer getUserID() {
        return userID;
    }
    public LocalDate getDateOfBooking() {
        return dateOfBooking;
    }

    public LocalTime getTimeFrom() {
        return timeFrom;
    }

    public LocalTime getTimeTo() {
        return timeTo;
    }

    public String getPurpose() {
        return purpose;
    }


}
