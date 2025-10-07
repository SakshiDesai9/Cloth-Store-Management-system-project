package cloth;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionListener;

public abstract class dp extends JFrame implements ActionListener {
    private JTextField username;
    private JTextField password;
    private JButton login;
    private JButton cancel;
    dp(){
    setLayout(null);

    JLabel user=new JLabel("Username");
        user.setBounds(230,300,100,30);
        user.setFont(new Font("serif",Font.PLAIN,20));
    add(user);

    username=new JTextField();
        username.setBounds(450,300,250,40);
        username.setFont(new Font("serif",Font.PLAIN,20));
    add(username);

    JLabel pass=new JLabel("Password");
        pass.setBounds(230,450,100,30);
        pass.setFont(new Font("serif",Font.PLAIN,20));
    add(pass);
    password=new JPasswordField ();
        password.setBounds(450,450,250,40);
        password.setFont(new Font("serif",Font.PLAIN,20));
    add(password);
    login=new JButton("Login");
        login.setBounds(200,600,250,60);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.setFont(new Font("serif",Font.PLAIN,20));
        login.addActionListener(this);
    add(login);

    cancel=new JButton("Cancel");
        cancel.setBounds(480,600,220,60);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("serif",Font.PLAIN,20));
        cancel.addActionListener(this);

    add(cancel);

    setBounds(0,0,1550,1000);
    setVisible(true);
}
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
