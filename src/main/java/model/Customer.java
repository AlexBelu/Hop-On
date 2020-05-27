package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Flight;
import model.User;
import services.JSONWriterFlights;
import services.UserService;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Customer extends User {
    private ArrayList<Flight> boardingCard=new ArrayList<>();;
    public Customer(String username, String password)
    {
        super(username,password,"Customer");
    }
    public Customer(){}

    public void addFlight(int flightNo, ArrayList<Flight> flight_list) {
        Flight a = null;
        for (Flight flight : flight_list) {
            if (flightNo == flight.getFlightNo()) {
                this.myFlights.add(flight);
                a = flight;
            }
        }
        flight_list.remove(a);
        UserService.writeCustomers();
    }
    public ArrayList<Flight> getBoardingCard()
    {
        return boardingCard;
    }
    public void addBoardingCards(int flightNo, ArrayList<Flight> flight_list) {
        Flight a = null;
        for (Flight flight : flight_list) {
            if (flightNo == flight.getFlightNo()) {
                this.boardingCard.add(flight);
                a=flight;
            }
        }
        flight_list.remove(a);
        try {
            ObjectMapper objectMapper1 = new ObjectMapper();
            objectMapper1.writerWithDefaultPrettyPrinter().writeValue(UserService.getPathCustomer().toFile(), UserService.getCustomers());
            ObjectMapper objectMapper2 = new ObjectMapper();
            objectMapper2.writerWithDefaultPrettyPrinter().writeValue(JSONWriterFlights.getPathFlight().toFile(), UserService.getFlights());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String[] showBoardingCards() {
        if(boardingCard.size() == 0){
            return null;
        }
        else {
            String[] flight_array = new String[boardingCard.size()];
            for (int i = 0; i < boardingCard.size(); i++) {
                flight_array[i] = boardingCard.get(i).toString();
            }
            return flight_array;
        }
    }
    public String toString()
    {
        return super.toString() + " Boarding cards:" +  boardingCard;
    }
}
