package com.qsp.daoImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.qsp.dao.FloorDao;
import com.qsp.model.Floor;
import com.qsp.repository.FloorRepository;

@Repository
public class FloorDaoImp implements FloorDao {

	@Autowired
	FloorRepository floorRepository;

	@Override
	public Floor saveFloor(Floor floor) {
		return floorRepository.save(floor);
	}

	@Override
	public Floor updateFloor(Floor floor) {
		return floorRepository.save(floor);
	}

	@Override
	public Optional<Floor> getFloorByid(int id) {
		return floorRepository.findById(id);
	}

	@Override
	public void deleteFloor(Floor floor) {
		floorRepository.delete(floor);
	}

	@Override
	public List<Floor> findAllFloors() {
		return floorRepository.findAll();
	}

}
