package com.qsp.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.qsp.model.Batch;
import com.qsp.util.BatchStatus;

public interface BatchDao {

	public Batch saveBatch(Batch batch);

	public Batch updateBatch(Batch batch);

	public Optional<Batch> getBatchByid(int id);

	public List<Batch> getBatchByBatchStatus(BatchStatus batchStatus);

	public List<Batch> getBatchByBatchStatusAndStartDate(BatchStatus batchStatus, LocalDate startDate);

	public List<Batch> getBatchesBetweenDates(LocalDate startDate, LocalDate endDate);

	public void deleteBatch(Batch batch);

	public Batch fetchByBatchCode(String batchCode);

}
