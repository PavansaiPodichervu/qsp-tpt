package com.qsp.dao;

import java.util.List;
import java.util.Optional;

import com.qsp.model.Room;
import com.qsp.util.RoomStatus;

public interface RoomDao {
	public Room saveRoom(Room room);

	public Room updateRoom(Room room);

	public Optional<Room> getRoomByid(int id);

	public Room getRoomByName(String name);

	public void deleteRoom(Room room);

	public List<Room> getRoomsByFloorId(int floorId);

	List<Room> findByRoomStatus(RoomStatus roomStatus);
}
