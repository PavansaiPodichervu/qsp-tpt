package com.qsp.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qsp.ReposnseStructure.ResponseStructure;
import com.qsp.dao.FloorDao;
import com.qsp.model.Floor;
import com.qsp.service.FloorService;

@Service
public class FloorServiceImp implements FloorService {

	@Autowired
	private FloorDao floorDao;

	@Override
	public ResponseEntity<ResponseStructure<Floor>> saveFloor(Floor floor) {
		ResponseStructure<Floor> responseStructure = new ResponseStructure<>();
		try {
			floorDao.saveFloor(floor);
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Floor created successfully");
			responseStructure.setBody(floor);
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} catch (Exception e) {
			responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
			responseStructure.setMessage("Invalid Request Body");
			responseStructure.setBody(floor);
			return new ResponseEntity<>(responseStructure, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<Floor>> updateFloor(Floor floor) {
		ResponseStructure<Floor> responseStructure = new ResponseStructure<>();
		try {
			Optional<Floor> existingFloor = floorDao.getFloorByid(floor.getId());
			if (existingFloor.isEmpty()) {
				responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
				responseStructure.setMessage("Floor not found with id " + floor.getId());
				responseStructure.setBody(floor);
				return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
			}
			floorDao.updateFloor(floor);
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("Floor updated successfully");
			responseStructure.setBody(floor);
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} catch (Exception e) {
			responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
			responseStructure.setMessage("Invalid Request Body");
			responseStructure.setBody(floor);
			return new ResponseEntity<>(responseStructure, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<Floor>> getFloorByid(int id) {
		ResponseStructure<Floor> responseStructure = new ResponseStructure<>();
		Optional<Floor> floor = floorDao.getFloorByid(id);
		if (floor.isEmpty()) {
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Floor not found with id " + id);
			responseStructure.setBody(null);
			return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
		}
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Floor found");
		responseStructure.setBody(floor.get());
		return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<Floor>> deleteFloor(Floor floor) {
		ResponseStructure<Floor> responseStructure = new ResponseStructure<>();
		Optional<Floor> existingFloor = floorDao.getFloorByid(floor.getId());
		if (existingFloor.isEmpty()) {
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("Floor not found with id " + floor.getId());
			responseStructure.setBody(null);
			return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
		}
		floorDao.deleteFloor(existingFloor.get());
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Floor deleted successfully");
		responseStructure.setBody(existingFloor.get());
		return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Floor>>> findAllFloors() {
		ResponseStructure<List<Floor>> responseStructure = new ResponseStructure<>();
		List<Floor> floors = floorDao.findAllFloors();
		if (floors.isEmpty()) {
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("No floors found");
			responseStructure.setBody(null);
			return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
		}
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Floors found");
		responseStructure.setBody(floors);
		return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}

}
