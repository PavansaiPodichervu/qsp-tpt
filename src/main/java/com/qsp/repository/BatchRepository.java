package com.qsp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qsp.model.Batch;

public interface BatchRepository extends JpaRepository<Batch, Integer> {

}
