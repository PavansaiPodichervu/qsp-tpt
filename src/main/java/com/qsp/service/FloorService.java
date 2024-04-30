package com.qsp.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qsp.ReposnseStructure.ResponseStructure;
import com.qsp.model.Floor;

public interface FloorService {
	public ResponseEntity<ResponseStructure<Floor>> saveFloor(Floor floor);

	public ResponseEntity<ResponseStructure<Floor>> updateFloor(Floor floor);

	public ResponseEntity<ResponseStructure<Floor>> getFloorByid(int id);

	public ResponseEntity<ResponseStructure<Floor>> deleteFloor(Floor floor);

	public ResponseEntity<ResponseStructure<List<Floor>>> findAllFloors();

}
