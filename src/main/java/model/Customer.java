package model;

import model.Flight;
import model.User;

import java.util.ArrayList;

public class Customer extends User {
    private ArrayList<Flight> boardingCard;
    public Customer(String username, String password)
    {
        super(username,password,"model.Customer");
    }
    public Customer(){}
}
