package com.qsp.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qsp.model.Batch;
import com.qsp.util.BatchStatus;

public interface BatchRepository extends JpaRepository<Batch, Integer> {

	List<Batch> findByBatchStatus(BatchStatus batchStatus);

	List<Batch> findByBatchStatusAndStartDate(BatchStatus batchStatus, LocalDate startDate);

	@Query("SELECT b FROM Batch b WHERE b.startDate BETWEEN :startDate AND :endDate")
	List<Batch> findBatchesBetweenDates(LocalDate startDate, LocalDate endDate);

	Batch findByBatchCode(String batchCode);

}
