package view;

import services.UserService;
import javax.swing.JScrollPane;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PilotViewFlights  extends JFrame {
     private JButton back ;
    public static void main(String[] args) throws IOException {
        PilotAdd frameTabel = new PilotAdd();
        frameTabel.setVisible(true);
    }


    PilotViewFlights() {
        super("Show flights");
        setSize(500, 500);
        setLocation(500, 280);
        JLabel welcome = new JLabel("Welcome to the 'Show flights' page");
        JPanel panel = new JPanel();
        welcome.setBounds(167, 10, 250, 100);
        panel.add(welcome);
        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        String[] b = UserService.findPilot(LoginView.getTxtUsername()).showFlights();
        back= new JButton("Go Back");
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                PilotView regFace = null;
                regFace = new PilotView();
                regFace.setVisible(true);
                dispose();
            }
        });
        back.setBounds(50, 100, 200, 80);
        panel.add(back);

        if (b != null) {
            JList a = new JList(b);
            a.setBounds(100, 100, 200, 60);
            panel.add(a);
            add(new JScrollPane(panel),BorderLayout.CENTER);
        } else {
            JLabel er = new JLabel("You do not have any flights");
            er.setBounds(180, 200, 250, 100);
            panel.add(er);
        }
    }
}
