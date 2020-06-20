package LoginTests;

import junit.framework.TestCase;
import model.Customer;
import model.Flight;
import model.Pilot;
import org.junit.Test;
import org.junit.runners.JUnit4;
import services.*;
import java.io.IOException;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;


public class LoadFromFileTest{
        public LoadFromFileTest() throws IOException {


        }
    @Test
        public void test() throws IOException {
            UserService.loadCustomersfromFile("src/test/resources/JsonFileCustomer.json");
            ArrayList<Customer> users = new ArrayList<Customer>();
            Customer customer1 = new Customer("Vancea Roxana", JsonWriterTest.encodePassword("Vancea Roxana", "mamaNatura"));
            Customer customer2 = new Customer("Fodor Razvan", JsonWriterTest.encodePassword("Fodor Razvan", "lotifan"));
            users.add(customer1);
            users.add(customer2);
            assertEquals(users, UserService.getCustomers());
        }
    @Test
        public void test1() throws IOException {
            UserService.loadPilotsfromFile("src/test/resources/JsonFilePilot.json");
            ArrayList<Pilot> users1 = new ArrayList<Pilot>();
            Pilot pilot1 = new Pilot("Popovici Marian", JsonWriterTest.encodePassword("Popovici Marian", "popmaio99"));
            Pilot pilot2 = new Pilot("Vladovici Ana", JsonWriterTest.encodePassword("Vladovici Ana", "vladi93"));
            users1.add(pilot1);
            users1.add(pilot2);
            assertEquals(users1, UserService.getPilots());
        }
    @Test
       public void test2() throws IOException {
           UserService.loadFlightsFromFile("src/test/resources/JsonFileFlight.json");
           ArrayList<Flight> tflights = new ArrayList<>();
           Flight flight1 = new Flight(1, "Timisoara", "Londra", "29-05-2020");
           Flight flight2 = new Flight(2, "Timisoara", "Berlin", "20-05-2020");
           Flight flight3 = new Flight(3, "Bucuresti", "Roma", "21-05-2020");
           Flight flight4 = new Flight(4, "Bucuresti", "Paris", "17-06-2020");
           Flight flight5 = new Flight(5, "Timisoara", "Paris", "20-06-2020");
           Flight flight6 = new Flight(6, "Berlin", "Roma", "13-06-2020");
           tflights.add(flight1);
           tflights.add(flight2);
           tflights.add(flight3);
           tflights.add(flight4);
           tflights.add(flight5);
           tflights.add(flight6);
           assertEquals(tflights,UserService.getFlights());
       }
       @Test(expected=IOException.class)
       public void test3 () throws IOException {
            UserService.loadFlightsFromFile("src/test/resources/nimic.json");

       }
    @Test(expected=IOException.class)
    public void test4 () throws IOException {
        UserService.loadCustomersfromFile("src/test/resources/nimic.json");

    }
    @Test(expected=IOException.class)
    public void test5 () throws IOException {
        UserService.loadPilotsfromFile("src/test/resources/nimic.json");

    }
    @Test
    public void test6() throws  IOException{
            UserService.loadCustomersfromFile("nu avem path");
    }
    @Test
    public void test7() throws  IOException{
        UserService.loadPilotsfromFile("nu avem path");
    }
    @Test
    public void test8() throws  IOException{
        UserService.loadFlightsFromFile("nu avem path");
    }



}
