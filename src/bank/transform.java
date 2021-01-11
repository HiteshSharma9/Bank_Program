package bank;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class transform
{ // begin of form
    static JLabel lblHeading,lblsubhead;
    static JLabel lblTid, lblDr, lblCr, lbldate, lblCid, lblremarks;
    static JFormattedTextField txtTid,txtDr, txtCr, txtdate, txtCid, txtremarks;
    static JFrame frame;
    static JButton btnExit, btnDebit, btnCredit, btnSave, btnCancel;
    static Color btnColor;
    static int actionFlag,t,c;
    static Statement start,s1;
    static String b;

    public transform()
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
            lblsubhead = new JLabel("TRANSACTION MANAGER");
            lblTid = new JLabel("Transaction ID");
            lblCid = new JLabel("Account Id");
            lblDr = new JLabel("Debit");
            lblCr = new JLabel("Credit");
            lbldate = new JLabel("Transaction Date");
            lblremarks = new JLabel("Remarks");

            // button
            btnExit = new JButton("Exit");
            btnDebit = new JButton("Debit");
            btnCredit = new JButton("Credit");
            btnSave = new JButton("Save");
            btnCancel = new JButton("Cancel");

            actionFlag = 0;
            txtTid = new JFormattedTextField("");
            txtCid = new JFormattedTextField("");
            txtDr = new JFormattedTextField("");
            txtCr = new JFormattedTextField("");
            txtdate = new JFormattedTextField("");
            txtremarks = new JFormattedTextField("");

            frame.getContentPane().setLayout(null);
            addComponentToPane(frame.getContentPane(), lblHeading, 600, 20, 0, 0);
            addComponentToPane(frame.getContentPane(), lblsubhead, 600, 90, 0, 0);
            addComponentToPane(frame.getContentPane(), lblTid, 500, 200, 0, 0);
            addComponentToPane(frame.getContentPane(), lblCid, 500, 250, 0, 0);
            addComponentToPane(frame.getContentPane(), lblDr, 500, 300, 0, 0);
            addComponentToPane(frame.getContentPane(), lblCr, 500, 350, 0, 0);
            addComponentToPane(frame.getContentPane(), lbldate, 500, 400, 0, 0);
            addComponentToPane(frame.getContentPane(), lblremarks, 500, 450, 0, 0);

            addComponentToPane(frame.getContentPane(), btnDebit, 400, 600, 50, 0, "dr");
            addComponentToPane(frame.getContentPane(), btnCredit, 600, 600, 50, 0, "cr");
            addComponentToPane(frame.getContentPane(), btnExit, 800, 600, 50, 0, "exit");
            addComponentToPane(frame.getContentPane(), btnSave, 500, 650, 50, 0, "save");
            addComponentToPane(frame.getContentPane(), btnCancel, 700, 650, 50, 0, "Cancel");

            addComponentToPane(frame.getContentPane(), txtTid, 700, 200, 100, 0);
            addComponentToPane(frame.getContentPane(), txtCid, 700, 250, 100, 0);
            addComponentToPane(frame.getContentPane(), txtDr, 700, 300, 100, 0);
            addComponentToPane(frame.getContentPane(), txtCr, 700, 350, 100, 0);
            addComponentToPane(frame.getContentPane(), txtdate, 700, 400, 100, 0);
            addComponentToPane(frame.getContentPane(), txtremarks, 700, 450, 100, 0);
            enable_disable(false);

        }
        catch (Exception e)
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

    private static class myAction implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            if ("exit".equals(e.getActionCommand()))
            {
                System.exit(0);
            }
            if ("dr".equals(e.getActionCommand()))
            {
                actionFlag = 1;
                enable_disable(true);
                txtTid.setEnabled(true);
                txtDr.setEnabled(true);
                txtCr.setEnabled(false);
                btnExit.setEnabled(true);
            }
            if ("cr".equals(e.getActionCommand()))
            {
                actionFlag = 2;
                enable_disable(true);
                txtTid.setEnabled(true);
                txtCr.setEnabled(true);
                txtDr.setEnabled(false);
                btnExit.setEnabled(true);
            }
            if ("save".equals(e.getActionCommand()))
            {
                try {
                    if (actionFlag == 1)
                    {
                        enable_disable(false);
                        transwork();
                        JOptionPane.showMessageDialog(frame, "Amount Debited is "+ (txtDr.getText()));
                        clearText();
                    }
                    else if (actionFlag == 2)
                    {
                        enable_disable(false);
                        transwork();
                        JOptionPane.showMessageDialog(frame, "Amount Credited is "+ (txtCr.getText()));
                        clearText();
                    }
                    JOptionPane.showMessageDialog(frame, "Transaction Successful");
                    //System.out.println("Transaction Successful");
                }
                catch (Exception e2)
                {
                    JOptionPane.showMessageDialog(frame, e2.getMessage());
                }
            }
            if ("Cancel".equals(e.getActionCommand())) {
                enable_disable(false);
                JOptionPane.showMessageDialog(frame, "Cancelled");
            }

        }

    }

    static private void enable_disable(boolean tf) {
        txtTid.setEnabled(tf);
        txtCid.setEnabled(tf);
        txtDr.setEnabled(tf);
        txtCr.setEnabled(tf);
        txtdate.setEnabled(tf);
        txtremarks.setEnabled(tf);
        btnCancel.setEnabled(tf);
        btnSave.setEnabled(tf);
        btnCredit.setEnabled(!tf);
        btnDebit.setEnabled(!tf);
        btnExit.setEnabled(!tf);
    }

    static private void clearText() {
        txtTid.setText("");
        txtCid.setText("");
        txtDr.setText("");
        txtCr.setText("");
        txtdate.setText("");
        txtremarks.setText("");
    }

    public static void main(String arg[])
    {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });

    }

    public static void transwork() {
        try {
            connection mycon3 = new connection();
            Connection myCon =  mycon3.getConn();
            start = myCon.createStatement();
            s1 = myCon.createStatement();
            String sqlstring;
            if (actionFlag == 1)
            {
                sqlstring = "insert into transaction(tid,tdate,cid,amt,remarks) values("+ txtTid.getText() +",'"+ txtdate.getText() +"',"+ txtCid.getText() +","+ txtDr.getText() +",'"+ txtremarks.getText() +"')";
                b=txtDr.getText();
                c=Integer.parseInt(txtCid.getText());
                start.executeUpdate(sqlstring);
                t=Integer.parseInt(b);
                s1.executeUpdate("update account_master set bal=bal-"+t+" where account_master.cid="+c+"");
                System.out.println("done");
            }
            if (actionFlag == 2)
            {
                sqlstring = "insert into transaction(tid,tdate,cid,amt,remarks) values("+ txtTid.getText() +",'"+ txtdate.getText() +"',"+ txtCid.getText() +","+ txtCr.getText() +",'"+ txtremarks.getText() +"')";
                start.executeUpdate(sqlstring);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
