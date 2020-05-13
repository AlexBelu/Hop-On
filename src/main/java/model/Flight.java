package model;

import services.UserService;

import java.io.IOException;

public class Flight {
    private int flightNo;
    private String departure;
    private String arrival;
    private String date;
    private String usernamePilot1;
    private String usernamePilot2;
    private int noPilots;

    public Flight(int flightNo, String departure, String arrival, String date) {
        this.flightNo = flightNo;
        this.departure = departure;
        this.arrival = arrival;
        this.date = date;
        this.noPilots = 0;
    }

    public Flight(){}

    public int getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(int flightNo) {
        this.flightNo = flightNo;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUsernamePilot1() {
        return usernamePilot1;
    }

    public void setUsernamePilot1(String usernamePilot1) {
        this.usernamePilot1 = usernamePilot1;
        this.noPilots++;
    }

    public String getUsernamePilot2() {
        return usernamePilot2;
    }

    public void setUsernamePilot2(String usernamePilot2) {
        this.usernamePilot2 = usernamePilot2;
        this.noPilots++;
    }

    public int getNoPilots() {
        return noPilots;
    }

    public String toString() {
        return
                "flightNo=" + flightNo +
                ", departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                ", date='" + date + '\''+"pilot:" + usernamePilot1 ;


    }

}
