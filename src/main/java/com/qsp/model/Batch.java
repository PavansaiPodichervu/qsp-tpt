package com.qsp.model;

import java.time.LocalDate;
import java.time.LocalTime;

import com.qsp.util.BatchStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Batch {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String batchCode;
	private String subject;
	private LocalTime startTime;
	private LocalTime endTime;
	@Enumerated(EnumType.STRING)
	private BatchStatus batchStatus;
	private LocalDate startDate;
	private LocalDate endDate;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private User user;
	@OneToOne(mappedBy = "batch")
	private Room room;
}
