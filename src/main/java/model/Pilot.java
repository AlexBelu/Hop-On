package model;

import model.User;

public class Pilot extends User {
    public Pilot(String username, String password) {
        super(username, password, "Pilot");
    }

    public Pilot() {
    }

}

