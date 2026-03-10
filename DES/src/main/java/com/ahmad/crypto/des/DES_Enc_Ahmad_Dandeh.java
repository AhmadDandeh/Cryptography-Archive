package com.ahmad.crypto.des;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class DES_Enc_Ahmad_Dandeh extends JFrame {
    // Instance of the helper class for cryptographic operations
    helpFun a = new helpFun();

    // GUI Components definition
    private JButton jButton1, jButton2, jButton3, jButton4;
    private JTextField jTextField1, jTextField2, jTextField5;
    private JTextArea jTextArea1, jTextArea2, jTextArea3, jTextArea4;
    private JPanel jPanel1;

    public DES_Enc_Ahmad_Dandeh() {
        initComponents();
        this.setTitle("DES Encryption Suite - Legacy (2014)");
        this.setLocation(400, 100);
    }

    private void initComponents() {
        // Initialize Buttons
        jButton1 = new JButton("Encryption");
        jButton2 = new JButton("Load File");
        jButton3 = new JButton("Input Text");
        jButton4 = new JButton("Export");

        // Initialize Text Fields (Input & Key)
        jTextField1 = new JTextField(20); // Field for text to be encrypted
        jTextField2 = new JTextField(16); // Field for the key (must be 16 Hex characters)
        jTextField5 = new JTextField(20); // Field for file path

        // Initialize Output Areas
        jTextArea1 = new JTextArea(5, 20); // Display block status
        jTextArea2 = new JTextArea(5, 20); // Display text in Binary after Padding
        jTextArea3 = new JTextArea(5, 20); // Display final Ciphertext
        jTextArea4 = new JTextArea(5, 20); // Display the 16 generated sub-keys

        // Link buttons to event handlers (Event Listeners)
        jButton1.addActionListener(this::jButton1ActionPerformed);
        jButton2.addActionListener(this::jButton2ActionPerformed);
        jButton3.addActionListener(this::jButton3ActionPerformed);
        jButton4.addActionListener(this::jButton4ActionPerformed);

        // UI Layout setup based on the original green design
        this.jPanel1 = new JPanel(new BorderLayout(10, 10));

        // Top Panel (Input & Key)
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setBackground(new Color(204, 255, 153)); // Light Green
        topPanel.add(new JLabel("Text:")); topPanel.add(jTextField1);
        topPanel.add(jButton3);
        topPanel.add(new JLabel("Key:")); topPanel.add(jTextField2);
        topPanel.add(jButton1);

        // Middle Panel (Binary & Blocks Display)
        JPanel midPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        midPanel.add(new JScrollPane(jTextArea1));
        midPanel.add(new JScrollPane(jTextArea2));

        // Bottom Panel (Result & Export)
        JPanel botPanel = new JPanel(new FlowLayout());
        botPanel.setBackground(new Color(153, 255, 153)); // Darker Green
        botPanel.add(new JScrollPane(jTextArea3));
        botPanel.add(jButton4);

        jPanel1.add(topPanel, BorderLayout.NORTH);
        jPanel1.add(midPanel, BorderLayout.CENTER);
        jPanel1.add(botPanel, BorderLayout.SOUTH);

        this.add(jPanel1);
        this.pack();
    }

    // Encryption Button logic
    private void jButton1ActionPerformed(ActionEvent evt) {
        String key = jTextField2.getText();

        // Validate key length (16 Hex characters)
        if (a.testKey(key)) {
            // 1. Generate 16 sub-keys
            a.KeysGeneration(key);

            // Display keys in jTextArea4 for process documentation
            StringBuilder sbKeys = new StringBuilder();
            for(int i=0; i<16; i++) {
                sbKeys.append("K").append(i+1).append(": ").append(a.KeysHex[i]).append("\n");
            }
            jTextArea4.setText(sbKeys.toString());

            // 2. Encrypt blocks stored in the ArrayList
            StringBuilder cipherResult = new StringBuilder();
            for(Object block : a.Texts64) {
                cipherResult.append(a.Cipher(block.toString()));
            }

            // 3. Display final result in jTextArea3
            jTextArea3.setText(cipherResult.toString());
        }
    }

    // Load File Button logic
    private void jButton2ActionPerformed(ActionEvent evt) {
        String path = a.ChooseFile();
        if (path != null) {
            jTextField5.setText(path);
            int lines = a.LenFile(path);
            String content = a.OpenReadFile(path, lines);
            jTextField1.setText(content); // Place file content into text field to begin
        }
    }

    // Input Text Button logic - Required before Encryption
    private void jButton3ActionPerformed(ActionEvent evt) {
        String text = jTextField1.getText();

        // 1. Convert text to Hex (Based on helpFun language limits)
        String conText = a.convertText(text.toLowerCase());

        if (!conText.equals("z")) { // Ensure text doesn't contain unsupported characters
            // 2. Convert Hex to Binary with Padding (64-bit blocks)
            String padded = a.Padding(a.Keyhex2bin(conText));

            // 3. Display Binary text and divide into blocks
            jTextArea2.setText(padded);
            a.devText(padded);
            jTextArea1.setText("Blocks Count: " + a.Texts64.size());
        }
    }

    // Export to File Button logic
    private void jButton4ActionPerformed(ActionEvent evt) {
        String result = jTextArea3.getText();
        if(!result.isEmpty()) {
            a.WriteInFile(result);
        } else {
            JOptionPane.showMessageDialog(this, "No cipher text to export!");
        }
    }
}