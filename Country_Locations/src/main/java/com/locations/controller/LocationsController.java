package com.locations.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Locations API", description = "**APIs for managing and retrieving country and state codes.**")
public class LocationsController {

	private final Map<String, String> countryCodes = new HashMap<>() {
		{
			put("US", "United States");
			put("IN", "India");
			put("CA", "Canada");
			put("UK", "United Kingdom");
		}
	};

	private final Map<String, String> stateCodes = new HashMap<>() {
		{
			put("CA", "California");
			put("NY", "New York");
			put("TX", "Texas");
			put("WA", "Washington");
		}
	};

	
	@GetMapping("/getCountryCodes")
	@Tag(name = "Countries API", description = "**Countries codes retrieving**")
	public ResponseEntity<Map<String, String>> getCountryCodes() {
		return ResponseEntity.ok(countryCodes);
	}

	@Operation(summary = "Retrieve State Codes", description = "Fetches a map of state codes with their corresponding state names.")
	@ApiResponse(responseCode = "200", description = "Successfully retrieved the list of state codes")
	@PostMapping("/addCountryCode")
	public ResponseEntity<String> addCountryCode(@Parameter(description = "The country code to add") @RequestParam String code, @RequestParam String name) {
		if (countryCodes.containsKey(code)) {
			return ResponseEntity.badRequest().body("Country code already exists.");
		}
		countryCodes.put(code, name);
		return ResponseEntity.ok("Country added successfully.");
	}

	@PutMapping("/updateCountryCode")
	public ResponseEntity<String> updateCountryCode(@RequestParam String code, @RequestParam String name) {
		if (!countryCodes.containsKey(code)) {
			return ResponseEntity.badRequest().body("Country code does not exist.");
		}
		countryCodes.put(code, name);
		return ResponseEntity.ok("Country updated successfully.");
	}

	@DeleteMapping("/removeCountryCode")
	public ResponseEntity<String> removeCountryCode(@RequestParam String code) {
		if (!countryCodes.containsKey(code)) {
			return ResponseEntity.badRequest().body("Country code does not exist.");
		}
		countryCodes.remove(code);
		return ResponseEntity.ok("Country removed successfully.");
	}

	@GetMapping("/getStateCodes")
	public ResponseEntity<Map<String, String>> getStateCodes() {
		return ResponseEntity.ok(stateCodes);
	}

	@PostMapping("/addStateCode")
	public ResponseEntity<String> addStateCode(@RequestParam String code, @RequestParam String name) {
		if (stateCodes.containsKey(code)) {
			return ResponseEntity.badRequest().body("State code already exists.");
		}
		stateCodes.put(code, name);
		return ResponseEntity.ok("State added successfully.");
	}

	@PutMapping("/updateStateCode")
	public ResponseEntity<String> updateStateCode(@RequestParam String code, @RequestParam String name) {
		if (!stateCodes.containsKey(code)) {
			return ResponseEntity.badRequest().body("State code does not exist.");
		}
		stateCodes.put(code, name);
		return ResponseEntity.ok("State updated successfully.");
	}

	@DeleteMapping("/removeStateCode")
	public ResponseEntity<String> removeStateCode(@RequestParam String code) {
		if (!stateCodes.containsKey(code)) {
			return ResponseEntity.badRequest().body("State code does not exist.");
		}
		stateCodes.remove(code);
		return ResponseEntity.ok("State removed successfully.");
	}
}
