package com.driver.services;

import com.driver.Repository.AirportRepository;
import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class AirportService {

    AirportRepository airportRepository = new AirportRepository();

    public void addAirport(Airport airport){
        airportRepository.addAirport(airport);
    }

    public void addFlight(Flight flight){
        airportRepository.addFlight(flight);
    }

    public void addPassenger(Passenger passenger){
        airportRepository.addPassenger(passenger);
    }

    public String bookTicket(Integer flightId, Integer passengerId){
        return airportRepository.bookTicket(flightId,passengerId);
    }

    public String cancelTicket(Integer flightId, Integer passengerId){
        return airportRepository.cancelTicket(flightId, passengerId);
    }

    public String getLargestAirport(){
        return airportRepository.getLargestAirport();
    }

    public double getShortestTimeFlight(City from, City to){
        return airportRepository.getShortestTimeFlight(from,to);
    }

    public int calculateFlightFare(Integer flightId){
        return airportRepository.calculateFlightFare(flightId);
    }

    public int getCountOfBookingByPassenger(Integer passengerId){
        return airportRepository.getCountOfBookingByPassenger(passengerId);
    }

    public int calculateRevenue(Integer flightId){
        return airportRepository.calculateRevenueByFlight(flightId);
    }

    public String getAirportNameByFlight(Integer flightId){
        return airportRepository.getAirportNameByFlight(flightId);
    }
}
