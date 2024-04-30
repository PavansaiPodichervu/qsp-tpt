package com.qsp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qsp.model.Room;
import com.qsp.util.RoomStatus;

public interface RoomRepository extends JpaRepository<Room, Integer> {

	Room findByRoomName(String roomName);

	List<Room> findByFloorId(int floorId);

	List<Room> findByRoomStatus(RoomStatus roomStatus);

}
