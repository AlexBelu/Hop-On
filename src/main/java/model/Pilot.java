package model;

import model.User;

public class Pilot extends User {
    public Pilot(String username, String password)
    {
        super(username,password,"model.Pilot");
    }
    public Pilot(){}

    public String showFlights(){
        String str = "";
        for(Flight flight:myFlights){
            str = str + flight + "\n";
        }
        return str;
    }
}
