package LoginTests;

import junit.framework.TestCase;
import model.*;
import org.apache.commons.lang3.SerializationUtils;
import services.UserService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.io.IOException;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
public class WriteTests {

    public WriteTests() throws IOException {
        UserService.loadCustomersfromFile("src/test/resources/jsonFileCustomer.json");
        UserService.loadPilotsfromFile("src/test/resources/jsonFilePilot.json");
        UserService.loadFlightsFromFile("src/test/resources/jsonFileFlight.json");
    }
    @Test
    public void test1() throws IOException {
        ArrayList<Customer> custo = new ArrayList<Customer>();
        ArrayList<Customer> custom = new ArrayList<Customer>();
        custo = SerializationUtils.clone(UserService.getCustomers());
        custom = SerializationUtils.clone(UserService.getCustomers());
        UserService.getCustomers().get(0).addFlight(1, UserService.getFlights());
        UserService.writeCustomers(UserService.getCustomers());
        UserService.loadCustomersfromFile("src/test/resources/jsonFileCustomer.json");
        Flight flight1 = new Flight(1, "Timisoara", "Londra", "29-05-2020");
        custom.get(0).getMyFlights().add(flight1);
        assertEquals(custom, UserService.getCustomers());
        UserService.writeCustomers(custo);
    }
   @Test
   public void test2() throws IOException {
       ArrayList<Pilot> custo = new ArrayList<Pilot>();
       ArrayList<Pilot> custom = new ArrayList<Pilot>();

       ArrayList<Flight> custo1 = new ArrayList<Flight>();
       ArrayList<Flight> custom1 = new ArrayList<Flight>();
       custo = SerializationUtils.clone(UserService.getPilots());
       custom = SerializationUtils.clone(UserService.getPilots());

       custo1 = SerializationUtils.clone(UserService.getFlights());
       custom1 = SerializationUtils.clone(UserService.getFlights());
       UserService.getPilots().get(0).addFlight(1, UserService.getFlights());
       UserService.writePilots(UserService.getPilots());
       UserService.writeFlights(UserService.getFlights());
       UserService.loadPilotsfromFile("src/test/resources/jsonFilePilot.json");
       Flight flight1 = new Flight(1, "Timisoara", "Londra", "29-05-2020");
       flight1.setUsernamePilot1("Popovici Marian");
       custom.get(0).getMyFlights().add(flight1);
       assertEquals(custom, UserService.getPilots());
       assertEquals(flight1,UserService.getFlight(1));
       UserService.writePilots(custo);
       UserService.writeFlights(custo1);
   }





}
