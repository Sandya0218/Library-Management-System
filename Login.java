import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {

    JLabel l1, l2;
    JTextField username;
    JPasswordField password;
    JButton login;

    public Login() {

        setTitle("Library Management System - Login");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 10, 10));

        l1 = new JLabel("Username:");
        l2 = new JLabel("Password:");

        username = new JTextField();
        password = new JPasswordField();

        login = new JButton("Login");
        login.addActionListener(this);

        add(l1);
        add(username);
        add(l2);
        add(password);
        add(new JLabel());
        add(login);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String user = username.getText();
        String pass = String.valueOf(password.getPassword());

        if(user.equals("admin") && pass.equals("admin123")) {

            JOptionPane.showMessageDialog(this, "Login Successful!");

            dispose();

            Menu.showMenu();

        } else {

            JOptionPane.showMessageDialog(this, "Invalid Username or Password");

        }
    }
}
