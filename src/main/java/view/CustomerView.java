package view;
import services.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;


public class CustomerView extends JFrame {
        private JButton add;
        private JButton show;
        private JButton checkin;
        private JButton boarding;
        private JButton back;



        CustomerView(){

            super("Customer's Page");
            JLabel welcome = new JLabel("Welcome to the Customer's Page");
            setSize(500,500);
            setResizable(false);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocation(500,280);
            welcome.setBounds(160,10,200,110);
            Container contentPane = this.getContentPane();
            contentPane.setLayout(null);
            contentPane.add(welcome);
            add=new JButton("Buy a ticket");
            add.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    CustomerAdd regFace = new CustomerAdd();
                    regFace.setVisible(true);
                    dispose();
                }
            });
            add.setBounds(175, 110, 120, 40);
            contentPane.add(add);
            show=new JButton("Show Flights");
            show.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    CustomerViewFlights regFace = new CustomerViewFlights();
                    regFace.setVisible(true);
                    dispose();
                }
            });
            show.setBounds(175, 160, 120, 40);
            contentPane.add(show);
            checkin=new JButton("Check-in");
            checkin.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    Check_inView regFace = null;
                    try {
                        regFace = new Check_inView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    regFace.setVisible(true);
                    dispose();
                }
            });
            checkin.setBounds(175, 210, 120, 40);
            contentPane.add(checkin);
            boarding=new JButton("Boarding Cards");
            boarding.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    BoardingTable regFace = new BoardingTable();
                    regFace.setVisible(true);
                    dispose();
                }
            });
            boarding.setBounds(175, 260, 120, 40);
            contentPane.add(boarding);
            back=new JButton("Go Back");
            back.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    LoginView regFace = null;
                    regFace = new LoginView();
                    regFace.setVisible(true);
                    dispose();
                }
            });
            back.setBounds(175, 310, 120, 40);
            contentPane.add(back);
        }



    }