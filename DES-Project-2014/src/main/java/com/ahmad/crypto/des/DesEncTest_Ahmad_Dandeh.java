package com.ahmad.crypto.des;

import javax.swing.SwingUtilities;

public class DesEncTest_Ahmad_Dandeh {
    public static void main(String[] args) {
        // استخدام SwingUtilities لضمان تشغيل الواجهة في المسار الصحيح
        SwingUtilities.invokeLater(() -> {
            DES_Enc_Ahmad_Dandeh app = new DES_Enc_Ahmad_Dandeh();
            app.setVisible(true);
        });
    }
}

/*
package com.ahmad.crypto.des;

import javax.swing.UIManager;
import java.awt.EventQueue;

public class DesEncTest_Ahmad_Dandeh {
    public static void main(String[] args) {
        try {
            // تفعيل شكل Nimbus العصري
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        EventQueue.invokeLater(() -> {
            try {
                // إنشاء النافذة وإظهارها
                DES_Enc_Ahmad_Dandeh frame = new DES_Enc_Ahmad_Dandeh();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null); // لفتح النافذة في منتصف الشاشة
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
*/
