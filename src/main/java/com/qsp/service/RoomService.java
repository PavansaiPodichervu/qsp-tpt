package com.qsp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qsp.ReposnseStructure.ResponseStructure;
import com.qsp.model.Floor;
import com.qsp.model.Room;
import com.qsp.util.RoomStatus;

public interface RoomService {
	public ResponseEntity<ResponseStructure<Room>> saveRoom(Room room);

	public ResponseEntity<ResponseStructure<Room>> updateRoom(Room room);

	public ResponseEntity<ResponseStructure<Room>> getRoomByid(int id);

	public ResponseEntity<ResponseStructure<Room>> getRoomByName(String name);

	public ResponseEntity<ResponseStructure<Room>> deleteRoom(Room room);

	public ResponseEntity<ResponseStructure<List<Room>>> getRoomsByFloorId(int floorId);

	ResponseEntity<ResponseStructure<List<Room>>> findByRoomStatus(RoomStatus roomStatus);
}
