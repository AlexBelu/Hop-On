package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerAdd extends JFrame {
    CustomerAdd(){
        super("Buy a Ticket");
        JLabel welcome = new JLabel("Choose a ticket to buy");
        setSize(500,500);
        setLocation(500,280);
        welcome.setBounds(50,10,200,60);
        JPanel panel = new JPanel();
        panel.add(welcome);
        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public static void main(String[] args) {
        CustomerAdd frameTabel = new CustomerAdd();
        frameTabel.setVisible(true);
    }

}