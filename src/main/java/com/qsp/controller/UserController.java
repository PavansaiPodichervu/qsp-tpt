package com.qsp.controller;

//
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.ReposnseStructure.ResponseStructure;
import com.qsp.model.User;
import com.qsp.service.UserService;
import com.qsp.util.CourseType;
import com.qsp.util.UserRole;
import com.qsp.util.UserStatus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

//
@RestController
@RequestMapping(value = "/user")
@Tag(name = "UserController", description = "Endpoints for managing users")
public class UserController {
	@Autowired
	private UserService userService;

//	http://localhost:8080/swagger-ui/index.html#/

	/**
	 * save user details.
	 * 
	 * @param user The user details to save.
	 * @return ResponseEntity containing the saved user details.
	 */
	@PostMapping
	@Operation(description = "Save user details into the system.", summary = "Save User")
	@ApiResponses({
			@ApiResponse(responseCode = "201", description = "User saved successfully.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
			@ApiResponse(responseCode = "400", description = "User unable to save", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))) })
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}

	/**
	 * Update user details.
	 * 
	 * @param user The user details to update.
	 * @return ResponseEntity containing the updated user details.
	 */
	@PutMapping
	@Operation(summary = "Update user details", description = "Updates user details in the system.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "User details updated successfully.", content = @Content),
			@ApiResponse(responseCode = "400", description = "Invalid user details provided.", content = @Content),
			@ApiResponse(responseCode = "401", description = "Unauthorized access.", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content) })
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user) {
		return userService.saveUser(user);
	}

//	@PostMapping("/{email}/{password}")
//	@Operation(description = "Find a user by email and password.", summary = "Find User by Email and Password")
//	@ApiResponses({
//			@ApiResponse(responseCode = "200", description = "User returned successfully.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
//			@ApiResponse(responseCode = "404", description = "UserNotFound", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))) })
//	ResponseEntity<ResponseStructure<User>> findUserByEmailAndPassword(@PathVariable String email,
//			@PathVariable String password) {
//		return userService.findUserByEmailAndPassword(email, password);
//	}

	@GetMapping("/userrole/{userRole}")
	@Operation(summary = "Find users by user role", description = "Retrieves users by their role")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Users found", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = List.class))),
			@ApiResponse(responseCode = "404", description = "No users found with the specified role") })
	public ResponseEntity<ResponseStructure<List<User>>> findUserByUserRole(@PathVariable UserRole userRole) {
		return userService.findUserByUserRole(userRole);
	}

	@GetMapping("/All")
	@Operation(summary = "Find all users", description = "Retrieves all users")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Users found", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = List.class))),
			@ApiResponse(responseCode = "404", description = "No users found") })
	public ResponseEntity<ResponseStructure<List<User>>> findAll() {
		return userService.FindAll();
	}

	@GetMapping("/{id}")
	@Operation(summary = "Find user by ID", description = "Retrieves a user by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "User found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
			@ApiResponse(responseCode = "404", description = "User not found") })
	public ResponseEntity<ResponseStructure<User>> findUserById(@PathVariable("id") int id) {
		return userService.findUserById(id);
	}

	@GetMapping("/ByCourseType/{courseType}")
	@Operation(summary = "Find users by course type", description = "Retrieves users by their course type")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Users found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))),
			@ApiResponse(responseCode = "404", description = "No users found for the given course type") })
	public ResponseEntity<ResponseStructure<List<User>>> findUserByCourseType(
			@PathVariable("courseType") CourseType courseType) {
		return userService.findUserByCourseType(courseType);
	}

	@GetMapping("/ByMonth/{month}")
	@Operation(summary = "Find users by month", description = "Retrieves users by their month of registration")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Users found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))),
			@ApiResponse(responseCode = "404", description = "No users found for the given month") })
	public ResponseEntity<ResponseStructure<List<User>>> findUserByMonth(@PathVariable("month") String month) {
		return userService.findUserByMonth(month);
	}

	@GetMapping("/ByUserStatus/{userStatus}")
	@Operation(summary = "Find users by user status", description = "Retrieves users by their status")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Users found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))),
			@ApiResponse(responseCode = "404", description = "No users found for the given status") })
	public ResponseEntity<ResponseStructure<List<User>>> findUserByUserStatus(
			@PathVariable("userStatus") UserStatus userStatus) {
		return userService.findUserByUserStatus(userStatus);
	}

	@GetMapping("/ByMonthAndCourseType/{month}/{courseType}")
	@Operation(summary = "Find users by month and course type", description = "Retrieves users by their month of registration and course type")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Users found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = List.class))),
			@ApiResponse(responseCode = "404", description = "No users found for the given month and course type") })
	public ResponseEntity<ResponseStructure<List<User>>> findUserByMonthAndCourseType(
			@PathVariable("month") String month, @PathVariable("courseType") CourseType courseType) {
		return userService.findUserByMonthAndCourseType(month, courseType);
	}

	@PostMapping("/{email}/{password}")
	@Operation(summary = "Find user by email and password", description = "Retrieves a user by their email and password")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "User found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
			@ApiResponse(responseCode = "404", description = "User not found") })
	public ResponseEntity<ResponseStructure<User>> findUserByEmailAndPassword(@PathVariable("email") String email,
			@PathVariable("password") String password) {
		return userService.findUserByEmailAndPassword(email, password);
	}

}
