package com.driver.Repository;

import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class AirportRepository {

    HashMap<String, Airport> airportDb = new HashMap<>();

    HashMap<Integer, List<Integer>> flightTicketBookingDb = new HashMap<>();

    HashMap<Integer, Flight> flightDb = new HashMap<>();

    HashMap<Integer, Passenger> passengerDb = new HashMap<>();




    public void addAirport(Airport airport){
        airportDb.put(airport.getAirportName(), airport);
    }

    public String bookTicket(Integer flightId, Integer passengerId){
        Flight flight = flightDb.get(flightId);
        List<Integer> passengers = flightTicketBookingDb.get(flightId);
        int noOfPassengers = passengers.size();
        if(noOfPassengers < flight.getMaxCapacity() && !passengers.contains(passengerId)){
            passengers.add(passengerId);
            flightTicketBookingDb.put(flightId, passengers);
            return "SUCCESS";
        }
        else{
            return "FAILURE";
        }
    }

    public void addFlight(Flight flight){
        flightDb.put(flight.getFlightId(), flight);
    }

    public void addPassenger(Passenger passenger){
        passengerDb.put(passenger.getPassengerId(), passenger);
    }

    public String getLargestAirport(){
        String ans = "";
        int noOfTerminals = 0;
        for(Airport airport : airportDb.values()){
            if(airport.getNoOfTerminals() > noOfTerminals){
                ans = airport.getAirportName();
                noOfTerminals = airport.getNoOfTerminals();
            }
            else if(airport.getNoOfTerminals() == noOfTerminals){
                if(airport.getAirportName().compareTo(ans)<0){
                    ans = airport.getAirportName();
                }
            }
        }
        return ans;
    }

    public double getShortestTimeFlight(City fromCity, City toCity){
        double ans = Integer.MAX_VALUE;
        for(Flight flight : flightDb.values()){
            City from = flight.getFromCity();
            City to = flight.getToCity();
            if(fromCity.equals(from) && toCity.equals(to)){
                ans = Math.min(ans, flight.getDuration());
            }
        }
        if(ans == Integer.MAX_VALUE){
            return -1;
        }
        else{
            return ans;
        }
    }

    public int calculateFlightFare(Integer flightId){
        int ans = 0;
        List<Integer> passengers = flightTicketBookingDb.get(flightId);
        ans += 3000 + (passengers.size() * 50);
        return ans;
    }

    public int getCountOfBookingByPassenger(Integer passengerId){
        int ans = 0;
        for(Integer flight : flightTicketBookingDb.keySet()){
            List<Integer> passengers = flightTicketBookingDb.get(flight);
            for(Integer id : passengers){
                if(id == passengerId){
                    ans++;
                }
            }
        }
        return ans;
    }

    public int calculateRevenueByFlight(Integer flightId){
        int ans = 0;
        List<Integer> passengers = flightTicketBookingDb.get(flightId);
        ans += (3000 * passengers.size()) + (50 * passengers.size()) - 50;
        return ans;
    }

    public String cancelTicket(Integer flightId, Integer passengerId){
        List<Integer> passengers = flightTicketBookingDb.get(flightId);
        if(passengers == null){
            return "FAILURE";
        }
        if(passengers.contains(passengerId)){
            passengers.remove(passengerId);
            return "SUCCESS";
        }
        return "FAILURE";
    }
}
