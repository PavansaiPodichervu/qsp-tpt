package com.qsp.controller;

import java.time.LocalDate;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qsp.ReposnseStructure.ResponseStructure;
import com.qsp.model.Batch;
import com.qsp.service.BatchService;
import com.qsp.util.BatchStatus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/batch")
@Tag(name = "Batch Management", description = "Endpoints for managing batches")
public class BatchController {

	@Autowired
	private BatchService batchService;

	@PostMapping
	@Operation(description = "Create a new batch", summary = "Create Batch")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Batch created successfully", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = Batch.class))),
			@ApiResponse(responseCode = "400", description = "Invalid request body") })
	public ResponseEntity<ResponseStructure<Batch>> saveBatch(@RequestBody Batch batch) {
		return batchService.saveBatch(batch);
	}

	@PutMapping
	@Operation(description = "Update an existing batch", summary = "Update Batch")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Batch updated successfully", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = Batch.class))),
			@ApiResponse(responseCode = "400", description = "Invalid request body"),
			@ApiResponse(responseCode = "404", description = "Batch not found") })
	public ResponseEntity<ResponseStructure<Batch>> updateBatch(@RequestBody Batch batch) {
		return batchService.updateBatch(batch);
	}

	@GetMapping("/{id}")
	@Operation(description = "Get batch by ID", summary = "Get Batch by ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Batch found", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = Batch.class))),
			@ApiResponse(responseCode = "404", description = "Batch not found") })
	public ResponseEntity<ResponseStructure<Batch>> getBatchById(@PathVariable int id) {
		return batchService.getBatchById(id);
	}

	@GetMapping("status/{batchStatus}")
	@Operation(description = "Get batches by status", summary = "Get Batches by Status")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Batches found", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = Batch.class))),
			@ApiResponse(responseCode = "404", description = "No batches found with the specified status") })
	public ResponseEntity<ResponseStructure<List<Batch>>> getBatchByBatchStatus(@PathVariable BatchStatus batchStatus) {
		return batchService.getBatchByBatchStatus(batchStatus);
	}

	@GetMapping("/{batchStatus}/{startDate}")
	@Operation(description = "Get batches by status and start date", summary = "Get Batches by Status and Start Date")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Batches found", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = Batch.class))),
			@ApiResponse(responseCode = "404", description = "No batches found with the specified status and start date") })
	public ResponseEntity<ResponseStructure<List<Batch>>> getBatchByBatchStatusAndStartDate(
			@PathVariable BatchStatus batchStatus, @RequestParam LocalDate startDate) {
		return batchService.getBatchByBatchStatusAndStartDate(batchStatus, startDate);
	}

	@GetMapping("/dates")
	@Operation(description = "Get batches between dates", summary = "Get Batches Between Dates")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Batches found", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ResponseStructure.class))),
			@ApiResponse(responseCode = "404", description = "No batches found between the specified dates") })
	public ResponseEntity<ResponseStructure<List<Batch>>> getBatchesBetweenDates(
			@RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate) {
		return batchService.getBatchesBetweenDates(startDate, endDate);
	}

	@DeleteMapping("/{id}")
	@Operation(description = "Delete a batch", summary = "Delete Batch")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Batch deleted successfully", content = @io.swagger.v3.oas.annotations.media.Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ResponseStructure.class))),
			@ApiResponse(responseCode = "400", description = "Invalid request body"),
			@ApiResponse(responseCode = "404", description = "Batch not found") })
	public ResponseEntity<ResponseStructure<Batch>> deleteBatch(@PathVariable int id) {
		return batchService.deleteBatch(id);
	}

	@GetMapping("/batches/code/{batchCode}")
	@Operation(summary = "Fetch batch by code", description = "Retrieves a batch by its code")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Batch found", content = @Content(mediaType = "application/json", schema = @io.swagger.v3.oas.annotations.media.Schema(implementation = ResponseStructure.class))),
			@ApiResponse(responseCode = "404", description = "Batch not found") })
	public ResponseEntity<ResponseStructure<Batch>> fetchByBatchCode(@PathVariable String batchCode) {
		return batchService.fetchByBatchCode(batchCode);
	}

	@PostMapping("/closeBatch/{batchCode}")
	@Operation(summary = "Close a batch", description = "Closes a batch by its batch code")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Batch closed successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Batch.class))),
			@ApiResponse(responseCode = "404", description = "Batch not found"),
			@ApiResponse(responseCode = "400", description = "Invalid request body") })
	public ResponseEntity<ResponseStructure<Batch>> closeBatch(@PathVariable("batchCode") String batchCode) {
		return batchService.closeBatch(batchCode);
	}

}
