package view;

import controller.LoginController;
import model.Pilot;
import services.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PilotAdd extends JFrame {
    public static void main(String[] args) {
        PilotAdd frameTabel = new PilotAdd();
        frameTabel.setVisible(true);
    }


    JPanel panel = new JPanel();

    PilotAdd(){
        super("Add Flights");
        JLabel welcome = new JLabel("Welcome to the 'Add Flights' Page");
        setSize(500,500);
        setLocation(500,280);

        welcome.setBounds(50,10,200,60);

        panel.add(welcome);

        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}

