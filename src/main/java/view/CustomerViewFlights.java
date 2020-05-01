package view;

import javax.swing.*;

public class CustomerViewFlights extends JFrame {
    CustomerViewFlights(){
        super("View Flights");
        JLabel welcome = new JLabel("Welcome to the 'View Flights' Page");
        setSize(500,500);
        setLocation(500,280);
        welcome.setBounds(50,10,200,60);
        JPanel panel = new JPanel();
        panel.add(welcome);
        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public static void main(String[] args) {
        CustomerAdd frameTabel = new CustomerAdd();
        frameTabel.setVisible(true);
    }

}
