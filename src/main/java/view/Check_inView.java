package view;

import services.UserService;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNumeric;

public class Check_inView extends JFrame {
    private JButton back;
    private JButton boarding= new JButton("Boarding Cards");
    List<String> selectedValuesList;
    Check_inView() throws IOException {
        super("Check-in");
        JLabel welcome = new JLabel("Welcome to the 'Check-in' Page");
        setSize(500,500);
        setLocation(500,280);
        welcome.setBounds(50,10,200,60);
        JPanel panel = new JPanel();
        panel.add(welcome);

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
        boarding.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                BoardingTable regFace = null;
                regFace = new BoardingTable();
                regFace.setVisible(true);
                dispose();
            }
        });
        boarding.setBounds(50, 100, 200, 100);
        panel.add(boarding);
        String[] b = UserService.listToArray(UserService.checkIn(LoginView.getTxtUsername()));
        System.out.println(b);
        if (b != null) {
            JList a = new JList(b);
            a.setBounds(50, 100, 200, 60);
            a.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()) {
                        selectedValuesList = a.getSelectedValuesList();
                        System.out.println(selectedValuesList);
                    }
                }
            });
            JButton select=new JButton("Select flight");
            select.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent actionEvent) {
                    try {int a=0;
                        int i=9;
                        while(isNumeric(String.valueOf(selectedValuesList.get(selectedValuesList.size()-1).charAt(i)))) {
                            a=a*10+Character.getNumericValue(selectedValuesList.get(selectedValuesList.size()-1).charAt(i));
                            i++;
                        }


                        if(UserService.findCustomer(LoginView.getTxtUsername()).getBoardingCard().contains(UserService.getFlight(a)))
                            JOptionPane.showMessageDialog(null, "You have already added", "Adding flight", JOptionPane.ERROR_MESSAGE);
                        else
                            (UserService.findCustomer(LoginView.getTxtUsername())).addBoardingCards(a, UserService.checkAvailabilityFlight(LoginView.getTxtUsername()));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }});
            panel.add(select);
            panel.add(a);
            add(new JScrollPane(panel), BorderLayout.CENTER);


        } else {
            JLabel er = new JLabel("You do not have any flights available for check-in");
            er.setBounds(180, 350, 250, 200);
            panel.add(er);
            getContentPane().add(panel);
        }

    }


}
