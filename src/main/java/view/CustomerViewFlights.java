package view;

import services.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerViewFlights extends JFrame {
    private JButton back ;
    CustomerViewFlights(){
        super("View Flights");
        JLabel welcome = new JLabel("Welcome to the 'Show Flights' Page");
        setSize(500,500);
        setLocation(500,280);
        welcome.setBounds(50,10,200,60);
        JPanel panel = new JPanel();
        panel.add(welcome);
        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        back= new JButton("Go Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                CustomerView regFace = null;
                regFace = new CustomerView();
                regFace.setVisible(true);
                dispose();
            }
        });
        back.setBounds(50, 100, 200, 80);
        panel.add(back);

        String[] b = UserService.findCustomer(LoginView.getTxtUsername()).showFlights();

        if (b != null) {
            JList a = new JList(b);
            a.setBounds(100, 100, 200, 60);
            panel.add(a);
            add(new JScrollPane(panel), BorderLayout.CENTER);
        } else {
            JLabel er = new JLabel("You do not have any flights", SwingConstants.CENTER);
            er.setVerticalAlignment(SwingConstants.CENTER);
            er.setBounds(180, 200, 250, 100);
            panel.add(er);
        }
    }
}
