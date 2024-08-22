package org.sm.reservationapi.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.sm.reservationapi.dao.AdminDao;
import org.sm.reservationapi.dao.BusDao;
import org.sm.reservationapi.dto.BusRequest;
import org.sm.reservationapi.dto.BusResponse;
import org.sm.reservationapi.dto.ResponseStrcture;
import org.sm.reservationapi.exception.BusNotFoundException;
import org.sm.reservationapi.model.Admin;
import org.sm.reservationapi.model.Bus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BusService {
	@Autowired
	private BusDao busDao;
	@Autowired
	private AdminDao adminDao;

	private Bus mapBus(BusRequest request) {
		return Bus.builder().name(request.getName()).departure_date_time(request.getDeparture_date_time())
				.busno(request.getBusno()).fromLoc(request.getFromLoc()).toLoc(request.getToLoc())
				.noOfSeats(request.getNoOfSeats()).typeofbus(request.getTypeofbus()).cost(request.getCost()).build();
	}

	private BusResponse mapBusResponse(Bus bus) {
		return BusResponse.builder().name(bus.getName()).departure_date_time(bus.getDeparture_date_time())
				.id(bus.getId()).toLoc(bus.getToLoc()).busno(bus.getBusno()).fromLoc(bus.getFromLoc())
				.noOfSeats(bus.getNoOfSeats()).typeofbus(bus.getTypeofbus()).cost(bus.getCost()).build();
	}

	public ResponseEntity<ResponseStrcture<BusResponse>> saveBus(BusRequest request, Integer admin_id) {
		ResponseStrcture<BusResponse> structure = new ResponseStrcture<>();
		Optional<Admin> admin = adminDao.findById(admin_id);
		if (admin.isPresent()) {
			Bus bus = mapBus(request);
//        	Admin a=admin.get();
//        	bus.setAdmin(a);
//            a.getBus().add(bus);
//            adminDao.saveAdmin(a);
			bus.setAvailableSeats(bus.getNoOfSeats());
			bus.setAdmin(admin.get());
			admin.get().getBus().add(bus);
//			busDao.save(bus); // In Admin Class i have taken CascadeType.ALL so no need to call save method explicitly.
			adminDao.saveAdmin(admin.get());
			structure.setData(mapBusResponse(bus));
			structure.setMessage("Bus Added");
			structure.setStatus(HttpStatus.CREATED.value());
			return ResponseEntity.status(HttpStatus.CREATED).body(structure);
		}
		throw new BusNotFoundException("Invalid Admin Id");
	}

	public ResponseEntity<ResponseStrcture<BusResponse>> updateBus(Integer id, BusRequest request) {
		ResponseStrcture<BusResponse> strcture = new ResponseStrcture<>();
		Optional<Bus> data = busDao.findById(id);
		if (data.isPresent()) {
			Bus bus = mapBus(request);
			bus.setId(id);
			bus.setName(request.getName());
			bus.setBusno(request.getBusno());
			bus.setDeparture_date_time(request.getDeparture_date_time());
			bus.setFromLoc(request.getFromLoc());
			bus.setToLoc(request.getToLoc());
			bus.setCost(request.getCost());
			bus.setTypeofbus(request.getTypeofbus());
			bus.setNoOfSeats(request.getNoOfSeats());
			bus.setAvailableSeats(request.getAvailableSeats());
			bus.setAdmin(request.getAdmin());
			strcture.setData(mapBusResponse(busDao.save(bus)));
			strcture.setMessage("Bus Added");
			strcture.setStatus(HttpStatus.CREATED.value());
			return ResponseEntity.status(HttpStatus.CREATED).body(strcture);
		}
		throw new BusNotFoundException("Invalid User Id");
	}

	public ResponseEntity<ResponseStrcture<BusResponse>> findByBusId(Integer id) {
		ResponseStrcture<BusResponse> strcture = new ResponseStrcture<>();
		Optional<Bus> data = busDao.findById(id);
		if (data.isPresent()) {
			strcture.setData(mapBusResponse(data.get()));
			strcture.setMessage("Bus Details.");
			strcture.setStatus(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(strcture);
		}
		throw new BusNotFoundException("Invalid User Id");
	}

	public ResponseEntity<ResponseStrcture<BusResponse>> findByBusNo(String busno) {
		ResponseStrcture<BusResponse> strcture = new ResponseStrcture<>();
		Optional<Bus> data = busDao.findByBusNo(busno);
		if (data.isPresent()) {
			strcture.setData(mapBusResponse(data.get()));
			strcture.setMessage("Bus Details.");
			strcture.setStatus(HttpStatus.FOUND.value());
			return ResponseEntity.status(HttpStatus.FOUND).body(strcture);
		}
		throw new BusNotFoundException("Invalid User Id");
	}

	public ResponseEntity<ResponseStrcture<List<Bus>>> findAllBusDetails() {
		ResponseStrcture<List<Bus>> strcture = new ResponseStrcture<>();
		List<Bus> bus = busDao.findAllBus();
		strcture.setData(bus);
		strcture.setMessage("List of All Buses");
		strcture.setStatus(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(strcture);
	}

	public ResponseEntity<ResponseStrcture<List<Bus>>> findSpecificBusDetails(String from, String to, LocalDate date) {
		ResponseStrcture<List<Bus>> strcture = new ResponseStrcture<>();
		List<Bus> bus = busDao.findByDestination(from, to, date);
		strcture.setData(bus);
		strcture.setMessage("List of All Buses From: " + from + ", To: " + to);
		strcture.setStatus(HttpStatus.OK.value());
		return ResponseEntity.status(HttpStatus.OK).body(strcture);
	}

	public ResponseEntity<ResponseStrcture<String>> deleteBus(Integer admin_id, Integer bus_id) {
		ResponseStrcture<String> strcture = new ResponseStrcture<>();
		Optional<Admin> admin = adminDao.findById(admin_id);
		if (admin.isPresent()) {
			strcture.setData(null);
			busDao.deleteBus(bus_id);
			strcture.setMessage("Bus Deleted");
			strcture.setStatus(HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).body(strcture);
		}
		throw new BusNotFoundException("Invalid User Id or admin id");
	}
}
