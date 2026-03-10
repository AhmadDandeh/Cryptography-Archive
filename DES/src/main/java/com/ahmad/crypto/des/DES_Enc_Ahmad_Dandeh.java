package com.ahmad.crypto.des;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class DES_Enc_Ahmad_Dandeh extends JFrame {
    // نسخة من كلاس العمليات للوصول للدوال الرياضية
    helpFun a = new helpFun();

    // تعريف عناصر الواجهة الرسومية
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
        // إنشاء الأزرار
        jButton1 = new JButton("Encryption");
        jButton2 = new JButton("Load File");
        jButton3 = new JButton("Input Text");
        jButton4 = new JButton("Export");

        // إنشاء حقول النص (Input & Key)
        jTextField1 = new JTextField(20); // لحقل النص المراد تشفيره
        jTextField2 = new JTextField(16); // لحقل المفتاح (يجب أن يكون 16 حرف Hex)
        jTextField5 = new JTextField(20); // لحقل مسار الملف

        // إنشاء مناطق النص (Output areas)
        jTextArea1 = new JTextArea(5, 20); // لعرض حالة البلوكات
        jTextArea2 = new JTextArea(5, 20); // لعرض النص بصيغة Binary بعد الـ Padding
        jTextArea3 = new JTextArea(5, 20); // لعرض النص المشفر النهائي
        jTextArea4 = new JTextArea(5, 20); // لعرض المفاتيح الـ 16 المولدة

        // ربط الأزرار بالدوال البرمجية (Event Listeners)
        jButton1.addActionListener(this::jButton1ActionPerformed);
        jButton2.addActionListener(this::jButton2ActionPerformed);
        jButton3.addActionListener(this::jButton3ActionPerformed);
        jButton4.addActionListener(this::jButton4ActionPerformed);

        // تنسيق الواجهة (Layout) بناءً على التصميم الأخضر في الصورة
        this.jPanel1 = new JPanel(new BorderLayout(10, 10));

        // الجزء العلوي (Input & Key)
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setBackground(new Color(204, 255, 153)); // اللون الأخضر الفاتح
        topPanel.add(new JLabel("Text:")); topPanel.add(jTextField1);
        topPanel.add(jButton3);
        topPanel.add(new JLabel("Key:")); topPanel.add(jTextField2);
        topPanel.add(jButton1);

        // الجزء الأوسط (Binary & Blocks Display)
        JPanel midPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        midPanel.add(new JScrollPane(jTextArea1));
        midPanel.add(new JScrollPane(jTextArea2));

        // الجزء السفلي (Result & Export)
        JPanel botPanel = new JPanel(new FlowLayout());
        botPanel.setBackground(new Color(153, 255, 153)); // اللون الأخضر الأغمق
        botPanel.add(new JScrollPane(jTextArea3));
        botPanel.add(jButton4);

        jPanel1.add(topPanel, BorderLayout.NORTH);
        jPanel1.add(midPanel, BorderLayout.CENTER);
        jPanel1.add(botPanel, BorderLayout.SOUTH);

        this.add(jPanel1);
        this.pack();
    }

    // زر التشفير (Encryption)
    private void jButton1ActionPerformed(ActionEvent evt) {
        String key = jTextField2.getText();

        // التأكد من صحة المفتاح (16 حرف Hex)
        if (a.testKey(key)) {
            // 1. توليد المفاتيح الـ 16
            a.KeysGeneration(key);

            // عرض المفاتيح في jTextArea4 لتوثيق العملية
            StringBuilder sbKeys = new StringBuilder();
            for(int i=0; i<16; i++) {
                sbKeys.append("K").append(i+1).append(": ").append(a.KeysHex[i]).append("\n");
            }
            jTextArea4.setText(sbKeys.toString());

            // 2. تشفير البلوكات المخزنة في ArrayList
            StringBuilder cipherResult = new StringBuilder();
            for(Object block : a.Texts64) {
                cipherResult.append(a.Cipher(block.toString()));
            }

            // 3. عرض النتيجة النهائية في jTextArea3
            jTextArea3.setText(cipherResult.toString());
        }
    }

    // زر اختيار الملف (Load File)
    private void jButton2ActionPerformed(ActionEvent evt) {
        String path = a.ChooseFile();
        if (path != null) {
            jTextField5.setText(path);
            int lines = a.LenFile(path);
            String content = a.OpenReadFile(path, lines);
            jTextField1.setText(content); // وضع محتوى الملف في حقل النص للبدء
        }
    }

    // زر معالجة النص (Input Text) - ضروري قبل التشفير
    private void jButton3ActionPerformed(ActionEvent evt) {
        String text = jTextField1.getText();

        // 1. تحويل النص لـ Hex (بناءً على لغة helpFun المحدودة)
        String conText = a.convertText(text.toLowerCase());

        if (!conText.equals("z")) { // التأكد أن النص لا يحتوي رموزاً غير مدعومة
            // 2. تحويل الـ Hex إلى Binary مع إضافة الـ Padding (64-bit blocks)
            String padded = a.Padding(a.Keyhex2bin(conText));

            // 3. عرض النص الثنائي وتقسيمه
            jTextArea2.setText(padded);
            a.devText(padded);
            jTextArea1.setText("Blocks Count: " + a.Texts64.size());
        }
    }

    // زر التصدير لملف (Export)
    private void jButton4ActionPerformed(ActionEvent evt) {
        String result = jTextArea3.getText();
        if(!result.isEmpty()) {
            a.WriteInFile(result);
        } else {
            JOptionPane.showMessageDialog(this, "No cipher text to export!");
        }
    }
}