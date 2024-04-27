package com.qsp.model;

import java.time.LocalDate;
import java.util.List;

import com.qsp.util.CourseType;
import com.qsp.util.UserRole;
import com.qsp.util.UserStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String empId;
	private String address;
	@Enumerated(EnumType.STRING)
	private UserRole role;
	@Enumerated(EnumType.STRING)
	private UserStatus userStatus;
	@Enumerated(EnumType.STRING)
	private CourseType courseType;
	private LocalDate joiningDate;
	private LocalDate endDate;
	private long phone;
	private String email;
	private String password;
	private LocalDate dob;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Batch> batches;
}
