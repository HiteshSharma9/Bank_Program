package bank;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class login
{ // begin of form
    static JLabel lblHeading,lblsubhead;
    static JLabel lbluname, lblpass;
    static JFormattedTextField txtuname;
    static JPasswordField txtpass;
    static JFrame frame;
    static JButton btnExit, btnRegister, btnLogin;
    static Color btnColor;
    static Statement start;

    public login()
    {
        createAndShowGUI();
    }
    private static void createAndShowGUI() {
        try {// frame setting
            frame = new JFrame("Banking System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Insets insets = frame.getInsets();
            frame.setBounds(0, 0, screenSize.width, screenSize.height);
            frame.setResizable(true);
            frame.setUndecorated(true);
            frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
            frame.setVisible(true);

            // label
            lblHeading = new JLabel("HDFC BANK ACCOUNT MANAGER");
            lblsubhead = new JLabel("LOGIN MANAGER");
            lbluname = new JLabel("User Name/ID");
            lblpass = new JLabel("Password");

            // button
            btnExit = new JButton("Exit");
            btnRegister = new JButton("Register");
            btnLogin = new JButton("Login");

            // textbox
            txtuname = new JFormattedTextField("");
            txtpass = new JPasswordField("");

            frame.getContentPane().setLayout(null);
            addComponentToPane(frame.getContentPane(), lblHeading, 600, 20, 0, 0);
            addComponentToPane(frame.getContentPane(), lblsubhead, 640, 90, 0, 0);
            addComponentToPane(frame.getContentPane(), lbluname, 500, 200, 0, 0);
            addComponentToPane(frame.getContentPane(), lblpass, 500, 250, 0, 0);

            addComponentToPane(frame.getContentPane(), btnLogin, 450, 400, 50, 0, "Login");
            addComponentToPane(frame.getContentPane(), btnRegister, 650, 400, 50, 0, "Register");
            addComponentToPane(frame.getContentPane(), btnExit, 850, 400, 50, 0, "exit");

            addComponentToPane(frame.getContentPane(), txtuname, 700, 200, 100, 0);
            addComponentToPane(frame.getContentPane(), txtpass, 700, 250, 100, 0);
            enable_disable(false);

        } catch (Exception e)
        {
            System.out.println("error in displaying control" + e.getMessage());
            JOptionPane.showMessageDialog(frame, "Error");
        }

    } // end of createAndShowGUI method

    public static void addComponentToPane(Container pane, JButton paneControl, int l, int t, int w, int h, String title) {
        Insets insets = pane.getInsets();
        Dimension size = paneControl.getPreferredSize();
        size = paneControl.getPreferredSize();
        paneControl.setBounds(l + insets.left, t + insets.top, size.width + w, size.height + h);
        pane.add(paneControl);
        btnColor = new Color(153, 51, 0);
        paneControl.setForeground(btnColor);
        paneControl.setActionCommand(title);
        paneControl.setFont(new Font("Times New Roman", Font.BOLD, 16));
        paneControl.addActionListener(new myAction());
    }

    public static void addComponentToPane(Container pane, JLabel paneControl, int l, int t, int w, int h) {
        Insets insets = pane.getInsets();
        Dimension size = paneControl.getPreferredSize();
        size = paneControl.getPreferredSize();
        paneControl.setBounds(l + insets.left, t + insets.top, size.width + w, size.height + h);
        pane.add(paneControl);
    }

    public static void addComponentToPane(Container pane, JFormattedTextField paneControl, int l, int t, int w, int h) {
        Insets insets = pane.getInsets();
        Dimension size = paneControl.getPreferredSize();
        size = paneControl.getPreferredSize();
        paneControl.setBounds(l + insets.left, t + insets.top, size.width + w, size.height + h);
        pane.add(paneControl);
    }
    public static void addComponentToPane(Container pane, JPasswordField paneControl, int l, int t, int w, int h) {
        Insets insets = pane.getInsets();
        Dimension size = paneControl.getPreferredSize();
        size = paneControl.getPreferredSize();
        paneControl.setBounds(l + insets.left, t + insets.top, size.width + w, size.height + h);
        pane.add(paneControl);
    }

    private static class myAction implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            if ("exit".equals(e.getActionCommand()))
            {
                System.exit(0);
            }
            if ("Login".equals(e.getActionCommand()))
            {
                try
                {
                    enable_disable(false);
                    loginwork();
                    clearText();
                }
                catch (Exception e2)
                {
                    JOptionPane.showMessageDialog(frame, e2.getMessage());
                }
            }
            if ("Register".equals(e.getActionCommand()))
            {
                accreate ac = new accreate();
                frame.setVisible(false);
            }
        }

    }

    static private void enable_disable(boolean tf) {
        txtuname.setEnabled(!tf);
        txtpass.setEnabled(!tf);
        btnLogin.setEnabled(!tf);
        btnRegister.setEnabled(!tf);
        btnExit.setEnabled(!tf);
    }

    static private void clearText() {
        txtuname.setText("");
        txtpass.setText("");
    }

    public static void main(String arg[])
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });

    }

    public static void loginwork() {
        try {
            connection mycon3 = new connection();
            Connection myCon =  mycon3.getConn();
            start = myCon.createStatement();
            String sqlstring,id,pass;
            id=txtuname.getText();
            pass=txtpass.getText().toString();
            sqlstring = "select * from account_master where userid='"+id+"' and pass='"+pass+"'";
            ResultSet rs = start.executeQuery(sqlstring);
            if (rs.next())
            {
                transform tr = new transform();
                frame.setVisible(false);
            }
            else
            {
                JOptionPane.showMessageDialog(frame, "Username or Password Incorrect");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
