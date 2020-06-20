package services;

import model.Customer;
import model.Flight;
import model.Pilot;
import model.User;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

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
        Flight flight5 = new Flight(5, "Timisoara", "Paris", "20-06-2020");
        Flight flight6 = new Flight(6, "Berlin", "Roma", "13-06-2020");
        flights.add(flight1);
        flights.add(flight2);
        flights.add(flight3);
        flights.add(flight4);
        flights.add(flight5);
        flights.add(flight6);
        assertEquals(flights, UserService.checkAvailabilityFlight(UserService.getPilots().get(0).getUsername()));
    }

    @Test
    public void test1() throws IOException {
        UserService.loadCustomersfromFile("src/test/resources/JsonFileCustomer.json");
        UserService.loadFlightsFromFile("src/test/resources/JsonFileFlight.json");
        ArrayList<Flight> flights = new ArrayList<>();
        Flight flight1 = new Flight(1, "Timisoara", "Londra", "29-05-2020");
        flights.add(flight1);
        assertEquals(flights, UserService.checkAvailableFlightsUser(UserService.getCustomers().get(0).getUsername(), "Timisoara", "Londra"));
    }

    @Test
    public void test2() throws IOException {
        UserService.loadPilotsfromFile("src/test/resources/JsonFilePilot.json");
        Pilot pilot = new Pilot("Vladovici Ana", JSONWriter.encodePassword("Vladovici Ana", "vladi93"));
        assertEquals(pilot, UserService.findPilot(UserService.getPilots().get(1).getUsername()));
    }

    @Test
    public void test3() throws IOException {
        UserService.loadCustomersfromFile("src/test/resources/JsonFileCustomer.json");
        Customer customer = new Customer ("Vancea Roxana", JSONWriter.encodePassword("Vancea Roxana", "mamaNatura"));
        assertEquals(customer, UserService.findCustomer(UserService.getCustomers().get(0).getUsername()));
    }

    @Test
    public void test4() throws IOException {
        UserService.loadFlightsFromFile("src/test/resources/JsonFileFlight.json");
        Flight flight = new Flight(1, "Timisoara", "Londra", "29-05-2020");
        assertEquals(flight, UserService.getFlight(1));
    }

    @Test
    public void test5(){
        ArrayList<Flight> flights = new ArrayList<>();
        Flight flight1 = new Flight(1, "Timisoara", "Londra", "29-05-2020");
        Flight flight2 = new Flight(2, "Timisoara", "Berlin", "20-05-2020");
        Flight flight3 = new Flight(3, "Bucuresti", "Roma", "21-05-2020");
        flights.add(flight1);
        flights.add(flight2);
        flights.add(flight3);
        String[] f = new String[3];
        f[0] = flight1.toString();
        f[1] = flight2.toString();
        f[2] = flight3.toString();
        assertEquals(f, UserService.listToArray(flights));
    }

    @Test
    public void test6(){
        ArrayList<Flight> flights = new ArrayList<>();
        assertNull(UserService.listToArray(flights));
    }

    @Test
    public void testPasswordEncoding() {
        assertNotEquals("vladi93", JSONWriter.encodePassword("Vladovici Ana", "vladi93"));
    }
}
