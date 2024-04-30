package com.oop.rbs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
public class RoomController {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomRepository roomRepository;

    @PatchMapping("/rooms")
    public String editRoom(@RequestBody Room room) {
        Room existingRoom = roomRepository.findByRoomID(room.getRoomID());
        if (existingRoom == null) {
            return "{\"Error\": \"Room does not exist\"}";
        }
        int oldCapacity = existingRoom.getRoomCapacity();
        Room existingRoom1 = roomRepository.findByRoomName(room.getRoomName());
        if (existingRoom1 != null) {
            return "{\"Error\": \"Room with given name already exists\"}";
        }
        if (room.getRoomName() != null) {
            existingRoom.setRoomName(room.getRoomName());
        }

        if (room.getRoomCapacity() == null) {
            existingRoom.setRoomCapacity(oldCapacity);
        }
        else if (room.getRoomCapacity() > 0) {
            existingRoom.setRoomCapacity(room.getRoomCapacity());
        } else if (room.getRoomCapacity() <= 0) {
            return "{\"Error\": \"Invalid capacity\"}";
        }
        roomRepository.save(existingRoom);
        return "Room edited successfully";
    }

    @DeleteMapping("/rooms")
    public String deleteRoom(@RequestParam("roomID") int roomID) {
        Room existingRoom = roomRepository.findByRoomID(roomID);
        if (existingRoom == null) {
            return "{\"Error\": \"Room does not exist\"}";
        }
        roomRepository.delete(existingRoom);
        Iterable<Booking> b1 = bookingRepository.findAll();
        for (Booking b : b1) {
            if(b.getRoomID()!=roomID){
                ;
            }
            else {
                bookingRepository.delete(b);
            }
        }
        return "Room deleted successfully";
    }

    @PostMapping("/rooms")
    public String addRoom(@RequestBody Room room) {
        Room existingRoom = roomRepository.findByRoomName(room.getRoomName());
        if (existingRoom != null) {
            return "{\"Error\": \"Room already exists\"}";
        }
        if (room.getRoomCapacity() == 0 || room.getRoomCapacity() < 0) {
            return "{\"Error\": \"Invalid capacity\"}";
        }
        Room newRoom = new Room();
        newRoom.setRoomCapacity(room.getRoomCapacity());
        newRoom.setRoomName(room.getRoomName());

        roomRepository.save(newRoom);
        return "Room created successfully";
    }

}

