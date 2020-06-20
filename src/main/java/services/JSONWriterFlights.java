package services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Flight;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JSONWriterFlights {

    private static final Path FLIGHT_PATH = FileSystemService.getPathToFile("src/main/resources/jsonFileFlight.json");


    public static void main(String[] args){
        ArrayList<Flight> flights = new ArrayList<>();
        Flight flight1 = new Flight(1, "Timisoara", "Londra", "29-06-2020");
        Flight flight2 = new Flight(2, "Timisoara", "Berlin", "25-06-2020");
        Flight flight3 = new Flight(3, "Bucuresti", "Roma", "26-06-2020");
        Flight flight4 = new Flight(4, "Bucuresti", "Paris", "17-07-2020");
        Flight flight5 = new Flight(5, "Timisoara", "Paris", "15-07-2020");
        Flight flight6 = new Flight(6, "Berlin", "Roma", "27-06-2020");
        Flight flight7 = new Flight(7, "Londra", "Timisoara", "13-07-2020");
        Flight flight8 = new Flight(8, "Cluj-Napoca", "Roma", "25-06-2020");
        Flight flight9 = new Flight(9, "Cluj-Napoca", "London", "30-06-2020");
        Flight flight10 = new Flight(10, "Bucuresti", "Copenhaga", "25-06-2020");

        flights.add(flight1);
        flights.add(flight2);
        flights.add(flight3);
        flights.add(flight4);
        flights.add(flight5);
        flights.add(flight6);
        flights.add(flight7);
        flights.add(flight8);
        flights.add(flight9);
        flights.add(flight10);
        try {
            ObjectMapper objectMapper1 = new ObjectMapper();
            objectMapper1.writerWithDefaultPrettyPrinter().writeValue(FLIGHT_PATH.toFile(), flights);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
