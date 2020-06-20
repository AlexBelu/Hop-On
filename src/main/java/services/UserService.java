package services;
import exceptions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import exceptions.IncorrectUsernameException;
import model.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import view.LoginView;


public class UserService {

    private static ArrayList<Pilot> pilots;
    private static ArrayList<Customer> customers;
    private static ArrayList<Flight> flights;
    private static  Path PILOTS_PATH=FileSystemService.getPathToFile("src/main/resources/jsonFilePilot.json");
    private static  Path CUSTOMERS_PATH=FileSystemService.getPathToFile("src/main/resources/jsonFileCustomer.json"); ;
    private static  Path FLIGHT_PATH=FileSystemService.getPathToFile("src/main/resources/jsonFileFlight.json"); ;
    private static String LoginRole;

    public static void loadPilotsfromFile(String path) throws IOException { //
            PILOTS_PATH = FileSystemService.getPathToFile(path);
        try {
            ObjectMapper objectMapper1 = new ObjectMapper();
            pilots = objectMapper1.readValue(PILOTS_PATH.toFile(), new TypeReference<ArrayList<Pilot>>() {
            });
        }catch (FileNotFoundException a) {
            System.out.println("Nu exista fisierul pt pilot");
        }


    }

    public static void loadCustomersfromFile(String path) throws IOException {  //
         CUSTOMERS_PATH=  FileSystemService.getPathToFile(path);
     try{
        ObjectMapper objectMapper1 = new ObjectMapper();
        customers = objectMapper1.readValue( CUSTOMERS_PATH.toFile(), new TypeReference<ArrayList<Customer>>() {
        });
     }catch (FileNotFoundException a) {
         System.out.println("Nu exista fisierul pt customer");
     }

    }


    public static void loadFlightsFromFile(String path) throws IOException {   //
        FLIGHT_PATH=  FileSystemService.getPathToFile(path);

      try {  ObjectMapper objectMapper3 = new ObjectMapper();
        flights = objectMapper3.readValue(FLIGHT_PATH.toFile(), new TypeReference<ArrayList<Flight>>() {
        });
      }catch (FileNotFoundException a) {
          System.out.println("Nu exista fisierul pt zbor");
      }

    }
    public static void copy() throws IOException {
       if(!Files.exists((UserService.getPathCustomer())))
           FileUtils.copyURLToFile(UserService.class.getClassLoader().getResource("jsonFileCustomer.json"), UserService.getPathCustomer().toFile());
       if(!Files.exists((UserService.getFlightPath())))
        FileUtils.copyURLToFile(UserService.class.getClassLoader().getResource("jsonFileFlight.json"), UserService.getFlightPath().toFile());
        if(!Files.exists((UserService.getPathPilot())))
        FileUtils.copyURLToFile(UserService.class.getClassLoader().getResource("jsonFilePilot.json"), UserService.getPathPilot().toFile());
    }


    public static Path getFlightPath()
    {
        return FLIGHT_PATH;
    }
    public static void loginTry(String username, String password) throws IncorrectUsernameException, IncorrectPasswordException { //
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

    public static ArrayList<Pilot> getPilots() {
        return pilots;
    }//

    public static ArrayList <Customer> getCustomers(){return customers;}//

    public static ArrayList<Flight> getFlights() {
        return flights;
    }//

    public static Path getPathPilot() {
        return PILOTS_PATH;
    }

    public static Path getPathCustomer() {
        return CUSTOMERS_PATH;
    }

    public static String getLoginRole() {
        return LoginRole;
    }//

    public static Pilot findPilot(String username) {    //
        for (Pilot pilot : pilots)
            if (pilot.getUsername().equals(username))
                return pilot;
        return null;
    }

    public static Customer findCustomer(String username) {  //
        for (Customer customer : customers)
            if (customer.getUsername().equals(username))
                return customer;
        return null;
    }

    public static ArrayList<Flight> checkAvailabilityFlight(String username) throws IOException {   //testata in UserServiceTests
        ArrayList<Flight> availableFlights = new ArrayList<>();
        //UserService.loadFlightsFromFile("src/main/resources/jsonFileFlight");
        for (Flight flight : flights) {
            if ((flight.getUsernamePilot1() == null || !(flight.getUsernamePilot1().equals(username))
                    && (flight.getUsernamePilot2() == null || !(flight.getUsernamePilot2().equals(username))))
                    && flight.getNoPilots() < 2) {
                availableFlights.add(flight);
            }
        }

        return availableFlights;
    }

    public static ArrayList<Flight> checkAvailableFlightsUser(String username, String departure, String arrival) throws IOException {   //testata in UserServiceTests
        ArrayList<Flight> availableFlights = new ArrayList<>();
        //UserService.loadFlightsFromFile("src/main/resources/jsonFileFlight");
        for (Flight flight : flights) {
            if (!(findCustomer(username).getMyFlights().contains(flight)))
               if(flight.getDeparture().equals(departure)&&flight.getArrival().equals(arrival)) {
                   availableFlights.add(flight);
               }
        }
        return availableFlights;
    }


    public static ArrayList<Flight> checkIn(String username) throws IOException {   //
        ArrayList<Flight> checkInavailable = new ArrayList<>();
        //UserService.loadFlightsFromFile("src/main/resources/jsonFileFlight");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String s = formatter.format(date);
        int valData = Character.getNumericValue(s.charAt(0)) * 10 + Character.getNumericValue(s.charAt(1));
        for(Flight flight:findCustomer(username).getMyFlights()){
            int a = Character.getNumericValue(flight.getDate().charAt(0));
            int b = Character.getNumericValue(flight.getDate().charAt(1));
            int c = 10*a + b;
            int dif = c - valData;
            if(dif <= 2){
                checkInavailable.add(flight);
            }
        }
        return checkInavailable;
    }

    public static Flight getFlight(int a ) //
    {  Flight b = null;
        for(Flight flight:flights) {
            if (a == flight.getFlightNo()) {
                b = flight;
            }
        }
        return b;
    }

    public static String[] listToArray(ArrayList list) {    //
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
    public static void writePilots(ArrayList<Pilot> a) //
    {  pilots=a;
        try {
        ObjectMapper objectMapper1 = new ObjectMapper();
        objectMapper1.writerWithDefaultPrettyPrinter().writeValue(PILOTS_PATH.toFile(), pilots);
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    public static void writeCustomers(ArrayList <Customer> a)//
    {   customers=a;
        try {
        ObjectMapper objectMapper1 = new ObjectMapper();
        objectMapper1.writerWithDefaultPrettyPrinter().writeValue(CUSTOMERS_PATH.toFile(), customers);
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    public static void writeFlights(ArrayList<Flight> a) //

    {  flights=a;
        try {
        ObjectMapper objectMapper1 = new ObjectMapper();
        objectMapper1.writerWithDefaultPrettyPrinter().writeValue(FLIGHT_PATH.toFile(), flights);
    } catch (IOException e) {
        e.printStackTrace();
    }
    }



    public static void main(String[] argv) throws IOException {
       loadCustomersfromFile("src/main/resources/da");
        loadPilotsfromFile("src/main/resources/nu");
       loadFlightsFromFile("src/main/resources/jsonFileFlight.json");
       System.out.println(CUSTOMERS_PATH);
       System.out.println(customers);



    }
}
