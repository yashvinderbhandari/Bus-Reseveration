package org.sm.reservationapi.controller;

import java.time.LocalDate;
import java.util.List;

import org.sm.reservationapi.dto.BusRequest;
import org.sm.reservationapi.dto.BusResponse;
import org.sm.reservationapi.dto.ResponseStrcture;
import org.sm.reservationapi.model.Bus;
import org.sm.reservationapi.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/bus")
public class BusController {
	@Autowired
	private BusService service;

	@PostMapping("/{admin_id}")
	public ResponseEntity<ResponseStrcture<BusResponse>> save(@Valid @RequestBody BusRequest busRequest,
			@PathVariable(name = "admin_id") Integer admin_id) {
		return service.saveBus(busRequest, admin_id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ResponseStrcture<BusResponse>> update(@PathVariable(name = "id") Integer id,
			@RequestBody BusRequest busRequest) {
		return service.updateBus(id, busRequest);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ResponseStrcture<BusResponse>> findbyBusId(@PathVariable Integer id) {
		return service.findByBusId(id);
	}

	@GetMapping("/find-By-BusNo/{busno}")
	public ResponseEntity<ResponseStrcture<BusResponse>> findbyBusNo(@PathVariable(name = "busno") String busno) {
		return service.findByBusNo(busno);
	}

	@GetMapping()
	public ResponseEntity<ResponseStrcture<List<Bus>>> findAllBus() {
		return service.findAllBusDetails();
	}

	@GetMapping("/find-buses")
	public ResponseEntity<ResponseStrcture<List<Bus>>> findBus(@RequestParam String fromLoc,@RequestParam String toLoc,@RequestParam LocalDate departure_date_time) {
		return service.findSpecificBusDetails(fromLoc, toLoc, departure_date_time);
	}

	@DeleteMapping("/{adminid}/{userid}")
	public ResponseEntity<ResponseStrcture<String>> deletebus(@PathVariable Integer adminid,
			@PathVariable Integer userid) {
		return service.deleteBus(adminid, userid);
	}
}
