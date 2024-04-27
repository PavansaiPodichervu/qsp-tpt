package com.qsp.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qsp.ReposnseStructure.ResponseStructure;
import com.qsp.dao.UserDao;
import com.qsp.model.User;
import com.qsp.service.UserService;
import com.qsp.util.CourseType;
import com.qsp.util.UserRole;
import com.qsp.util.UserStatus;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public ResponseEntity<ResponseStructure<User>> findUserByEmailAndPassword(String email, String password) {
		ResponseStructure<User> responseStructure = new ResponseStructure<>();
		User user1 = null;
		try {
			user1 = userDao.findUserByEmailAndPassword(email, password);

			if (user1 != null) {
				responseStructure.setStatusCode(HttpStatus.OK.value());
				responseStructure.setMessage("user returned successfully");
				responseStructure.setBody(user1);
				return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.OK);
			}
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("user not Found successfully");
			responseStructure.setBody(user1);
			return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
			responseStructure.setMessage("user account unable to create");
			responseStructure.setBody(user1);
			return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.BAD_REQUEST);
		}

	}

	@Override
	public ResponseEntity<ResponseStructure<User>> findUserById(int id) {

		ResponseStructure<User> responseStructure = new ResponseStructure<>();
		User user1 = null;
		try {
			user1 = userDao.findUserById(id).get();
			if (user1 != null) {
				responseStructure.setStatusCode(HttpStatus.FOUND.value());
				responseStructure.setMessage("user returned successfully");
				responseStructure.setBody(user1);
				return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.FOUND);
			}
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("user not Found successfully");
			responseStructure.setBody(user1);
			return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
			responseStructure.setMessage("user account unable to create");
			responseStructure.setBody(user1);
			return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<User>> updateUser(User user) {
		userDao.updateUser(user);
		return null;
	}

	@Override
	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
		User user1 = userDao.saveUser(user);
		ResponseStructure<User> responseStructure = new ResponseStructure<>();
		if (user1 != null) {
			user.setEmpId("qsp" + user.getId());
			responseStructure.setStatusCode(HttpStatus.CREATED.value());
			responseStructure.setMessage("user account created successfully");
			responseStructure.setBody(user);
			return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.CREATED);
		}
		responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		responseStructure.setMessage("user account unable to create");
		responseStructure.setBody(user);
		return new ResponseEntity<ResponseStructure<User>>(responseStructure, HttpStatus.BAD_REQUEST);
	}

	@Override
	public ResponseEntity<ResponseStructure<List<User>>> findUserByUserStatus(UserStatus userStatus) {
		ResponseStructure<List<User>> responseStructure = new ResponseStructure<>();
		List<User> users = null;
		try {
			users = userDao.findUserByUserStatus(userStatus);
			if (users != null) {
				responseStructure.setStatusCode(HttpStatus.FOUND.value());
				responseStructure.setMessage("user returned successfully");
				responseStructure.setBody(users);
				return new ResponseEntity<ResponseStructure<List<User>>>(responseStructure, HttpStatus.FOUND);
			}
			responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("user not Found successfully");
			responseStructure.setBody(users);
			return new ResponseEntity<ResponseStructure<List<User>>>(responseStructure, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
			responseStructure.setMessage("user account unable to create");
			responseStructure.setBody(users);
			return new ResponseEntity<ResponseStructure<List<User>>>(responseStructure, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<List<User>>> findUserByUserRole(UserRole userRole) {
		ResponseStructure<List<User>> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("user returned successfully");
		responseStructure.setBody(userDao.findUserByUserRole(userRole));
		return new ResponseEntity<ResponseStructure<List<User>>>(responseStructure, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<List<User>>> findUserByCourseType(CourseType courseType) {
		userDao.findUserByCourseType(courseType);
		return null;
	}

	@Override
	public ResponseEntity<ResponseStructure<List<User>>> findUserByMonth(String month) {
		userDao.findUserByMonth(month);
		return null;
	}

	@Override
	public ResponseEntity<ResponseStructure<List<User>>> findUserByMonthAndCourseType(String month,
			CourseType courseType) {
		userDao.findUserByMonthAndCourseType(month, courseType);
		return null;
	}

	@Override
	public ResponseEntity<ResponseStructure<List<User>>> FindAll() {
		ResponseStructure<List<User>> responseStructure = new ResponseStructure<>();
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage("user returned successfully");
		responseStructure.setBody(userDao.findAll());
		return new ResponseEntity<ResponseStructure<List<User>>>(responseStructure, HttpStatus.OK);
	}

}
