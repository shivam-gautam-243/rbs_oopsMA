package com.oop.rbs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

@RestController
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @DeleteMapping("/book")
    public String deleteBooking(@RequestParam ("bookingID") int bookingID) {

        Booking existingBooking = bookingRepository.findByBookingID(bookingID);
        if (existingBooking != null) {
            bookingRepository.delete(existingBooking);
            return "Booking deleted successfully";
        }
        return "{\"Error\": \"Booking does not exist\"}";
    }

    @PatchMapping("/book")
    public String patchBooking(@RequestBody AddBook addBook) {
        int k1=0;int k2=0;
        Integer userID = addBook.getUserID();
        if (userID != null) {
            User user = userRepository.findById(addBook.getUserID()).orElse(null);
            if (user == null) {
                return "{\"Error\": \"User does not exist\"}";
            }
            else {
                ;
            }
        }
        else {
            k1=1;
        }

        Integer roomID = addBook.getRoomID();
        if (roomID != null) {
            Room room = roomRepository.findById(addBook.getRoomID()).orElse(null);
            if (room == null) {
                return "{\"Error\": \"Room does not exist\"}";
            }
            else {
                ;
            }
        }
        else {
            k2=2;
        }

        Booking booking = bookingRepository.findById(addBook.getBookingID()).orElse(null);
        if (booking == null) {
            return "{\"Error\": \"Booking does not exist\"}";
        }
        if (k2==2)
        {
            roomID= booking.getRoomID();
        }
        LocalDate dateOfBooking=addBook.getDateOfBooking();
        int x1=0;
        int x2=0;
        int x3=0;
        if (dateOfBooking==null)
        {
            x1++;
            dateOfBooking=booking.getDateOfBooking();
        }
        LocalTime timeFrom=addBook.getTimeFrom();
        if (timeFrom==null)
        {
            x2++;
            timeFrom=booking.getTimeFrom();
        }
        LocalTime timeTo=addBook.getTimeTo();
        if (timeTo==null)
        {
            x3++;
            timeTo=booking.getTimeTo();
        }

        LocalDate currentDate = LocalDate.now();
        if((dateOfBooking.isBefore(currentDate))||(dateOfBooking.isEqual(currentDate)&&timeFrom.isBefore(LocalTime.now()))) {
            return "{\"Error\": \"Invalid date/Time\"}";
        }
        if(timeFrom.isAfter(timeTo)) {
            return "{\"Error\": \"Invalid date/Time\"}";
        }

        if (x3==0 && x1==1 && x2==1)
        {
            if(addBook.getTimeTo().isBefore(booking.getTimeTo())) {
                timeTo = addBook.getTimeTo();
            }
        }
        else if (x2==0&&x3==1&&x1==1)
        {
            if(addBook.getTimeFrom().isAfter(booking.getTimeFrom())) {
                timeFrom = addBook.getTimeFrom();
            }
        }

        else {
            int count1 = bookingRepository.countByRoomIDAndDateOfBookingAndTimeFromLessThanAndTimeToLessThanAndTimeToGreaterThan(roomID, dateOfBooking, timeFrom, timeTo, timeFrom);
            int count2 = bookingRepository.countByRoomIDAndDateOfBookingAndTimeFromGreaterThanEqualAndTimeToLessThanEqual(roomID, dateOfBooking, timeFrom, timeTo);
            int count3 = bookingRepository.countByRoomIDAndDateOfBookingAndTimeFromGreaterThanAndTimeToGreaterThanAndTimeFromLessThan(roomID, dateOfBooking, timeFrom, timeTo, timeTo);
            int count4 = bookingRepository.countByRoomIDAndDateOfBookingAndTimeFromLessThanEqualAndTimeToGreaterThanEqual(roomID, dateOfBooking, timeFrom, timeTo);

            if (count3 != 0 || count2 != 0) {
                return "{\"Error\": \"Room unavailable\"}";
            }
            if (count1 != 0 || count4 != 0) {
                return "{\"Error\": \"Room unavailable\"}";
            }
        }


        if (k1==1)
        {
            userID= booking.getUserID();
        }

        String newpurpose=addBook.getPurpose();

        if (newpurpose==null){
            newpurpose=booking.getPurpose();
        }

        booking.setBooking(userID, roomID, dateOfBooking, timeFrom, timeTo, newpurpose);
        bookingRepository.save(booking);

        return "Booking modified successfully";
    }


    @PostMapping("/book")
    public String createBooking(@RequestBody AddBook addBook) {

        User user = userRepository.findById(addBook.getUserID()).orElse(null);
        if (user == null) {
            return "{\"Error\": \"User does not exist\"}";
        }

        Room room = roomRepository.findById(addBook.getRoomID()).orElse(null);
        if (room == null) {
            return "{\"Error\": \"Room does not exist\"}";
        }

        LocalDate dateOfBooking=addBook.getDateOfBooking();
        LocalTime timeFrom=addBook.getTimeFrom();
        LocalTime timeTo=addBook.getTimeTo();


        LocalDate currentDate = LocalDate.now();
        if((dateOfBooking.isBefore(currentDate))||(dateOfBooking.isEqual(currentDate)&&timeFrom.isBefore(LocalTime.now()))) {
            return "{\"Error\": \"Invalid date/time\"}";
        }

        if(timeFrom.isAfter(timeTo)) {
            return "{\"Error\": \"Invalid date/time\"}";
        }

        int roomID=addBook.getRoomID();
        int count1 = bookingRepository.countByRoomIDAndDateOfBookingAndTimeFromLessThanAndTimeToLessThanAndTimeToGreaterThan(roomID, dateOfBooking, timeFrom, timeTo, timeFrom);
        int count2 = bookingRepository.countByRoomIDAndDateOfBookingAndTimeFromGreaterThanEqualAndTimeToLessThanEqual(roomID, dateOfBooking, timeFrom, timeTo);
        int count3 = bookingRepository.countByRoomIDAndDateOfBookingAndTimeFromGreaterThanAndTimeToGreaterThanAndTimeFromLessThan(roomID, dateOfBooking, timeFrom, timeTo, timeTo);
        int count4 = bookingRepository.countByRoomIDAndDateOfBookingAndTimeFromLessThanEqualAndTimeToGreaterThanEqual(roomID, dateOfBooking, timeFrom, timeTo);

        if(count4!=0|| count2!=0) {
            return "{\"Error\": \"Room unavailable\"}";
        }
        if(count3!=0 || count1!=0){
            return "{\"Error\": \"Room unavailable\"}";
        }


        // Create booking
        Booking newbooking = new Booking(addBook.getUserID(), addBook.getRoomID(), dateOfBooking, timeFrom, timeTo, addBook.getPurpose());
        bookingRepository.save(newbooking);

        return "Booking created successfully";
    }


    @GetMapping("/upcoming")
    public ResponseEntity<?> futureRooms(@RequestParam("userID") int userID) {
        Iterable<Booking> b1 = bookingRepository.findAll();
        List<Retreiveroom> out = new ArrayList<>();
            for (Booking b : b1) {
                if (b.getUserID() == userID) {
                    Room room = roomRepository.findByRoomID(b.getRoomID());
                    if ((b.getDateOfBooking().isAfter(LocalDate.now()))||((b.getDateOfBooking().equals(LocalDate.now()))&&b.getTimeFrom().isAfter(LocalTime.now()))) {
                        Retreiveroom rr = new Retreiveroom(b.getRoomID(),b.getBookingID(),room.getRoomName(), b.getDateOfBooking(),b.getTimeFrom(),b.getTimeTo(),b.getPurpose());
                        out.add(rr);
                    }
                }
            }
            if(out.isEmpty()){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Error\": \"User does not exist\"}");
            }
            else {
                return new ResponseEntity<>(out, HttpStatus.OK);
            }
    }

    @GetMapping("/history")
    public ResponseEntity<?> pastRooms(@RequestParam("userID") int userID) {
        Iterable<Booking> b1 = bookingRepository.findAll();
        List<Retreiveroom> out = new ArrayList<>();
        for (Booking b : b1) {
            if (b.getUserID() == userID) {
                Room room = roomRepository.findByRoomID(b.getRoomID());
                if ((b.getDateOfBooking().isBefore(LocalDate.now()))||((b.getDateOfBooking().equals(LocalDate.now()))&&b.getTimeFrom().isBefore(LocalTime.now()))) {
                    Retreiveroom rr = new Retreiveroom(b.getRoomID(),b.getBookingID(),room.getRoomName(), b.getDateOfBooking(),b.getTimeFrom(),b.getTimeTo(),b.getPurpose());
                    out.add(rr);
                }
            }
        }
        if(out.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Error\": \"User does not exist\"}");
        }
        else {
            return new ResponseEntity<>(out, HttpStatus.OK);
        }
    }


    @GetMapping("/rooms")
    public ResponseEntity<?> getRooms(@RequestParam(name="capacity", defaultValue = "1") int roomCapacity) {
        if (roomCapacity>0) {
            Iterable<Room> rooms = roomRepository.findAll();
            Iterable<Booking> bookings = bookingRepository.findAll();
            List<Map<String, Object>> out = new ArrayList<>();
            for (Room room : rooms) {
                if(room.getRoomCapacity()<roomCapacity) {
                    ;
                }
                else {
                    Map<String, Object> roomObject = new HashMap<>();
                    roomObject.put("roomID", room.getRoomID());
                    roomObject.put("roomName", room.getRoomName());
                    roomObject.put("roomCapacity", room.getRoomCapacity());
                    List<Map<String, Object>> bookedList = new ArrayList<>();
                    for (Booking booking : bookings) {
                        if (booking.getRoomID() != room.getRoomID()) {
                            ;
                        }
                        else {
                            Map<String, Object> bookingObject = new HashMap<>();
                            bookingObject.put("bookingID", booking.getBookingID());
                            bookingObject.put("dateOfBooking", booking.getDateOfBooking());
                            bookingObject.put("timeFrom", booking.getTimeFrom());
                            bookingObject.put("timeTo", booking.getTimeTo());
                            bookingObject.put("purpose", booking.getPurpose());
                            Map<String, Object> user = new HashMap<>();
                            user.put("userID", booking.getUserID());
                            bookingObject.put("user", user);
                            bookedList.add(bookingObject);
                        }
                    }
                    roomObject.put("booked", bookedList);
                    out.add(roomObject);
                }
            }
            return new ResponseEntity<>(out, HttpStatus.OK);
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"Error\": \"Invalid parameters\"}");
        }
    }
}

