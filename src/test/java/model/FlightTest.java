package model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FlightTest {
    public FlightTest(){

    }

    @Test
    public void test(){
        Flight f = new Flight();
        f.setFlightNo(1);
        f.setDeparture("Timisoara");
        f.setArrival("Londra");
        f.setDate("13-06-2020");
        f.setUsernamePilot1("Popovici Marian");
        f.setUsernamePilot2("Vladovici Ana");
        Flight f1 = new Flight(f.getFlightNo(), f.getDeparture(), f.getArrival(), f.getDate());
        f1.setUsernamePilot1(f.getUsernamePilot1());
        f1.setUsernamePilot2(f.getUsernamePilot2());
        assertEquals(f, f1);
        assertEquals(f.getNoPilots(), f1.getNoPilots());
    }
}
