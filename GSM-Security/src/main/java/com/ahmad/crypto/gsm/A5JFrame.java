package com.ahmad.crypto.gsm;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.*;
import java.io.File;

/**
 * GSM Encryption Suite - GUI Implementation
 * Reconstructed to match the required visual design (2026).
 */
public class A5JFrame extends JFrame {
    private A5Fun af = new A5Fun();
    private String filename = "";
    private String KeyC = "";

    // Components
    private JTextField txtKi, txtSres, txtKeyBinary, txtFrameCounter, txtTimeSec;
    private JTextArea areaCipher, areaPlain, areaDesc;
    private JButton btnA3, btnA8, btnOpenFile, btnRecord, btnPlay, btnA5Enc, btnOpenEnc, btnDecryption, btnOpenDesc;

    public A5JFrame() {
        initComponents();
        setupStyling();
        setupLayout();

        this.setTitle("GSM Encryption Suite - Simulation");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(950, 750);
        this.setLocationRelativeTo(null);
    }

    private void initComponents() {
        // Upper Section Fields
        txtKi = new JTextField();
        txtSres = new JTextField();
        txtSres.setEditable(false);
        btnA3 = new JButton("A3");
        btnA8 = new JButton("A8");

        // A5/1 Section Fields
        txtKeyBinary = new JTextField();
        txtFrameCounter = new JTextField();
        txtTimeSec = new JTextField();

        btnOpenFile = new JButton("OPEN FILE");
        btnRecord = new JButton("RECORD MP3 FILE");
        btnPlay = new JButton("PLAY FILE");
        btnA5Enc = new JButton("A5/1 ENCRIPTION");
        btnOpenEnc = new JButton("OPEN ENCRIPTION FILE");
        btnDecryption = new JButton("DESCRIPTION");
        btnOpenDesc = new JButton("OPEN DESCRIPTION FILE");

        areaCipher = new JTextArea(5, 20);
        areaPlain = new JTextArea(5, 20);
        areaDesc = new JTextArea(5, 20);

        // --- Logic Actions ---

        btnA3.addActionListener(e -> {
            Comp128 cc = new Comp128(txtKi.getText());
            txtSres.setText(cc.getSRES());
            txtSres.setBackground(Color.WHITE);
            this.KeyC = cc.getKeyC();
        });

        btnA8.addActionListener(e -> {
            txtKeyBinary.setText(this.KeyC);
            txtKeyBinary.setBackground(Color.WHITE);
        });

        btnOpenFile.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                filename = fc.getSelectedFile().getAbsolutePath();
            }
        });

        btnRecord.addActionListener(e -> {
            if (!txtTimeSec.getText().isEmpty()) {
                try {
                    int sec = Integer.parseInt(txtTimeSec.getText()) * 1000;
                    JavaSoundRecorder.ma(sec);
                    filename = JavaSoundRecorder.getF();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid time in seconds");
                }
            }
        });

        btnPlay.addActionListener(e -> JavaSoundRecorder.play(filename));

        btnA5Enc.addActionListener(e -> {
            try {
                af.set_session_key(txtKeyBinary.getText());
                af.set_frame_counter(txtFrameCounter.getText());
                af.A5Enc(filename);
                updateArea(areaPlain, af.convertBoolToBytes(af.aud_bool));
                updateArea(areaCipher, af.convertBoolToBytes(af.text_cipher));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Encryption Error: " + ex.getMessage());
            }
        });

        btnDecryption.addActionListener(e -> {
            try {
                af.A5Desc();
                updateArea(areaDesc, af.convertBoolToBytes(af.text_origin));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Decryption Error: " + ex.getMessage());
            }
        });
    }

    private void setupStyling() {
        Color bgColor = new Color(37, 168, 254); // Blue background from image
        Color btnColor = new Color(0, 255, 220); // Cyan/Turquoise buttons
        Font labelFont = new Font("OCR A Extended", Font.BOLD, 12);

        this.getContentPane().setBackground(bgColor);

        Component[] btns = {btnA3, btnA8, btnOpenFile, btnRecord, btnPlay, btnA5Enc, btnOpenEnc, btnDecryption, btnOpenDesc};
        for (Component c : btns) {
            c.setBackground(btnColor);
            c.setFont(labelFont);
            c.setFocusable(false);
        }
    }

    private void updateArea(JTextArea area, byte[] data) {
        StringBuilder sb = new StringBuilder();
        if (data != null) {
            for (int i = 0; i < Math.min(300, data.length); i++) {
                sb.append(String.format("%02X ", data[i])); // Display as Hex for better look
                if (i % 15 == 14) sb.append("\n");
            }
        }
        area.setText(sb.toString());
    }

    private void setupLayout() {
        Container cp = getContentPane();
        GroupLayout layout = new GroupLayout(cp);
        cp.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Pre-defining ScrollPanes as variables to avoid IllegalStateException
        JScrollPane scrollPlain = new JScrollPane(areaPlain);
        JScrollPane scrollCipher = new JScrollPane(areaCipher);
        JScrollPane scrollDesc = new JScrollPane(areaDesc);

        JLabel lblKi = new JLabel("KI IN SIM");
        JLabel lblSres = new JLabel("SRES");
        JLabel lblKeyBin = new JLabel("KEY ENCRIPTION IN BINARY");
        JLabel lblFrame = new JLabel("FRAME COUNTER IN BINARY");
        JLabel lblTime = new JLabel("TIME IN SECOND");

        // --- Horizontal Layout ---
        layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(lblKi, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                .addComponent(lblSres, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10)
                        .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                .addComponent(txtKi)
                                .addComponent(btnA3, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtSres)
                                .addComponent(btnA8, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)))

                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(lblKeyBin, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                .addComponent(lblFrame, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10)
                        .addGroup(layout.createParallelGroup(Alignment.LEADING).addComponent(txtKeyBinary).addComponent(txtFrameCounter)))

                .addGroup(layout.createSequentialGroup()
                        .addComponent(btnOpenFile).addComponent(btnRecord).addComponent(lblTime)
                        .addComponent(txtTimeSec, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE).addComponent(btnPlay))

                .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                .addComponent(scrollPlain).addComponent(scrollCipher).addComponent(scrollDesc))
                        .addGap(10)
                        .addGroup(layout.createParallelGroup(Alignment.LEADING, false)
                                .addComponent(btnA5Enc, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnOpenEnc, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnDecryption, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnOpenDesc, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        // --- Vertical Layout ---
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lblKi).addComponent(txtKi))
                .addComponent(btnA3)
                .addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lblSres).addComponent(txtSres))
                .addComponent(btnA8)
                .addGap(20)
                .addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lblKeyBin).addComponent(txtKeyBinary))
                .addGroup(layout.createParallelGroup(Alignment.BASELINE).addComponent(lblFrame).addComponent(txtFrameCounter))
                .addGap(10)
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(btnOpenFile).addComponent(btnRecord).addComponent(lblTime).addComponent(txtTimeSec).addComponent(btnPlay))
                .addGap(10)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(scrollPlain, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnA5Enc, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                .addGap(10)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(scrollCipher, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup().addComponent(btnOpenEnc).addComponent(btnDecryption)))
                .addGap(10)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                        .addComponent(scrollDesc, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnOpenDesc))
        );
    }

    public static void main(String[] args) {
        // Set Look and Feel to System to ensure transparency and colors work well
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) {}

        EventQueue.invokeLater(() -> new A5JFrame().setVisible(true));
    }
}