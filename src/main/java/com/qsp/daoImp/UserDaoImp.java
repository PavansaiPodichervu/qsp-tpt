package com.qsp.daoImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qsp.dao.UserDao;
import com.qsp.model.User;
import com.qsp.repository.UserRepository;
import com.qsp.util.CourseType;
import com.qsp.util.UserRole;
import com.qsp.util.UserStatus;

@Repository
public class UserDaoImp implements UserDao {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User findUserByEmailAndPassword(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}

	@Override
	public Optional<User> findUserById(int id) {
		return userRepository.findById(id);
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> findUserByUserStatus(UserStatus userStatus) {
		return userRepository.findByUserStatus(userStatus);
	}

	@Override
	public List<User> findUserByUserRole(UserRole userRole) {
		return userRepository.findByUserRole(userRole);
	}

	@Override
	public List<User> findUserByCourseType(CourseType courseType) {
		return userRepository.findByCourseType(courseType);
	}

	@Override
	public List<User> findUserByMonth(String month) {
		return null;
	}

	@Override
	public List<User> findUserByMonthAndCourseType(String month, CourseType courseType) {
		return null;
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

}
