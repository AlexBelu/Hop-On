package view;

import javax.swing.*;

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
        JPanel panel = new JPanel();

        welcome.setBounds(70, 50, 150, 60);

        panel.add(welcome);

        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
