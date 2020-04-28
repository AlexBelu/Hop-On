package services;
import exceptions.*;
import java.io.IOException;
import java.nio.file.Files;
        import java.nio.file.Path;
import java.util.List;

import exceptions.IncorrectUsernameException;
import model.*;

import com.fasterxml.jackson.core.type.TypeReference;
        import com.fasterxml.jackson.databind.ObjectMapper;
        import org.apache.commons.io.FileUtils;

public class UserService {

    private static List<Pilot> pilots;
    private static List<Customer> customers;
    private static final Path PILOTS_PATH = FileSystemService.getPathToFile("jsonFilePilot.json");
    private static final Path CUSTOMERS_PATH=FileSystemService.getPathToFile("jsonFileCustomer.json");
    private static String LoginRole;

    public static void loadUsersFromFile() throws IOException {

        if (!Files.exists(PILOTS_PATH)) {
            FileUtils.copyURLToFile(UserService.class.getClassLoader().getResource("jsonFile.json"), PILOTS_PATH.toFile());
        }
        if (!Files.exists(CUSTOMERS_PATH)) {
            FileUtils.copyURLToFile(UserService.class.getClassLoader().getResource("jsonFile.json"), CUSTOMERS_PATH.toFile());
        }
        ObjectMapper objectMapper1 = new ObjectMapper();

        pilots = objectMapper1.readValue(PILOTS_PATH.toFile(), new TypeReference<List<Pilot>>() {
        });
        ObjectMapper objectMapper2 = new ObjectMapper();

        customers = objectMapper2.readValue(CUSTOMERS_PATH.toFile(), new TypeReference<List<Customer>>() {
        });
    }
    public static void loginTry(String username, String password) throws IncorrectUsernameException,IncorrectPasswordException{
        int find=0;
        for(Pilot pilot:pilots)
           if(pilot.getUsername().equals(username)) {
               find = 1;
               LoginRole = "pilot";
               if (!pilot.getPassword().equals(JSONWriter.encodePassword(username, password)))
                   throw new IncorrectPasswordException(password);
           }
        for(Customer customer:customers)
           if(customer.getUsername().equals(username)) {
               find=1;
               LoginRole = "customer";
               if(!customer.getPassword().equals(JSONWriter.encodePassword(username,password)))
                   throw new IncorrectPasswordException(password);}
        if(find==0)
             throw new IncorrectUsernameException(username);
    }

    public static String getLoginRole(){
        return LoginRole;
    }
}