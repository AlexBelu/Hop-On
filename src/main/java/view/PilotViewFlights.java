package view;

import services.UserService;

import javax.swing.*;
import java.awt.*;

public class PilotViewFlights  extends JFrame {

    public static void main(String[] args) {
        PilotAdd frameTabel = new PilotAdd();
        frameTabel.setVisible(true);
    }


    PilotViewFlights() {
        super("Show flights");
        setSize(500, 500);
        setLocation(500, 280);
        JLabel welcome = new JLabel("Welcome to the 'Show flights' page");
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);
        welcome.setBounds(167, 10, 250, 100);
        contentPane.add(welcome);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[] b = UserService.findPilot(LoginView.getTxtUsername()).showFlights();

        if (b != null) {
            JList a = new JList(b);
            a.setBounds(100, 100, 100, 60);
            contentPane.add(a);
        } else {
            JLabel er = new JLabel("You do not have any flights");
            er.setBounds(180, 200, 250, 100);
            contentPane.add(er);
        }
    }
}
