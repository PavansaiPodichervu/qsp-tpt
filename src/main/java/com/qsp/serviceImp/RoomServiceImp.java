package com.qsp.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qsp.ReposnseStructure.ResponseStructure;
import com.qsp.dao.RoomDao;
import com.qsp.model.Room;
import com.qsp.service.RoomService;
import com.qsp.util.RoomStatus;

@Service
public class RoomServiceImp implements RoomService {

	@Autowired
	private RoomDao roomDao;

	@Override
	public ResponseEntity<ResponseStructure<Room>> saveRoom(Room room) {
		ResponseStructure<Room> response = new ResponseStructure<>();
		try {
			Room savedRoom = roomDao.saveRoom(room);
			response.setBody(savedRoom);
			response.setMessage("Room saved successfully");
			response.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.setMessage("Invalid Request Body");
			response.setStatusCode(HttpStatus.BAD_REQUEST.value());
			return ResponseEntity.badRequest().body(response);
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<Room>> updateRoom(Room room) {
		ResponseStructure<Room> response = new ResponseStructure<>();
		try {
			Room updatedRoom = roomDao.updateRoom(room);
			response.setBody(updatedRoom);
			response.setMessage("Room updated successfully");
			response.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.setMessage("Invalid Request Body");
			response.setStatusCode(HttpStatus.BAD_REQUEST.value());
			return ResponseEntity.badRequest().body(response);
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<Room>> getRoomByid(int id) {
		ResponseStructure<Room> response = new ResponseStructure<>();
		Optional<Room> roomOptional = roomDao.getRoomByid(id);
		if (roomOptional.isPresent()) {
			response.setBody(roomOptional.get());
			response.setMessage("Room found");
			response.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.ok(response);
		} else {
			response.setMessage("Room not found");
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<Room>> getRoomByName(String name) {
		ResponseStructure<Room> response = new ResponseStructure<>();
		Room savedRoom = roomDao.getRoomByName(name);
		if (savedRoom != null) {
			response.setBody(savedRoom);
			response.setMessage("Room found");
			response.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.ok(response);
		} else {
			response.setMessage("Room not found");
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<Room>> deleteRoom(Room room) {
		ResponseStructure<Room> response = new ResponseStructure<>();
		try {
			roomDao.deleteRoom(room);
			response.setMessage("Room deleted successfully");
			response.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.setMessage("Invalid Request Body");
			response.setStatusCode(HttpStatus.BAD_REQUEST.value());
			return ResponseEntity.badRequest().body(response);
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Room>>> getRoomsByFloorId(int floorId) {
		ResponseStructure<List<Room>> response = new ResponseStructure<>();
		List<Room> rooms = roomDao.getRoomsByFloorId(floorId);
		if (!rooms.isEmpty()) {
			response.setBody(rooms);
			response.setMessage("Rooms found for floor ID: " + floorId);
			response.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.ok(response);
		} else {
			response.setMessage("No rooms found for floor ID: " + floorId);
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			return ResponseEntity.notFound().build();
		}
	}

	public ResponseEntity<ResponseStructure<List<Room>>> findByRoomStatus(RoomStatus roomStatus) {
		ResponseStructure<List<Room>> response = new ResponseStructure<>();
		List<Room> rooms = roomDao.findByRoomStatus(roomStatus);
		if (!rooms.isEmpty()) {
			response.setBody(rooms);
			response.setMessage("Rooms found based on : " + roomStatus);
			response.setStatusCode(HttpStatus.OK.value());
			return ResponseEntity.ok(response);
		} else {
			response.setMessage("No rooms found based on " + roomStatus);
			response.setStatusCode(HttpStatus.NOT_FOUND.value());
			return ResponseEntity.notFound().build();
		}
	}
}
