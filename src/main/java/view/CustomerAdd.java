package view;
import services.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CustomerAdd extends JFrame {
    CustomerAdd(){
        super("Buy a Ticket");
        JLabel welcome = new JLabel("Enter departure <-> arrival place");
        setSize(500,500);
        setLocation(500,280);
        welcome.setBounds(50,0,200,60);
        //JPanel panel = new JPanel();
        //panel.add(welcome);
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);
        contentPane.add(welcome);
        //getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel departure = new JLabel("Departure:");
        departure.setBounds(10, 50, 120, 25);
        contentPane.add(departure);

        JTextField txtDepature = new JTextField();
        txtDepature.setBounds(80, 50, 120, 25);
        contentPane.add(txtDepature);

        JLabel arrival = new JLabel("Arrival:");
        arrival.setBounds(10, 80, 120, 25);
        contentPane.add(arrival);

        JTextField txtArrival = new JTextField();
        txtArrival.setBounds(80, 80, 120, 25);
        contentPane.add(txtArrival);

        JButton search = new JButton("Search");
        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (UserService.checkAvailableFlightsUser(LoginView.getTxtUsername(), txtDepature.getText(), txtArrival.getText()) == null) {
                        JOptionPane.showMessageDialog(null, "Sorry, we didn't fond any flight!", "Searching flight", JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        CustomerAddFlight regFace =new CustomerAddFlight();
                        regFace.setVisible(true);
                        dispose();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        search.setBounds(175, 110, 100, 40);
        contentPane.add(search);
    }
    public static void main(String[] args) {
        CustomerAdd frameTabel = new CustomerAdd();
        frameTabel.setVisible(true);
    }

}