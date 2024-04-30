package com.qsp.serviceImp;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qsp.ReposnseStructure.ResponseStructure;
import com.qsp.dao.BatchDao;
import com.qsp.model.Batch;
import com.qsp.service.BatchService;
import com.qsp.util.BatchStatus;
import com.qsp.util.RoomStatus;

@Service
public class BatchServiceImp implements BatchService {

	@Autowired
	BatchDao batchDao;

	@Override
	public ResponseEntity<ResponseStructure<Batch>> saveBatch(Batch batch) {
		ResponseStructure<Batch> responseStructure = new ResponseStructure<>();
		try {
			batchDao.saveBatch(batch);
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("batch created successfully");
			responseStructure.setBody(batch);
			return new ResponseEntity<ResponseStructure<Batch>>(responseStructure, HttpStatus.OK);
		} catch (Exception e) {
			responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
			responseStructure.setMessage("INvalid Request Body");
			responseStructure.setBody(batch);
			return new ResponseEntity<ResponseStructure<Batch>>(responseStructure, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<Batch>> updateBatch(Batch batch) {
		ResponseStructure<Batch> responseStructure = new ResponseStructure<>();
		try {
			Optional<Batch> btch = batchDao.getBatchByid(batch.getId());
			if (btch.isEmpty()) {
				responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
				responseStructure.setMessage("batch not found with passed id " + batch.getId());
				responseStructure.setBody(batch);
				return new ResponseEntity<ResponseStructure<Batch>>(responseStructure, HttpStatus.NOT_FOUND);
			}
			batchDao.updateBatch(batch);
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("batch created successfully");
			responseStructure.setBody(batch);
			return new ResponseEntity<ResponseStructure<Batch>>(responseStructure, HttpStatus.OK);
		} catch (Exception e) {
			responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
			responseStructure.setMessage("INvalid Request Body");
			responseStructure.setBody(batch);
			return new ResponseEntity<ResponseStructure<Batch>>(responseStructure, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<Batch>> getBatchById(int id) {
		ResponseStructure<Batch> responseStructure = new ResponseStructure<>();
		Optional<Batch> btch = batchDao.getBatchByid(id);
		if (btch.isEmpty()) {
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("batch not found with passed id " + id);
			responseStructure.setBody(null);
			return new ResponseEntity<ResponseStructure<Batch>>(responseStructure, HttpStatus.NOT_FOUND);
		}
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Batch  found ");
		responseStructure.setBody(btch.get());
		return new ResponseEntity<ResponseStructure<Batch>>(responseStructure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Batch>>> getBatchByBatchStatus(BatchStatus batchStatus) {
		ResponseStructure<List<Batch>> responseStructure = new ResponseStructure<>();
		List<Batch> batches = batchDao.getBatchByBatchStatus(batchStatus);
		if (batches.isEmpty()) {
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("batch not found with passed batchStatus " + batchStatus);
			responseStructure.setBody(null);
			return new ResponseEntity<ResponseStructure<List<Batch>>>(responseStructure, HttpStatus.NOT_FOUND);
		}
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Batches  found ");
		responseStructure.setBody(batches);
		return new ResponseEntity<ResponseStructure<List<Batch>>>(responseStructure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Batch>>> getBatchByBatchStatusAndStartDate(BatchStatus batchStatus,
			LocalDate startDate) {
		ResponseStructure<List<Batch>> responseStructure = new ResponseStructure<>();
		List<Batch> batches = batchDao.getBatchByBatchStatusAndStartDate(batchStatus, startDate);
		if (batches.isEmpty()) {
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage(
					"batch not found with passed batchStatus " + batchStatus + "and start date" + startDate);
			responseStructure.setBody(null);
			return new ResponseEntity<ResponseStructure<List<Batch>>>(responseStructure, HttpStatus.NOT_FOUND);
		}
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure
				.setMessage("Batches  found with passed batchStatus " + batchStatus + "and start date" + startDate);
		responseStructure.setBody(batches);
		return new ResponseEntity<ResponseStructure<List<Batch>>>(responseStructure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Batch>>> getBatchesBetweenDates(LocalDate startDate,
			LocalDate endDate) {
		ResponseStructure<List<Batch>> responseStructure = new ResponseStructure<>();
		List<Batch> batches = batchDao.getBatchesBetweenDates(startDate, endDate);
		if (batches.isEmpty()) {
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure
					.setMessage("batch not found with passed startDate " + startDate + "and endDate" + endDate);
			responseStructure.setBody(null);
			return new ResponseEntity<ResponseStructure<List<Batch>>>(responseStructure, HttpStatus.NOT_FOUND);
		}
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Batches  found with passed startDate " + startDate + "and endDate" + endDate);
		responseStructure.setBody(batches);
		return new ResponseEntity<ResponseStructure<List<Batch>>>(responseStructure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<Batch>> deleteBatch(int id) {
		ResponseStructure<Batch> responseStructure = new ResponseStructure<>();
		Optional<Batch> btch = null;
		try {
			btch = batchDao.getBatchByid(id);
			if (btch.isEmpty()) {
				responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
				responseStructure.setMessage("batch not found with passed id " + id);
				responseStructure.setBody(null);
				return new ResponseEntity<ResponseStructure<Batch>>(responseStructure, HttpStatus.NOT_FOUND);
			}
			batchDao.deleteBatch(btch.get());
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("batch deleted successfully");
			responseStructure.setBody(btch.get());
			return new ResponseEntity<ResponseStructure<Batch>>(responseStructure, HttpStatus.OK);
		} catch (Exception e) {
			responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
			responseStructure.setMessage("INvalid Request Body");
			responseStructure.setBody(btch.get());
			return new ResponseEntity<ResponseStructure<Batch>>(responseStructure, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<Batch>> fetchByBatchCode(String batchCode) {
		ResponseStructure<Batch> responseStructure = new ResponseStructure<>();
		Batch batch = batchDao.fetchByBatchCode(batchCode);
		if (batch == null) {
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("batch not found with passed batchCode " + batchCode);
			responseStructure.setBody(null);
			return new ResponseEntity<ResponseStructure<Batch>>(responseStructure, HttpStatus.NOT_FOUND);
		}
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("Batch  found ");
		responseStructure.setBody(batch);
		return new ResponseEntity<ResponseStructure<Batch>>(responseStructure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<Batch>> closeBatch(String batchCode) {
		ResponseStructure<Batch> responseStructure = new ResponseStructure<>();
		Batch batch = null;
		try {
			batch = batchDao.fetchByBatchCode(batchCode);
			if (batch == null) {
				responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
				responseStructure.setMessage("batch not found with passed id " + batch.getId());
				responseStructure.setBody(batch);
				return new ResponseEntity<ResponseStructure<Batch>>(responseStructure, HttpStatus.NOT_FOUND);
			}
			batch.getRoom().setRoomStatus(RoomStatus.NOT_OCCUPIED);
			batchDao.updateBatch(batch);
			responseStructure.setStatusCode(HttpStatus.OK.value());
			responseStructure.setMessage("batch created successfully");
			responseStructure.setBody(batch);
			return new ResponseEntity<ResponseStructure<Batch>>(responseStructure, HttpStatus.OK);
		} catch (Exception e) {
			responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
			responseStructure.setMessage("INvalid Request Body");
			responseStructure.setBody(batch);
			return new ResponseEntity<ResponseStructure<Batch>>(responseStructure, HttpStatus.BAD_REQUEST);
		}
	}
}
