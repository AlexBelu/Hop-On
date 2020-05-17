package services;
import exceptions.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import exceptions.IncorrectUsernameException;
import model.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;


public class UserService {

    private static List<Pilot> pilots;
    private static List<Customer> customers;
    private static List<Flight> flights;
    private static final Path PILOTS_PATH = FileSystemService.getPathToFile("jsonFilePilot.json");
    private static final Path CUSTOMERS_PATH = FileSystemService.getPathToFile("jsonFileCustomer.json");
    private static final Path FLIGHT_PATH = FileSystemService.getPathToFile("jsonFileFlight.json");
    private static String LoginRole;

    public static void loadUsersFromFile() throws IOException {

        if (!Files.exists(PILOTS_PATH)) {
            FileUtils.copyURLToFile(UserService.class.getClassLoader().getResource("jsonFilePilot.json"), PILOTS_PATH.toFile());
        }
        if (!Files.exists(CUSTOMERS_PATH)) {
            FileUtils.copyURLToFile(UserService.class.getClassLoader().getResource("jsonFileCustomer.json"), CUSTOMERS_PATH.toFile());
        }
        ObjectMapper objectMapper1 = new ObjectMapper();
        pilots = objectMapper1.readValue(PILOTS_PATH.toFile(), new TypeReference<List<Pilot>>() {
        });
        ObjectMapper objectMapper2 = new ObjectMapper();
        customers = objectMapper2.readValue(CUSTOMERS_PATH.toFile(), new TypeReference<List<Customer>>() {
        });
    }

    public static void loadFlightsFromFile() throws IOException {
        if (!Files.exists(FLIGHT_PATH)) {
            FileUtils.copyURLToFile(UserService.class.getClassLoader().getResource("jsonFileFlight.json"), FLIGHT_PATH.toFile());
        }
        ObjectMapper objectMapper3 = new ObjectMapper();
        flights = objectMapper3.readValue(FLIGHT_PATH.toFile(), new TypeReference<List<Flight>>() {
        });
    }

    public static void loginTry(String username, String password) throws IncorrectUsernameException, IncorrectPasswordException {
        int find = 0;
        for (Pilot pilot : pilots)
            if (pilot.getUsername().equals(username)) {
                find = 1;
                LoginRole = "pilot";
                if (!pilot.getPassword().equals(JSONWriter.encodePassword(username, password)))
                    throw new IncorrectPasswordException(password);
            }
        for (Customer customer : customers)
            if (customer.getUsername().equals(username)) {
                find = 1;
                LoginRole = "customer";
                if (!customer.getPassword().equals(JSONWriter.encodePassword(username, password)))
                    throw new IncorrectPasswordException(password);
            }
        if (find == 0)
            throw new IncorrectUsernameException(username);
    }

    public static List<Pilot> getPilots() {
        return pilots;
    }

    public static List<Flight> getFlights() {
        return flights;
    }

    public static Path getPathPilot() {
        return PILOTS_PATH;
    }

    public static Path getPathCustomer() {
        return CUSTOMERS_PATH;
    }

    public static String getLoginRole() {
        return LoginRole;
    }

    public static Pilot findPilot(String username) {
        for (Pilot pilot : pilots)
            if (pilot.getUsername().equals(username))
                return pilot;
        return null;
    }

    public static Customer findCustomer(String username) {
        for (Customer customer : customers)
            if (customer.getUsername().equals(username))
                return customer;
        return null;
    }

    public static ArrayList<Flight> checkAvailabilityFlight(String username) throws IOException {
        ArrayList<Flight> availableFlights = new ArrayList<>();
        UserService.loadFlightsFromFile();
        for (Flight flight : flights) {
            if ((flight.getUsernamePilot1() == null || !(flight.getUsernamePilot1().equals(username))
                    && (flight.getUsernamePilot2() == null || !(flight.getUsernamePilot2().equals(username))))
                    && flight.getNoPilots() < 2) {
                availableFlights.add(flight);
            }
        }

        return availableFlights;
    }

    public static ArrayList<Flight> checkAvailableFlightsUser(String username, String departure, String arrival) throws IOException {
        ArrayList<Flight> availableFlights = new ArrayList<>();
        UserService.loadFlightsFromFile();
        for (Flight flight : flights) {
            if (!(getCustomer(username).getMyFlights().contains(flight)))
               if(flight.getDeparture().equals(departure)&&flight.getArrival().equals(arrival))
                availableFlights.add(flight);
        }
        return availableFlights;
    }
    public static Flight getFlight(int a )
    {  Flight b = null;
        for(Flight flight:flights) {
            if (a == flight.getFlightNo()) {
                b = flight;
            }
        }
        return b;
    }
   public static Customer getCustomer(String username)
   {
       for (Customer customer : customers)
           if (customer.getUsername().equals(username))
               return customer;
       return null;
   }
    public static String[] listToArray(ArrayList list) {
        if (list.size() == 0) {
            return null;
        } else {
            String[] array = new String[list.size()];
            for (int i = 0; i < list.size(); i++) {
                array[i] = list.get(i).toString();
            }
            return array;
        }
    }
    public static void main(String[] argv) throws IOException {
       loadUsersFromFile();
       loadFlightsFromFile();


       //UserService.findPilot("Vladovici Ana").addFlight(2,checkAvailabilityFlight("Vladovici Ana"));
        //System.out.println(UserService.findPilot("Vladovici Ana").getMyFlights());

    }
}
