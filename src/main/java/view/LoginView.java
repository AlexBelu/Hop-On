package view;

import controller.LoginController;
import model.Pilot;
import services.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JButton btnRegister;
    private static JTextField txtUsername;
    private JPasswordField txtPassword;
    private JComboBox<String> cmbRole;
    private LoginController controller;

    public LoginView() {
        controller = new LoginController(this);

        setTitle("Hop-On: Login");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);

        Container contentPane = this.getContentPane();
        contentPane.setLayout(null);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(10, 10, 120, 25);
        contentPane.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setBounds(80, 10, 100, 25);
        contentPane.add(txtUsername);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(10, 40, 120, 25);
        contentPane.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(80, 40, 100, 25);
        contentPane.add(txtPassword);


        btnRegister = new JButton("Login");
        btnRegister.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (controller.checkAvailability(txtUsername.getText(), txtPassword.getText())) {
                    //JOptionPane.showMessageDialog(null, "User successfully logged in", "Logging in user", JOptionPane.INFORMATION_MESSAGE);
                    if(UserService.getLoginRole().equals("pilot")){
                        PilotView regFace =new PilotView();
                        regFace.setVisible(true);
                        dispose();
                    }
                    if(UserService.getLoginRole().equals("customer")){
                        CustomerView regFace =new CustomerView();
                        regFace.setVisible(true);
                        dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials", "Adding user", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btnRegister.setBounds(175, 110, 100, 40);

        contentPane.add(btnRegister);
    }

    public static String getTxtUsername(){
        return txtUsername.getText();
    }

    public static void main(String[] args) throws Exception {
        UserService.loadUsersFromFile();

        LoginView view = new LoginView();
        view.setVisible(true);
    }
}