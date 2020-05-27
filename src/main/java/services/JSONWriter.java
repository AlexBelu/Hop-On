package services;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import services.FileSystemService;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import model.*;
public class JSONWriter {
        private static FileWriter file;
        private static final Path PILOT_PATH = FileSystemService.getPathToFile("src/main/resources/jsonFilePilot.json");
        private static final Path CUSTOMER_PATH = FileSystemService.getPathToFile("src/main/resources/jsonFileCustomer.json");

        @SuppressWarnings("unchecked")
        static String encodePassword(String salt, String password) {
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
        try {
            ObjectMapper objectMapper1 = new ObjectMapper();
            objectMapper1.writerWithDefaultPrettyPrinter().writeValue(PILOT_PATH.toFile(), users1);
            ObjectMapper objectMapper2 = new ObjectMapper();
            objectMapper2.writerWithDefaultPrettyPrinter().writeValue(CUSTOMER_PATH.toFile(), users2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}