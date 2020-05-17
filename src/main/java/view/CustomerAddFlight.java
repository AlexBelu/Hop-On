package view;

import model.Customer;
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

public class CustomerAddFlight extends JFrame {

    public static void main(String[] args) throws IOException {
        CustomerAddFlight frameTabel = new CustomerAddFlight();
        frameTabel.setVisible(true);
    }

    List<String> selectedValuesList;

    public CustomerAddFlight() throws IOException, NullPointerException {
        super("Select Flight");
        setSize(500,250);
        setLocation(500,280);
        JLabel welcome = new JLabel("Welcome to the 'Select Flights' Page");
        welcome.setBounds(50,10,200,60);
        JPanel panel=new JPanel();
        panel.add(welcome);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] b = UserService.listToArray(UserService.checkAvailableFlightsUser(LoginView.getTxtUsername(), CustomerAdd.getDeparture(), CustomerAdd.getArrival()));
        System.out.println(LoginView.getTxtUsername());
        System.out.println(CustomerAdd.getDeparture());
        System.out.println(CustomerAdd.getArrival());
        System.out.println(UserService.checkAvailableFlightsUser(LoginView.getTxtUsername(), CustomerAdd.getDeparture(), CustomerAdd.getArrival()));

        if (b != null){
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

                        if(UserService.findCustomer(LoginView.getTxtUsername()).getMyFlights().contains(UserService.getFlight(a)))
                            JOptionPane.showMessageDialog(null, "You have already added", "Adding flight", JOptionPane.ERROR_MESSAGE);
                        else
                            (UserService.findCustomer(LoginView.getTxtUsername())).addFlight(a, UserService.checkAvailableFlightsUser(LoginView.getTxtUsername(), CustomerAdd.getDeparture(), CustomerAdd.getArrival()));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }});
            panel.add(select);
            panel.add(a);
            add(new JScrollPane(panel), BorderLayout.CENTER);
        }
    }
}
