package com.ahmad.crypto.whirlpool;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class WhirlpoolJFrame extends JFrame {
    helpFun f = new helpFun();
    private JButton jButton1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JTextArea jTextArea1;
    private JTextArea jTextArea2;
    private JTextField jTextField1;

    public WhirlpoolJFrame() {
        this.initComponents();
        this.setLocation(400, 100);
    }

    private void initComponents() {
        this.jPanel1 = new JPanel();
        this.jLabel1 = new JLabel();
        this.jTextField1 = new JTextField();
        this.jButton1 = new JButton();
        this.jScrollPane1 = new JScrollPane();
        this.jTextArea1 = new JTextArea();
        this.jLabel2 = new JLabel();
        this.jLabel3 = new JLabel();
        this.jScrollPane2 = new JScrollPane();
        this.jTextArea2 = new JTextArea();
        this.setDefaultCloseOperation(3);
        this.jPanel1.setBackground(new Color(51, 255, 255));
        this.jLabel1.setText("The Text");
        this.jButton1.setText("Whirlpool");
        this.jButton1.addActionListener(evt -> {
            jButton1ActionPerformed(evt);
        });
        this.jTextArea1.setColumns(20);
        this.jTextArea1.setRows(5);
        this.jScrollPane1.setViewportView(this.jTextArea1);
        this.jLabel2.setText("Digest In Hex");
        this.jLabel3.setText("Digest In Binary");
        this.jTextArea2.setColumns(20);
        this.jTextArea2.setRows(5);
        this.jScrollPane2.setViewportView(this.jTextArea2);
        GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false).addComponent(this.jLabel3, -1, 90, 32767).addComponent(this.jLabel2, -1, -1, 32767)).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false).addComponent(this.jScrollPane1, -1, 530, 32767).addComponent(this.jScrollPane2))).addGroup(jPanel1Layout.createSequentialGroup().addGap(21, 21, 21).addComponent(this.jLabel1, -2, 53, -2).addPreferredGap(ComponentPlacement.RELATED).addComponent(this.jTextField1, -2, 561, -2))).addContainerGap(37, 32767)).addComponent(this.jButton1, -1, -1, 32767));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE).addComponent(this.jTextField1, -2, 51, -2).addComponent(this.jLabel1, -2, 31, -2)).addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.jButton1, -2, 33, -2).addPreferredGap(ComponentPlacement.RELATED).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jScrollPane1, -2, 89, -2).addComponent(this.jLabel2, -2, 89, -2)).addGap(18, 18, 18).addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING).addComponent(this.jScrollPane2, -1, 123, 32767).addComponent(this.jLabel3, -1, -1, 32767)).addContainerGap()));
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.jPanel1, -1, -1, 32767));
        layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(this.jPanel1, -1, -1, 32767));
        this.pack();
    }

    private String textFormat32(String ss) {
        String sss = "";

        for(int i = 0; i < ss.length(); ++i) {
            sss = sss + ss.charAt(i);
            if (i % 4 == 3) {
                sss = sss + " ";
            }

            if (i % 64 == 63) {
                sss = sss + "\n";
            }
        }

        return sss;
    }

    private void jButton1ActionPerformed(ActionEvent evt) {
        try {
            String[][] test_state = new String[8][8];
            String z = this.jTextField1.getText();
            byte[] b = z.getBytes("UTF-8");
            String ss = "";

            for(int i = 0; i < b.length; ++i) {
                ss = ss + this.f.binPaddingUTF(this.f.conIntBin(b[i]));
            }

            test_state = this.f.whirlpoolAlgo(ss);
            this.jTextArea1.setText(this.textFormat32(this.f.desc_state_hex(test_state)));
            this.jTextArea2.setText(this.textFormat32(this.f.desc_state_bin(test_state)));
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(WhirlpoolJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
        }

    }

    public static void main(String[] args) {
        try {
            for(UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(WhirlpoolJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(WhirlpoolJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(WhirlpoolJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(WhirlpoolJFrame.class.getName()).log(Level.SEVERE, (String)null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new WhirlpoolJFrame().setVisible(true);
        });
    }
}
