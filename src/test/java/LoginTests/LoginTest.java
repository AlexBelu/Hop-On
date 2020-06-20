package LoginTests;

import exceptions.IncorrectPasswordException;
import exceptions.IncorrectUsernameException;
import org.junit.Test;
import services.UserService;
import  junit.framework.TestCase;
import java.io.IOException;
import static org.junit.Assert.assertEquals;

public class LoginTest {
    public LoginTest() throws IOException {
        UserService.loadPilotsfromFile("src/test/resources/JsonFilePilot.json");
        UserService.loadCustomersfromFile("src/test/resources/JsonFileCustomer.json");
    }
    @Test
    public void test1() throws IncorrectPasswordException, IncorrectUsernameException {
        UserService.loginTry("Fodor Razvan","lotifan");
        assertEquals("customer",UserService.getLoginRole());

    }
    @Test
    public void test2() throws IncorrectPasswordException, IncorrectUsernameException {
        UserService.loginTry("Vladovici Ana","vladi93");
        assertEquals("pilot",UserService.getLoginRole());

    }
    @Test(expected = IncorrectUsernameException.class)
    public void test3() throws IncorrectPasswordException, IncorrectUsernameException {
        UserService.loginTry("Vladovici Anisoara", "vladi93");
    }
    @Test(expected = IncorrectPasswordException.class)
    public void test4() throws IncorrectPasswordException, IncorrectUsernameException {
        UserService.loginTry("Vladovici Ana", "vladi94");

    }
    @Test
    public void test5() throws IncorrectPasswordException, IncorrectUsernameException {
        UserService.loginTry("Vancea Roxana","mamaNatura");
        assertEquals("customer",UserService.getLoginRole());


    }
    @Test
    public void test6() throws IncorrectPasswordException, IncorrectUsernameException {
        UserService.loginTry("Popovici Marian","popmaio99");
        assertEquals("pilot",UserService.getLoginRole());

    }
    @Test(expected = IncorrectPasswordException.class)
    public void test7() throws IncorrectPasswordException, IncorrectUsernameException {
        UserService.loginTry("Fodor Razvan", "loti2");

    }
}
