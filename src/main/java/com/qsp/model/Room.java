package com.qsp.model;

import com.qsp.util.RoomStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
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
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int roomId;
	private String roomName;
	RoomStatus roomStatus;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn
	private Floor floor;
	@OneToOne
	@JoinColumn
	private Batch batch;
}
