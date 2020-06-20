package LoginTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Customer;
import model.Flight;
import model.Pilot;
import org.junit.Test;
import services.FileSystemService;
import services.JSONWriter;
import services.JSONWriterFlights;
import services.UserService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
//aici cream fisierele pentru test
public class JsonWriterTest {
    private static final Path PILOTTest_PATH = FileSystemService.getPathToFile("src/test/resources/jsonFilePilot.json");
    private static final Path CUSTOMERTest_PATH = FileSystemService.getPathToFile("src/test/resources/jsonFileCustomer.json");
    private static final Path FLIGHTTest_PATH = FileSystemService.getPathToFile("src/test/resources/jsonFileFlight.json");

    @SuppressWarnings("unchecked")
  public   static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("", ""); //to be able to save in JSON format
    }
    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }
       @Test
        public void test()
       {
           JSONWriter a= new JSONWriter();
           String[] b=new String[1];
           a.main(b);
       }
    @Test
    public void test1()
    {
        JSONWriterFlights a= new JSONWriterFlights();
        String[] b=new String[1];
        a.main(b);
    }

    public static void main(String[] args){
        ArrayList<Pilot> users1 = new ArrayList<Pilot>();
        Pilot pilot1 = new Pilot("Popovici Marian", encodePassword("Popovici Marian", "popmaio99"));
        Pilot pilot2 = new Pilot("Vladovici Ana", encodePassword("Vladovici Ana", "vladi93"));
        users1.add(pilot1);
        users1.add(pilot2);

        ArrayList<Customer> users2 = new ArrayList<Customer>();
        Customer customer1 = new Customer ("Vancea Roxana", encodePassword("Vancea Roxana", "mamaNatura"));
        Customer customer2 = new Customer ("Fodor Razvan", encodePassword("Fodor Razvan", "lotifan"));
        users2.add(customer1);
        users2.add(customer2);
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
        try {
            ObjectMapper objectMapper1 = new ObjectMapper();
            objectMapper1.writerWithDefaultPrettyPrinter().writeValue(PILOTTest_PATH.toFile(), users1);
            ObjectMapper objectMapper2 = new ObjectMapper();
            objectMapper2.writerWithDefaultPrettyPrinter().writeValue(CUSTOMERTest_PATH.toFile(), users2);
            ObjectMapper objectMapper3 = new ObjectMapper();
            objectMapper3.writerWithDefaultPrettyPrinter().writeValue(FLIGHTTest_PATH.toFile(), flights);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

