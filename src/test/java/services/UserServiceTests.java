package services;

import model.Flight;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class UserServiceTests {
    public UserServiceTests(){

    }

    @Test
    public void test() throws IOException {
        UserService.loadPilotsfromFile("src/test/resources/JsonFilePilot.json");
        UserService.loadFlightsFromFile("src/test/resources/JsonFileFlight.json");
        ArrayList<Flight> flights = new ArrayList<>();
        Flight flight1 = new Flight(1, "Timisoara", "Londra", "29-05-2020");
        Flight flight2 = new Flight(2, "Timisoara", "Berlin", "20-05-2020");
        Flight flight3 = new Flight(3, "Bucuresti", "Roma", "21-05-2020");
        Flight flight4 = new Flight(4, "Bucuresti", "Paris", "17-06-2020");
        Flight flight5 = new Flight(5, "Timisoara", "Paris", "15-06-2020");
        Flight flight6 = new Flight(6, "Berlin", "Roma", "13-06-2020");
        flights.add(flight1);
        flights.add(flight2);
        flights.add(flight3);
        flights.add(flight4);
        flights.add(flight5);
        flights.add(flight6);
        assertEquals(flights, UserService.checkAvailabilityFlight(UserService.getPilots().get(0).getUsername()));
    }
}
