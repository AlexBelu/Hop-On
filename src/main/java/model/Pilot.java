package model;

import model.User;

public class Pilot extends User {
    public Pilot(String username, String password) {
        super(username, password, "Pilot");
    }

    public Pilot() {
    }

    public String[] showFlights() {
            String[] flight_array = new String[myFlights.size()];
            for (int i = 0; i < myFlights.size(); i++) {
                flight_array[i] = myFlights.get(i).toString();
            }
            return flight_array;

    }
}

