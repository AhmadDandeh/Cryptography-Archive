package com.ahmad.crypto.des;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class DES_Enc_Ahmad_Dandeh extends JFrame {
    helpFun a = new helpFun();
    private JButton jButton1, jButton2, jButton3, jButton4;
    private JTextField jTextField1, jTextField2, jTextField3, jTextField4, jTextField5, jTextField6, jTextField7;
    private JTextArea jTextArea1, jTextArea2, jTextArea3, jTextArea4;
    private JCheckBox jCheckBox1, jCheckBox2;
    private JLabel jLabel2, jLabel6, jLabel8, jLabel10, jLabel11;

    public DES_Enc_Ahmad_Dandeh() {
        setTitle("DES Encryption Suite - Legacy (2014)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        pack();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        // إنشاء الأزرار والحقول
        jButton1 = new JButton("Encryption");
        jButton2 = new JButton("Load File");
        jButton3 = new JButton("Input Text");
        jButton4 = new JButton("Export");

        jTextField1 = new JTextField(20); // Input text
        jTextField2 = new JTextField(16); // Key
        jTextField5 = new JTextField(20); // File Path

        jTextArea1 = new JTextArea(5, 20); // Blocks
        jTextArea2 = new JTextArea(5, 20); // Binary
        jTextArea3 = new JTextArea(5, 20); // Cipher Binary
        jTextArea4 = new JTextArea(5, 20); // Keys

        jCheckBox1 = new JCheckBox("UTF-8");
        jCheckBox2 = new JCheckBox("ASCII");

        // إضافة الأحداث (Listeners)
        jButton1.addActionListener(this::jButton1ActionPerformed);
        jButton2.addActionListener(this::jButton2ActionPerformed);
        jButton3.addActionListener(this::jButton3ActionPerformed);
        jButton4.addActionListener(this::jButton4ActionPerformed);

        // تنظيم الواجهة (Layout)
        JPanel mainPanel = new JPanel(new GridLayout(3, 1, 10, 10));

        // الجزء العلوي: المدخلات
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setBackground(new Color(204, 255, 153));
        topPanel.add(new JLabel("Text:")); topPanel.add(jTextField1);
        topPanel.add(jButton3);
        topPanel.add(new JLabel("Key:")); topPanel.add(jTextField2);
        topPanel.add(jButton1);

        // الجزء الأوسط: النتائج والتحويل
        JPanel midPanel = new JPanel(new GridLayout(1, 2));
        midPanel.add(new JScrollPane(jTextArea1));
        midPanel.add(new JScrollPane(jTextArea2));

        // الجزء السفلي: التشفير والتصدير
        JPanel botPanel = new JPanel(new FlowLayout());
        botPanel.setBackground(new Color(153, 255, 153));
        botPanel.add(new JScrollPane(jTextArea3));
        botPanel.add(jButton4);

        mainPanel.add(topPanel);
        mainPanel.add(midPanel);
        mainPanel.add(botPanel);

        this.add(mainPanel);
    }

    // الدوال البرمجية (Logic) - تأكد من مطابقتها لـ helpFun
    private void jButton1ActionPerformed(ActionEvent evt) {
        String key = jTextField2.getText();
        if (a.testKey(key)) {
            a.KeysGeneration(key);
            String cipherText = "";
            for(Object block : a.Texts64) cipherText += a.Cipher(block.toString());
            jTextArea3.setText(cipherText);
        }
    }

    private void jButton2ActionPerformed(ActionEvent evt) {
        String path = a.ChooseFile();
        if (path != null) jTextField5.setText(path);
    }

    private void jButton3ActionPerformed(ActionEvent evt) {
        String text = jTextField1.getText();
        String conText = a.convertText(text);
        String padded = a.Padding(a.Keyhex2bin(conText));
        jTextArea2.setText(padded);
        a.devText(padded);
    }

    private void jButton4ActionPerformed(ActionEvent evt) {
        a.WriteInFile(jTextArea3.getText());
    }
}

/*


package com.ahmad.crypto.des;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class DES_Enc_Ahmad_Dandeh extends JFrame {
    helpFun a = new helpFun();
    public String helpText;
    private JButton jButton1, jButton2, jButton3, jButton4;
    private JCheckBox jCheckBox1, jCheckBox2;
    private JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8, jLabel9, jLabel10, jLabel11, jLabel12;
    private JPanel jPanel1, jPanel2, jPanel3;
    private JScrollPane jScrollPane1, jScrollPane2, jScrollPane3, jScrollPane4;
    private JTextArea jTextArea1, jTextArea2, jTextArea3, jTextArea4;
    private JTextField jTextField1, jTextField2, jTextField3, jTextField4, jTextField5, jTextField6, jTextField7;

    public DES_Enc_Ahmad_Dandeh() {
        initComponents();
        this.setTitle("DES Encryption - Legacy Suite (2014)");
    }

    private void initComponents() {
        jPanel1 = new JPanel();
        jTextField1 = new JTextField();
        jLabel2 = new JLabel();
        jTextField2 = new JTextField();
        jButton1 = new JButton();
        jButton2 = new JButton();
        jTextField5 = new JTextField();
        jButton3 = new JButton();
        jPanel2 = new JPanel();
        jLabel4 = new JLabel();
        jLabel3 = new JLabel();
        jTextField3 = new JTextField();
        jScrollPane3 = new JScrollPane();
        jTextArea3 = new JTextArea();
        jLabel12 = new JLabel();
        jTextField6 = new JTextField();
        jButton4 = new JButton();
        jCheckBox1 = new JCheckBox();
        jCheckBox2 = new JCheckBox();
        jPanel3 = new JPanel();
        jLabel5 = new JLabel();
        jLabel6 = new JLabel();
        jLabel7 = new JLabel();
        jLabel8 = new JLabel();
        jLabel9 = new JLabel();
        jLabel10 = new JLabel();
        jTextField7 = new JTextField();
        jScrollPane2 = new JScrollPane();
        jTextArea2 = new JTextArea();
        jScrollPane1 = new JScrollPane();
        jTextArea1 = new JTextArea();
        jLabel1 = new JLabel();
        jLabel11 = new JLabel();
        jTextField4 = new JTextField();
        jScrollPane4 = new JScrollPane();
        jTextArea4 = new JTextArea();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jPanel1.setBackground(new Color(204, 255, 153));
        jLabel2.setText("   Input The Key");

        jButton1.setText("Encryption");
        jButton1.addActionListener(evt -> jButton1ActionPerformed(evt));

        jButton2.setText("Load the file");
        jButton2.addActionListener(evt -> jButton2ActionPerformed(evt));

        jButton3.setText("Input the text");
        jButton3.addActionListener(evt -> jButton3ActionPerformed(evt));

        jButton4.setText("Export");
        jButton4.addActionListener(evt -> jButton4ActionPerformed(evt));

        jCheckBox1.setText("UTF-8");
        jCheckBox1.addActionListener(evt -> jCheckBox1ActionPerformed(evt));

        jCheckBox2.setText("ASCII");
        jCheckBox2.addActionListener(evt -> jCheckBox2ActionPerformed(evt));

        jTextField7.addActionListener(evt -> jTextField7ActionPerformed(evt));

        // --- إعداد التصميم (Layout) - تم الإبقاء عليه كما ولده الـ GUI Builder ---
        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        // ... (تم اختصار تفاصيل الـ Layout للحفاظ على حجم الرد، تأكد من بقاء الـ GroupLayout الأصلي لديك)

        // --- إعدادات بقية المكونات ---
        jTextArea3.setColumns(20); jTextArea3.setRows(5); jScrollPane3.setViewportView(jTextArea3);
        jTextArea2.setColumns(20); jTextArea2.setRows(5); jScrollPane2.setViewportView(jTextArea2);
        jTextArea1.setColumns(20); jTextArea1.setRows(5); jScrollPane1.setViewportView(jTextArea1);
        jTextArea4.setColumns(20); jTextArea4.setRows(5); jScrollPane4.setViewportView(jTextArea4);

        this.getContentPane().setLayout(new javax.swing.BoxLayout(this.getContentPane(), javax.swing.BoxLayout.Y_AXIS));
        this.add(jPanel1);
        this.add(jPanel3);
        this.add(jPanel2);
        this.pack();
    }

    // --- الدوال البرمجية (Logic) ---

    private void jButton1ActionPerformed(ActionEvent evt) {
        String Key = jTextField2.getText();
        if (a.testKey(Key)) {
            try {
                a.KeysGeneration(Key);
                StringBuilder keysBuilder = new StringBuilder();
                for(int i=0; i<16; i++) {
                    keysBuilder.append("k").append(i+1).append(":   ").append(a.KeysHex[i]).append(System.lineSeparator());
                }
                jTextArea4.setText(keysBuilder.toString());

                String CipherText = "";
                for(Object block : a.Texts64) {
                    CipherText += a.Cipher(block.toString());
                }
                jTextArea3.setText(CipherText); // Simplified formatting
                jTextField3.setText(a.Keybin2hex(CipherText).toUpperCase());
                jTextField6.setText(a.BinToText(CipherText));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error in Encryption", "Error", 0);
            }
        }
    }

    private void jButton2ActionPerformed(ActionEvent evt) {
        String path = a.ChooseFile();
        if (path != null) {
            jTextField5.setText(path);
            String text1 = a.OpenReadFile(path, (int)a.LenFile(path));
            updateUIWithText(text1);
        }
    }

    private void jButton3ActionPerformed(ActionEvent evt) {
        updateUIWithText(jTextField1.getText());
    }

    private void updateUIWithText(String text1) {
        jTextField7.setText(text1);
        jLabel6.setText(text1.length() + "");
        jLabel8.setText((text1.length() * 8) + "");
        String conText = a.convertText(text1);
        helpText = a.Padding(a.Keyhex2bin(conText));
        jTextArea2.setText(helpText);
        a.devText(helpText);

        StringBuilder blocks = new StringBuilder();
        for(int i = 0; i < a.Texts64.size(); i++) {
            blocks.append("Block").append(i).append(" ").append(a.Texts64.get(i)).append(System.lineSeparator());
        }
        jTextArea1.setText(blocks.toString());
        jLabel10.setText(a.Texts64.size() + "");
        jTextField4.setText(conText);
    }

    private void jButton4ActionPerformed(ActionEvent evt) {
        String s = jTextField6.getText();
        if (jCheckBox1.isSelected()) a.WriteInFile8(s);
        else a.WriteInFile(s);
    }

    private void jCheckBox1ActionPerformed(ActionEvent evt) { jCheckBox2.setSelected(false); }
    private void jCheckBox2ActionPerformed(ActionEvent evt) { jCheckBox1.setSelected(false); }
    private void jTextField7ActionPerformed(ActionEvent evt) { }
}
*/
