package com.qsp.daoImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qsp.dao.RoomDao;
import com.qsp.model.Room;
import com.qsp.repository.RoomRepository;
import com.qsp.util.RoomStatus;

@Repository
public class RoomDaoImp implements RoomDao {

	@Autowired
	RoomRepository roomRepository;

	@Override
	public Room saveRoom(Room room) {
		return roomRepository.save(room);
	}

	@Override
	public Room updateRoom(Room room) {
		return roomRepository.save(room);
	}

	@Override
	public Optional<Room> getRoomByid(int id) {
		return roomRepository.findById(id);
	}

	@Override
	public Room getRoomByName(String name) {
		return roomRepository.findByRoomName(name);
	}

	@Override
	public void deleteRoom(Room room) {
		roomRepository.delete(room);
	}

	@Override
	public List<Room> getRoomsByFloorId(int floorId) {
		return roomRepository.findByFloorId(floorId);
	}

	@Override
	public List<Room> findByRoomStatus(RoomStatus roomStatus) {
		return roomRepository.findByRoomStatus(roomStatus);
	}
}
