package com.oop.rbs;

import jakarta.persistence.*;

@Entity
@Table(name="rooms")
public class Room {
    @Id
    @GeneratedValue(
            strategy = GenerationType.AUTO
    )
    @Column
    private Integer roomID;
    @Column
    private String roomName;
    @Column
    private Integer roomCapacity = null;

    public Room() {
    }
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getRoomCapacity() {
        return roomCapacity;
    }
    public Integer getRoomID() {
        return roomID;
    }

    public void setRoomCapacity(Integer roomCapacity) {
        this.roomCapacity = roomCapacity;
    }

    public String getRoomName() {
        return roomName;
    }

}

