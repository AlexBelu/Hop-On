package view;
import controller.LoginController;
import model.Pilot;
import services.UserService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.AttributeSet;

import static org.apache.commons.lang3.StringUtils.isNumeric;

public class PilotAdd extends JFrame {
    public static void main(String[] args) throws IOException {
        PilotAdd frameTabel = new PilotAdd();
        frameTabel.setVisible(true);

    }
    List<String> selectedValuesList;


    PilotAdd() throws IOException {

        super("Add Flights");

       JLabel welcome = new JLabel("Welcome to the 'Add Flights' Page");
        setSize(500,250);
        setLocation(500,280);
        welcome.setBounds(50,10,200,60);
        JPanel panel=new JPanel();
        panel.add(welcome);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[] b = UserService.listToArray(UserService.checkAvailabilityFlight(LoginView.getTxtUsername()));
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
                    try { int a=0;
                         int i=9;
                         while(isNumeric(String.valueOf(selectedValuesList.get(selectedValuesList.size()-1).charAt(i)))) {
                             a=a*10+Character.getNumericValue(selectedValuesList.get(selectedValuesList.size()-1).charAt(i));
                             i++;
                         }


                          if(LoginView.getTxtUsername().equals(UserService.getFlight(a).getUsernamePilot1()))
                              JOptionPane.showMessageDialog(null, "You have already added", "Adding flight", JOptionPane.ERROR_MESSAGE);
                          else
                              (UserService.findPilot(LoginView.getTxtUsername())).addFlight(a, UserService.checkAvailabilityFlight(LoginView.getTxtUsername()));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }});
            panel.add(select);
            panel.add(a);
            add(new JScrollPane(panel),BorderLayout.CENTER);


        } else {
            JLabel er = new JLabel("You do not have any flights");
            er.setBounds(180, 200, 250, 100);
          panel.add(er);
        }


    }

}

