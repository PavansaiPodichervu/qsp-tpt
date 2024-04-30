package com.qsp.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qsp.ReposnseStructure.ResponseStructure;
import com.qsp.model.Batch;
import com.qsp.util.BatchStatus;

public interface BatchService {

	ResponseEntity<ResponseStructure<Batch>> saveBatch(Batch batch);

	ResponseEntity<ResponseStructure<Batch>> updateBatch(Batch batch);

	ResponseEntity<ResponseStructure<Batch>> getBatchById(int id);

	ResponseEntity<ResponseStructure<List<Batch>>> getBatchByBatchStatus(BatchStatus batchStatus);

	ResponseEntity<ResponseStructure<List<Batch>>> getBatchByBatchStatusAndStartDate(BatchStatus batchStatus,
			LocalDate startDate);

	ResponseEntity<ResponseStructure<List<Batch>>> getBatchesBetweenDates(LocalDate startDate, LocalDate endDate);

	ResponseEntity<ResponseStructure<Batch>> deleteBatch(int id);

	ResponseEntity<ResponseStructure<Batch>> fetchByBatchCode(String batchCode);

	ResponseEntity<ResponseStructure<Batch>> closeBatch(String batchCode);
}
