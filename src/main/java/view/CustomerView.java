package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class CustomerView extends JFrame {

    public static void main(String[] args) {
        CustomerView frameTabel = new CustomerView();
    }

    JLabel welcome = new JLabel("Welcome to Customer Page");
    JPanel panel = new JPanel();

    CustomerView(){
        super("Welcome");
        setSize(300,200);
        setLocation(500,280);
        panel.setLayout (null);

        welcome.setBounds(70,50,150,60);

        panel.add(welcome);

        getContentPane().add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}