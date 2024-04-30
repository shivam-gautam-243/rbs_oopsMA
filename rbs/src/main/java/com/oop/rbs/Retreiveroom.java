package com.oop.rbs;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class Retreiveroom {
    private int roomID;
    private int bookingID;
    private String roomName;
    private LocalDate dateOfBooking;
    private LocalTime timeFrom;
    private LocalTime timeTo;
    private String purpose;

    List<Booking> booked = new ArrayList<Booking>();

    public Retreiveroom() {
    }

    public Retreiveroom(int roomID, int bookingID, String roomName, LocalDate dateOfBooking, LocalTime timeFrom, LocalTime timeTo, String purpose) {
        this.roomID = roomID;
        this.bookingID = bookingID;
        this.roomName = roomName;
        this.dateOfBooking = dateOfBooking;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
        this.purpose = purpose;

    }

    public int getRoomID() {
        return roomID;
    }
    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getBookingID() {
        return bookingID;
    }
    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }
    public String getRoomName() {
        return roomName;
    }
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
    public LocalDate getDateOfBooking() {
        return dateOfBooking;
    }
    public void setDateOfBooking(LocalDate dateOfBooking) {
        this.dateOfBooking = dateOfBooking;
    }
    public LocalTime getTimeFrom() {
        return timeFrom;
    }
    public void setTimeFrom(LocalTime timeFrom) {
        this.timeFrom = timeFrom;
    }
    public LocalTime getTimeTo() {
        return timeTo;
    }
    public void setTimeTo(LocalTime timeTo) {
        this.timeTo = timeTo;
    }
    public String getPurpose() {
        return purpose;
    }
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }



}
