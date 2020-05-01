package view;

import javax.swing.*;

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

    }
    public static void main(String[] args) {
        CustomerAdd frameTabel = new CustomerAdd();
        frameTabel.setVisible(true);
    }

}