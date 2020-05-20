package view;

import services.UserService;

import javax.swing.*;
import java.awt.*;

public class BoardingTable extends JFrame {
    BoardingTable(){
        super("Boarding Table");
        JLabel welcome = new JLabel("These are yours boarding cards");
        setSize(500,500);
        setLocation(500,280);
        welcome.setBounds(50,10,200,60);
        JPanel panel = new JPanel();
        panel.add(welcome);
        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] b = UserService.findCustomer(LoginView.getTxtUsername()).showBoardingCards();

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
    public static void main(String[] args) {
        BoardingTable frameTabel = new BoardingTable();
        frameTabel.setVisible(true);
    }

}