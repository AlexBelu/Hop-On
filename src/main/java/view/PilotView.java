package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class PilotView extends JFrame {
   private JButton add;
   private JButton show;



    PilotView(){

        super("Pilot's Page");
        JLabel welcome = new JLabel("Welcome to the Pilot's Page, "+ LoginView.getTxtUsername());
        setSize(500,500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(500,280);
        welcome.setBounds(135,10,300,110);
        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);
        contentPane.add(welcome);
        add=new JButton("Add Flight");
        add.addActionListener(new ActionListener() {
                                  public void actionPerformed(ActionEvent actionEvent) {
                                      PilotAdd regFace = new PilotAdd();
                                      regFace.setVisible(true);
                                      dispose();
                                  }
                              });
        add.setBounds(175, 110, 120, 40);
        contentPane.add(add);
        show=new JButton("Show Flights");
        show.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                PilotViewFlights regFace = new PilotViewFlights();
                regFace.setVisible(true);
                dispose();
            }
        });
        show.setBounds(175, 160, 120, 40);
        contentPane.add(show);


    }
    public static void main(String[] args) {
        PilotView frameTabel = new PilotView();
           frameTabel.setVisible(true);
    }

}