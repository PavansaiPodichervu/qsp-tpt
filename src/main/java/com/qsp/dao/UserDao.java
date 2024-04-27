package com.qsp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.qsp.ReposnseStructure.ResponseStructure;
import com.qsp.model.User;
import com.qsp.util.CourseType;
import com.qsp.util.UserRole;
import com.qsp.util.UserStatus;

public interface UserDao {

	User findUserByEmailAndPassword(String email, String password);

	Optional<User> findUserById(int id);

	User updateUser(User user);

	User saveUser(User user);

	List<User> findUserByUserStatus(UserStatus userStatus);

	List<User> findUserByUserRole(UserRole userRole);

	List<User> findUserByCourseType(CourseType courseType);

	List<User> findUserByMonth(String month);

	List<User> findUserByMonthAndCourseType(String month, CourseType courseType);

	List<User> findAll();
}
