package com.qsp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.ReposnseStructure.ResponseStructure;
import com.qsp.model.User;
import com.qsp.service.UserService;
import com.qsp.util.UserRole;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
//@RequestMapping(name = "/user")
@Tag(name = "UserController", description = "Endpoints for managing users")
@CrossOrigin(origins = { "http://localhost:3001", "http://localhost:3000" })
public class UserController {
	@Autowired
	private UserService userService;

	/**
	 * save user details.
	 * 
	 * @param user The user details to save.
	 * @return ResponseEntity containing the saved user details.
	 */
	@PostMapping(value = "/save")
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
	@PutMapping("/update")
	@Operation(summary = "Update user details", description = "Updates user details in the system.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "User details updated successfully.", content = @Content),
			@ApiResponse(responseCode = "400", description = "Invalid user details provided.", content = @Content),
			@ApiResponse(responseCode = "401", description = "Unauthorized access.", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content) })
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user) {
		return userService.saveUser(user);
	}

	@PostMapping(value = "{email}/{password}")
	@Operation(description = "Find a user by email and password.", summary = "Find User by Email and Password")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "User returned successfully.", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
			@ApiResponse(responseCode = "404", description = "UserNotFound", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))) })
	ResponseEntity<ResponseStructure<User>> findUserByEmailAndPassword(@PathVariable String email,
			@PathVariable String password) {
		return userService.findUserByEmailAndPassword(email, password);
	}

	@GetMapping(value = "/userrole/{userRole}")
	ResponseEntity<ResponseStructure<List<User>>> findUserByUserRole(@PathVariable UserRole userRole) {
		return userService.findUserByUserRole(userRole);
	}

	@GetMapping(value = "/All")
	ResponseEntity<ResponseStructure<List<User>>> findAll() {
		return userService.FindAll();
	}
}
