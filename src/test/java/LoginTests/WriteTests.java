package LoginTests;

import junit.framework.TestCase;
import model.*;
import org.apache.commons.lang3.SerializationUtils;
import services.UserService;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Date;
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
    public void test1() throws IOException {    //si Customer showFlights()
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

        String[] fli = new String[1];
        fli[0] = flight1.toString();
        assertEquals(fli, UserService.getCustomers().get(0).showFlights());
        UserService.writeCustomers(custo);
    }
   @Test
   public void test2() throws IOException { //si Pilot showFlights()
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

       String[] fli = new String[1];
       fli[0] = flight1.toString();
       assertEquals(fli, UserService.getPilots().get(0).showFlights());
       UserService.writePilots(custo);
       UserService.writeFlights(custo1);
   }

    @Test
    public void test3() throws IOException {    //pt User Service checkIn, Customer addBoardingCard, getBoardingCard, showBoardingCards
        ArrayList<Customer> custo = new ArrayList<Customer>();
        ArrayList<Customer> custom = new ArrayList<Customer>();
        custo = SerializationUtils.clone(UserService.getCustomers());
        custom = SerializationUtils.clone(UserService.getCustomers());
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String s = formatter.format(date);
        Flight flight5 = new Flight(5, "Timisoara", "Paris", s);
        ArrayList<Flight> ab = new ArrayList<>();
        ab.add(flight5);
        UserService.getCustomers().get(0).addFlight(5, ab);
        UserService.writeCustomers(UserService.getCustomers());
        UserService.loadCustomersfromFile("src/test/resources/jsonFileCustomer.json");

        ArrayList<Flight> check = new ArrayList<>();
        check.addAll(UserService.checkIn(UserService.getCustomers().get(0).getUsername()));
        UserService.getCustomers().get(0).addBoardingCards(5, check);
        String[] fli = new String[1];
        fli[0] = flight5.toString();

        custom.get(0).getMyFlights().add(flight5);
        ab.add(flight5);
        assertEquals(ab, UserService.checkIn(UserService.getCustomers().get(0).getUsername()));
        assertEquals(ab, UserService.getCustomers().get(0).getBoardingCard());
        assertEquals(fli, UserService.getCustomers().get(0).showBoardingCards());
        UserService.writeCustomers(custo);
    }


}
