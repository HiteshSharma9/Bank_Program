package bank;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class accreate
{ // begin of form
    static JLabel lblHeading,lblsubHead;
    static JLabel lblId, lblName, lblmno, lbldob, lblbal, lbladds, lblaccdate, lbluserid, lblpass;
    static JFormattedTextField txtId, txtName,txtmno, txtdob, txtbal, txtadds, txtaccdate, txtuserid;
    static JPasswordField txtpass;
    static JFrame frame;
    static JButton btnExit, btnCreate, btnSave, btnCancel, btnTrans;
    static Color btnColor;
    static int actionFlag;
    static Statement start;

    public accreate()
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
            frame.setResizable(false);
            frame.setUndecorated(true);
            frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
            frame.setVisible(true);

            // label
            lblHeading = new JLabel("HDFC BANK ACCOUNT MANAGER");
            lblsubHead = new JLabel("ACCOUNT CREATION");
            lblId = new JLabel("Account ID");
            lblName = new JLabel("Name");
            lblmno = new JLabel("Mobile Number");
            lbldob = new JLabel("Date OF Birth");
            lbladds = new JLabel("Address");
            lblaccdate = new JLabel("Account Date");
            lblbal = new JLabel("Balance");
            lbluserid = new JLabel("Username/Id");
            lblpass = new JLabel("Password");

            // button
            btnExit = new JButton("Exit");
            btnCreate = new JButton("Create Account");
            btnTrans = new JButton("Transaction");
            btnSave = new JButton("Save");
            btnCancel = new JButton("Cancel");

            // textfield
            actionFlag = 0;
            txtId = new JFormattedTextField("");
            txtName = new JFormattedTextField("");
            txtmno = new JFormattedTextField("");
            txtdob = new JFormattedTextField("");
            txtadds = new JFormattedTextField("");
            txtaccdate = new JFormattedTextField("");
            txtbal = new JFormattedTextField("");
            txtuserid = new JFormattedTextField("");
            txtpass = new JPasswordField("");

            frame.getContentPane().setLayout(null);
            addComponentToPane(frame.getContentPane(), lblHeading, 600, 20, 0, 0);
            addComponentToPane(frame.getContentPane(), lblsubHead, 600, 90, 0, 0);
            addComponentToPane(frame.getContentPane(), lblId, 500, 150, 0, 0);
            addComponentToPane(frame.getContentPane(), lblName, 500, 200, 0, 0);
            addComponentToPane(frame.getContentPane(), lblmno, 500, 250, 0, 0);
            addComponentToPane(frame.getContentPane(), lbldob, 500, 300, 0, 0);
            addComponentToPane(frame.getContentPane(), lbladds, 500, 350, 0, 0);
            addComponentToPane(frame.getContentPane(), lblaccdate, 500, 400, 0, 0);
            addComponentToPane(frame.getContentPane(), lblbal, 500, 450, 0, 0);
            addComponentToPane(frame.getContentPane(), lbluserid, 500, 500, 0, 0);
            addComponentToPane(frame.getContentPane(), lblpass, 500, 550, 0, 0);

            addComponentToPane(frame.getContentPane(), btnCreate, 390, 600, 50, 0, "create");
            addComponentToPane(frame.getContentPane(), btnTrans, 600, 600, 50, 0, "Transaction");
            addComponentToPane(frame.getContentPane(), btnExit, 800, 600, 50, 0, "exit");
            addComponentToPane(frame.getContentPane(), btnSave, 500, 650, 50, 0, "save");
            addComponentToPane(frame.getContentPane(), btnCancel, 700, 650, 50, 0, "Cancel");

            addComponentToPane(frame.getContentPane(), txtId, 700, 150, 100, 0);
            addComponentToPane(frame.getContentPane(), txtName, 700, 200, 100, 0);
            addComponentToPane(frame.getContentPane(), txtmno, 700, 250, 100, 0);
            addComponentToPane(frame.getContentPane(), txtdob, 700, 300, 100, 0);
            addComponentToPane(frame.getContentPane(), txtadds, 700, 350, 100, 0);
            addComponentToPane(frame.getContentPane(), txtaccdate, 700, 400, 100, 0);
            addComponentToPane(frame.getContentPane(), txtbal, 700, 450, 100, 0);
            addComponentToPane(frame.getContentPane(), txtuserid, 700, 500, 100, 0);
            addComponentToPane(frame.getContentPane(), txtpass, 700, 550, 100, 0);
            enable_disable(false);

        } catch (Exception e)
        {
            System.out.println("error in displaying controls: " + e.getMessage());
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

    public static void addComponentToPane(Container pane, JLabel paneControl, int l, int t, int w, int h)
    {
        Insets insets = pane.getInsets();
        Dimension size = paneControl.getPreferredSize();
        size = paneControl.getPreferredSize();
        paneControl.setBounds(l + insets.left, t + insets.top, size.width + w, size.height + h);
        pane.add(paneControl);
    }

    public static void addComponentToPane(Container pane, JFormattedTextField paneControl, int l, int t, int w, int h)
    {
        Insets insets = pane.getInsets();
        Dimension size = paneControl.getPreferredSize();
        size = paneControl.getPreferredSize();
        paneControl.setBounds(l + insets.left, t + insets.top, size.width + w, size.height + h);
        pane.add(paneControl);
    }
    public static void addComponentToPane(Container pane, JPasswordField paneControl, int l, int t, int w, int h)
    {
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

            if ("create".equals(e.getActionCommand()))
            {
                clearText();
                actionFlag = 0;
                enable_disable(true);
                txtId.setEnabled(true);
                txtName.setEnabled(true);
                txtmno.setEnabled(true);
                txtdob.setEnabled(true);
                txtadds.setEnabled(true);
                txtaccdate.setEnabled(true);
                txtbal.setEnabled(true);
                txtuserid.setEnabled(true);
                txtpass.setEnabled(true);
            }
            if ("Transaction".equals(e.getActionCommand()))
            {
                transform tr = new transform();
                frame.setVisible(false);
            }
            if ("save".equals(e.getActionCommand())) {
                try {
                    if (actionFlag == 0)
                    {
                        enable_disable(false);
                        accwork();
                        JOptionPane.showMessageDialog(frame, "Account Created");
                        clearText();
                        login l = new login();
                        frame.setVisible(false);
                    }
                }
                catch (Exception e2)
                {
                    JOptionPane.showMessageDialog(frame, e2.getMessage());
                }
            }
            if ("Cancel".equals(e.getActionCommand()))
            {
                enable_disable(false);
                JOptionPane.showMessageDialog(frame, "Cancelled");
            }

        }
    }

    static private void enable_disable(boolean tf) {
        txtId.setEnabled(tf);
        txtName.setEnabled(tf);
        txtmno.setEnabled(tf);
        txtdob.setEnabled(tf);
        txtadds.setEnabled(tf);
        txtaccdate.setEnabled(tf);
        txtbal.setEnabled(tf);
        txtuserid.setEnabled(tf);
        txtpass.setEnabled(tf);
        btnCancel.setEnabled(tf);
        btnSave.setEnabled(tf);
        btnCreate.setEnabled(!tf);
        btnTrans.setEnabled(!tf);
        btnExit.setEnabled(!tf);
    }

    static private void clearText() {
        txtId.setText("");
        txtName.setText("");
        txtmno.setText("");
        txtdob.setText("");
        txtadds.setText("");
        txtaccdate.setText("");
        txtbal.setText("");
        txtuserid.setText("");
        txtpass.setText("");
    }

    public static void main(String arg[])
    {
        javax.swing.SwingUtilities.invokeLater(accreate::createAndShowGUI);
    }

    public static void accwork() {
        try
        {
            connection mycon2 = new connection();
            Connection myCon =  mycon2.getConn();
            start = myCon.createStatement();

            String sqlstring = "insert into account_master (cid,cname,mno,dob,adds,acc_date,bal,userid,pass) values (" + txtId.getText() + " ,'" + txtName.getText() +"'," + txtmno.getText() +",'" + txtdob.getText() +"','" + txtadds.getText() +"','" + txtaccdate.getText() +"'," + txtbal.getText() +",'" + txtuserid.getText() + "','" + txtpass.getText().toString() + "')";
            start.executeUpdate(sqlstring);
            System.out.println(sqlstring);
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
