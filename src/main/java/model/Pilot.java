package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.User;
import services.JSONWriterFlights;
import services.UserService;

import java.io.IOException;
import java.util.ArrayList;

public class Pilot extends User {
    public Pilot(String username, String password) {
        super(username, password, "Pilot");
    }

    public Pilot() {
    }

    public void addFlight(int flightNo, ArrayList<Flight> flight_list){
        for(Flight flight:flight_list){
            if(flightNo == flight.getFlightNo()){
                this.myFlights.add(flight);
                if(flight.getFlightNo() == 0){
                    flight.setUsernamePilot1(this.username);
                }
                else if(flight.getFlightNo() == 1){
                    flight.setUsernamePilot2(this.username);
                    flight_list.remove(flight);
                }
            }
        }
        try {
            ObjectMapper objectMapper1 = new ObjectMapper();
            objectMapper1.writerWithDefaultPrettyPrinter().writeValue(UserService.getPathPilot().toFile(), UserService.getPilots());
            ObjectMapper objectMapper2 = new ObjectMapper();
            objectMapper2.writerWithDefaultPrettyPrinter().writeValue(JSONWriterFlights.getPathFlight().toFile(), UserService.getFlights());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

