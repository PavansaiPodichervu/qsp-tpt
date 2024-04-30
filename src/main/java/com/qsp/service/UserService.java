package com.qsp.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qsp.ReposnseStructure.ResponseStructure;
import com.qsp.model.User;
import com.qsp.util.CourseType;
import com.qsp.util.UserRole;
import com.qsp.util.UserStatus;

public interface UserService {
	ResponseEntity<ResponseStructure<User>> findUserByEmailAndPassword(String email, String password);

	ResponseEntity<ResponseStructure<User>> findUserById(int id);

	ResponseEntity<ResponseStructure<User>> updateUser(User user);

	ResponseEntity<ResponseStructure<List<User>>> FindAll();

	ResponseEntity<ResponseStructure<User>> saveUser(User user);

	ResponseEntity<ResponseStructure<List<User>>> findUserByUserStatus(UserStatus userStatus);

	ResponseEntity<ResponseStructure<List<User>>> findUserByUserRole(UserRole userRole);

	ResponseEntity<ResponseStructure<List<User>>> findUserByCourseType(CourseType courseType);

	ResponseEntity<ResponseStructure<List<User>>> findUserByMonth(String month);

	ResponseEntity<ResponseStructure<List<User>>> findUserByMonthAndCourseType(String month, CourseType courseType);
}
