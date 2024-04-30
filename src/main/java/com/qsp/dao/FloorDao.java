package com.qsp.dao;

import java.util.List;
import java.util.Optional;

import com.qsp.model.Floor;

public interface FloorDao {
	public Floor saveFloor(Floor floor);

	public Floor updateFloor(Floor floor);

	public Optional<Floor> getFloorByid(int id);

	public void deleteFloor(Floor floor);

	public List<Floor> findAllFloors();

}
