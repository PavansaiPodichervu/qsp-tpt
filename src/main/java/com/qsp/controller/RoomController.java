package com.qsp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.ReposnseStructure.ResponseStructure;
import com.qsp.model.Room;
import com.qsp.service.RoomService;
import com.qsp.util.RoomStatus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Room Management", description = "Endpoints for managing rooms")
@RequestMapping("/room")
public class RoomController {
	@Autowired
	private RoomService roomService;

	@PostMapping
	@Operation(description = "Save a new room", summary = "Create a new room")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Room saved successfully", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = Room.class))),
			@ApiResponse(responseCode = "400", description = "Invalid request body") })
	public ResponseEntity<ResponseStructure<Room>> saveRoom(@RequestBody Room room) {
		return roomService.saveRoom(room);
	}

	@PutMapping
	@Operation(description = "Update an existing room", summary = "Update an existing room")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Room updated successfully", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = Room.class))),
			@ApiResponse(responseCode = "400", description = "Invalid request body"),
			@ApiResponse(responseCode = "404", description = "Room not found") })
	public ResponseEntity<ResponseStructure<Room>> updateRoom(@RequestBody Room room) {
		return roomService.updateRoom(room);
	}

	@GetMapping("/{id}")
	@Operation(description = "Get room by ID", summary = "Retrieve a room by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Room found", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = Room.class))),
			@ApiResponse(responseCode = "404", description = "Room not found") })
	public ResponseEntity<ResponseStructure<Room>> getRoomById(@PathVariable int id) {
		return roomService.getRoomByid(id);
	}

	@GetMapping("/name/{name}")
	@Operation(description = "Get room by name", summary = "Retrieve a room by its name")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Room found", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = Room.class))),
			@ApiResponse(responseCode = "404", description = "Room not found") })
	public ResponseEntity<ResponseStructure<Room>> getRoomByName(@PathVariable String name) {
		return roomService.getRoomByName(name);
	}

	@DeleteMapping
	@Operation(description = "Delete a room", summary = "Delete a room")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Room deleted successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid request body"),
			@ApiResponse(responseCode = "404", description = "Room not found") })
	public ResponseEntity<ResponseStructure<Room>> deleteRoom(@RequestBody Room room) {
		return roomService.deleteRoom(room);
	}

	@GetMapping("/floor/{floorId}")
	@Operation(description = "Get rooms by floor ID", summary = "Retrieve rooms by floor ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Rooms found", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = Room.class))),
			@ApiResponse(responseCode = "404", description = "No rooms found with the specified floor ID") })
	public ResponseEntity<ResponseStructure<List<Room>>> getRoomsByFloorId(@PathVariable int floorId) {
		return roomService.getRoomsByFloorId(floorId);
	}

	@GetMapping("/roomStatus/{roomStatus}")
	@Operation(description = "Get rooms by roomStatus", summary = "Retrieve rooms by room Status")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Rooms found", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = Room.class))),
			@ApiResponse(responseCode = "404", description = "No rooms found with the specified floor ID") })
	public ResponseEntity<ResponseStructure<List<Room>>> getRoomsByFloorId(@PathVariable RoomStatus roomStatus) {
		return roomService.findByRoomStatus(roomStatus);
	}
}
