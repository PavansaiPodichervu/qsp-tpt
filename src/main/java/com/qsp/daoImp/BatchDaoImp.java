package com.qsp.daoImp;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qsp.dao.BatchDao;
import com.qsp.model.Batch;
import com.qsp.repository.BatchRepository;
import com.qsp.util.BatchStatus;

@Repository
public class BatchDaoImp implements BatchDao {

	@Autowired
	private BatchRepository batchRepository;

	@Override
	public Batch saveBatch(Batch batch) {
		return batchRepository.save(batch);
	}

	@Override
	public Batch updateBatch(Batch batch) {
		return batchRepository.save(batch);
	}

	@Override
	public Optional<Batch> getBatchByid(int id) {
		return batchRepository.findById(null);
	}

	@Override
	public List<Batch> getBatchByBatchStatus(BatchStatus batchStatus) {
		return batchRepository.findByBatchStatus(batchStatus);
	}

	@Override
	public List<Batch> getBatchByBatchStatusAndStartDate(BatchStatus batchStatus, LocalDate startDate) {
		return batchRepository.findByBatchStatusAndStartDate(batchStatus, startDate);
	}

	@Override
	public List<Batch> getBatchesBetweenDates(LocalDate startDate, LocalDate endDate) {
		return batchRepository.findBatchesBetweenDates(startDate, endDate);
	}

	@Override
	public void deleteBatch(Batch batch) {
		batchRepository.delete(batch);
	}

	@Override
	public Batch fetchByBatchCode(String batchCode) {
		return batchRepository.findByBatchCode(batchCode);
	}

}
