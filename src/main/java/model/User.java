package model;

import java.util.ArrayList;
import java.util.Objects;

abstract public class User {
    protected String username;
    protected String password;
    protected String role;
    protected ArrayList<Flight>myFlights=new ArrayList<>();
    public User(String username, String password, String role)
    {
        this.username=username;
        this.password=password;
        this.role=role;
    }
   public User(){}
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getUsername().equals(user.getUsername()) &&
                getPassword().equals(user.getPassword()) &&
                getRole().equals(user.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword(), getRole());
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", myFlights=" + myFlights +
                '}';
    }

    public String[] showFlights() {
        if(myFlights.size() == 0){
            return null;
        }
        else {
            String[] flight_array = new String[myFlights.size()];
            for (int i = 0; i < myFlights.size(); i++) {
                flight_array[i] = myFlights.get(i).toString();
            }
            return flight_array;
        }
    }
}

