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
    public void test() throws IOException {
        UserService.loadPilotsfromFile("src/test/resources/JsonFilePilot.json");
        assertNull(UserService.getPilots().get(0).showFlights());
    }
}
