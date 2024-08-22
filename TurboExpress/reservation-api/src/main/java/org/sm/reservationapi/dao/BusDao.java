package org.sm.reservationapi.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.sm.reservationapi.model.Bus;
import org.sm.reservationapi.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BusDao {
    @Autowired
    private BusRepository repository;

    public Bus save(Bus bus) {
        return repository.save(bus);
    }

    public Optional<Bus> findById(Integer id) {
        return repository.findById(id);
    }

    public Optional<Bus> findByBusNo(String busno) {
        return repository.findByBusno(busno);
    }
    public List<Bus> findAllBus() {
    	return repository.findAll();
    }
    public List<Bus> findByDestination(String from, String to, LocalDate date){
    	return repository.findByDestination(from, to, date);
    }
    public void deleteBus(Integer bus_id) {
    	repository.deleteById(bus_id);
    }
}
