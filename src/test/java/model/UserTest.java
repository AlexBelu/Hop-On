package model;

import org.junit.Test;
import services.UserService;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UserTest {
    public UserTest(){

    }

    @Test
    public void test() throws IOException { //pt Pilot showFlights() cand nu are niciun zbor
        UserService.loadPilotsfromFile("src/test/resources/JsonFilePilot.json");
        assertNull(UserService.getPilots().get(0).showFlights());
    }

    @Test
    public void test1() throws IOException { //pt Customer showFlights() cand nu are niciun zbor
        UserService.loadCustomersfromFile("src/test/resources/JsonFileCustomer.json");
        assertNull(UserService.getCustomers().get(0).showFlights());
    }

    @Test
    public void test2(){
        User p = new Pilot();
        p.setUsername("Vladovici Ana");
        p.setPassword("vladi93");
        p.setRole("Pilot");
        User p1 = new Pilot(p.getUsername(), p.getPassword());
        assertEquals(p, p1);
    }
}
