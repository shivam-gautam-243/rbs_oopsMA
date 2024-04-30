package com.oop.rbs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import jakarta.persistence.Table;
import java.util.UUID;

@Table
public interface RoomRepository extends CrudRepository<Room, Integer> {
    Room findByRoomName(String roomName);
    Room findByRoomID(Integer roomID);
}
