package com.qsp.controller;

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
import com.qsp.model.Floor;
import com.qsp.service.FloorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/floor")
@Tag(name = "Floor Management", description = "Endpoints for managing floors")
public class FloorController {

	@Autowired
	private FloorService floorService;

	@PostMapping
	@Operation(description = "Save a new floor", summary = "Create a new floor")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Floor saved successfully", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = Floor.class))),
			@ApiResponse(responseCode = "400", description = "Invalid request body") })
	public ResponseEntity<ResponseStructure<Floor>> saveFloor(@RequestBody Floor floor) {
		return floorService.saveFloor(floor);
	}

	@PutMapping
	@Operation(description = "Update an existing floor", summary = "Update an existing floor")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Floor updated successfully", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = Floor.class))),
			@ApiResponse(responseCode = "400", description = "Invalid request body"),
			@ApiResponse(responseCode = "404", description = "Floor not found") })
	public ResponseEntity<ResponseStructure<Floor>> updateFloor(@RequestBody Floor floor) {
		return floorService.updateFloor(floor);
	}

	@GetMapping("/{id}")
	@Operation(description = "Get floor by ID", summary = "Retrieve a floor by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Floor found", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = Floor.class))),
			@ApiResponse(responseCode = "404", description = "Floor not found") })
	public ResponseEntity<ResponseStructure<Floor>> getFloorById(@PathVariable int id) {
		return floorService.getFloorByid(id);
	}

	@DeleteMapping
	@Operation(description = "Delete a floor", summary = "Delete a floor")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Floor deleted successfully"),
			@ApiResponse(responseCode = "400", description = "Invalid request body") })
	public ResponseEntity<ResponseStructure<Floor>> deleteFloor(@RequestBody Floor floor) {
		return floorService.deleteFloor(floor);
	}
}
