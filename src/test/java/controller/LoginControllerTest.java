package controller;

import exceptions.IncorrectPasswordException;
import exceptions.IncorrectUsernameException;
import org.junit.Test;
import services.UserService;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class LoginControllerTest {
    LoginController cont= new LoginController(new view.LoginView());
    public LoginControllerTest() throws IOException {
        UserService.loadPilotsfromFile("src/test/resources/JsonFilePilot.json");
        UserService.loadCustomersfromFile("src/test/resources/JsonFileCustomer.json");

    }
    @Test
    public void test1() throws IncorrectPasswordException, IncorrectUsernameException {

        assertEquals(true,cont.checkAvailability("Fodor Razvan","lotifan"));

    }
    @Test
    public void test2() throws IncorrectPasswordException, IncorrectUsernameException {
        assertEquals(true, cont.checkAvailability("Vladovici Ana", "vladi93"));
    }
    @Test
    public void test3() {
        assertEquals(false,cont.checkAvailability("Vladovici Anisoara", "vladi93"));
    }
    @Test
    public void test4()  {
        assertEquals(false,cont.checkAvailability("Vladovici Ana", "vladi94"));

    }
    @Test
    public void test10()  {
        assertEquals(false,cont.checkAvailability("Vladovici Anisoara", "vladi93"));

    }
    @Test
    public void test5() throws IncorrectPasswordException, IncorrectUsernameException {
        assertEquals(true,cont.checkAvailability("Vancea Roxana","mamaNatura"));



    }
    @Test
    public void test6() throws IncorrectPasswordException, IncorrectUsernameException {
        assertEquals(true,cont.checkAvailability("Popovici Marian","popmaio99"));


    }
}
