package com.qsp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qsp.model.User;
import com.qsp.util.CourseType;
import com.qsp.util.UserRole;
import com.qsp.util.UserStatus;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("select u from User u where u.courseType=?1")
	List<User> findByCourseType(CourseType courseType);

	@Query("select u from User u where u.role=?1")
	List<User> findByUserRole(UserRole userRole);

	@Query("select u from User u where u.userStatus=?1")
	List<User> findByUserStatus(UserStatus userStatus);

	@Query("select u from User u where u.email=?1 and u.password=?2")
	User findByEmailAndPassword(String email, String password);

}
