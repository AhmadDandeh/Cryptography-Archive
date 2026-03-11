package com.ahmad.crypto.gsm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * GSM A5/1 Stream Cipher Implementation
 */
public class A5Fun {
    // Registers (LFSRs)
    public boolean[] r1 = new boolean[19];
    public boolean[] r2 = new boolean[22];
    public boolean[] r3 = new boolean[23];

    // Help Registers for State Preservation
    private boolean[] r1_help = new boolean[19];
    private boolean[] r2_help = new boolean[22];
    private boolean[] r3_help = new boolean[23];

    public boolean[] session_key = new boolean[64];
    private String session_key_str = "0100111000101111010011010111110000011110101110001000101100111010";

    public boolean[] frame_counter = new boolean[22];
    public String frame_counter_str = "0000000000000000000001";

    public boolean[] key_stream;
    public boolean[] aud_bool;
    public boolean[] text_cipher;
    public boolean[] text_origin;
    public int aud_bool_len;

    public A5Fun() {
        initSessionKey();
        zeroReg(r1);
        zeroReg(r2);
        zeroReg(r3);
    }

    // --- Main Cryptographic Steps ---

    public void A5Enc(String filePath) {
        step2Cycle64();
        loadAudioFile(filePath);

        // Process in 228-bit bursts (GSM Standard)
        for (int m = 0; m < aud_bool.length / 228; m++) {
            step3Cycle22();
            step4Cycle100();
            step5Cycle228(m);
        }
        xorTextKey();
    }

    public void A5Desc() {
        xorCipherKey();
    }

    // --- Initialization & Setup ---

    private void initSessionKey() {
        for (int i = 0; i < session_key_str.length(); i++) {
            session_key[i] = (session_key_str.charAt(i) == '1');
        }
    }

    public void initFrameCounter() {
        for (int i = 0; i < frame_counter_str.length(); i++) {
            frame_counter[i] = (frame_counter_str.charAt(i) == '1');
        }
    }

    public void incFrameCounter() {
        int val = Integer.parseInt(frame_counter_str, 2);
        val = (val + 1) % 4194304; // 2^22 limit
        frame_counter_str = String.format("%22s", Integer.toBinaryString(val)).replace(' ', '0');
    }

    private void zeroReg(boolean[] reg) {
        java.util.Arrays.fill(reg, false);
    }

    // --- LFSR Operations ---

    private boolean getLFSR1Xor() { return r1[13] ^ r1[16] ^ r1[17] ^ r1[18]; }
    private boolean getLFSR2Xor() { return r2[20] ^ r2[21]; }
    private boolean getLFSR3Xor() { return r3[7] ^ r3[20] ^ r3[21] ^ r3[22]; }

    private void shiftRight(boolean[] arr) {
        System.arraycopy(arr, 0, arr, 1, arr.length - 1);
    }

    // --- A5/1 Specific Cycles ---

    public void step2Cycle64() {
        for (boolean b : session_key) {
            boolean r1_in = b ^ getLFSR1Xor();
            boolean r2_in = b ^ getLFSR2Xor();
            boolean r3_in = b ^ getLFSR3Xor();

            shiftRight(r1); r1[0] = r1_in;
            shiftRight(r2); r2[0] = r2_in;
            shiftRight(r3); r3[0] = r3_in;
        }
        System.arraycopy(r1, 0, r1_help, 0, r1.length);
        System.arraycopy(r2, 0, r2_help, 0, r2.length);
        System.arraycopy(r3, 0, r3_help, 0, r3.length);
    }

    public void step3Cycle22() {
        System.arraycopy(r1_help, 0, r1, 0, r1.length);
        System.arraycopy(r2_help, 0, r2, 0, r2.length);
        System.arraycopy(r3_help, 0, r3, 0, r3.length);
        initFrameCounter();

        for (boolean b : frame_counter) {
            boolean r1_in = b ^ getLFSR1Xor();
            boolean r2_in = b ^ getLFSR2Xor();
            boolean r3_in = b ^ getLFSR3Xor();

            shiftRight(r1); r1[0] = r1_in;
            shiftRight(r2); r2[0] = r2_in;
            shiftRight(r3); r3[0] = r3_in;
        }
        incFrameCounter();
    }

    private boolean majorityFunction() {
        int count = (r1[8] ? 1 : 0) + (r2[10] ? 1 : 0) + (r3[10] ? 1 : 0);
        return count >= 2;
    }

    public void step4Cycle100() {
        for (int i = 0; i < 100; i++) {
            boolean maj = majorityFunction();
            if (r1[8] == maj) { boolean in = getLFSR1Xor(); shiftRight(r1); r1[0] = in; }
            if (r2[10] == maj) { boolean in = getLFSR2Xor(); shiftRight(r2); r2[0] = in; }
            if (r3[10] == maj) { boolean in = getLFSR3Xor(); shiftRight(r3); r3[0] = in; }
        }
    }

    public void step5Cycle228(int burstIndex) {
        for (int i = 0; i < 228; i++) {
            boolean maj = majorityFunction();
            key_stream[i + burstIndex * 228] = r1[18] ^ r2[21] ^ r3[22];

            if (r1[8] == maj) { boolean in = getLFSR1Xor(); shiftRight(r1); r1[0] = in; }
            if (r2[10] == maj) { boolean in = getLFSR2Xor(); shiftRight(r2); r2[0] = in; }
            if (r3[10] == maj) { boolean in = getLFSR3Xor(); shiftRight(r3); r3[0] = in; }
        }
    }

    // --- Data Conversion & I/O ---

    public void loadAudioFile(String path) {
        try {
            byte[] byteArr = Files.readAllBytes(Paths.get(path));
            int padding = 228 - (byteArr.length * 8 % 228);
            this.aud_bool_len = byteArr.length * 8;
            this.aud_bool = new boolean[aud_bool_len + padding];
            this.text_cipher = new boolean[aud_bool.length];
            this.text_origin = new boolean[aud_bool.length];
            this.key_stream = new boolean[aud_bool.length];

            for (int i = 0; i < byteArr.length; i++) {
                for (int bit = 0; bit < 8; bit++) {
                    aud_bool[i * 8 + bit] = ((byteArr[i] >> (7 - bit)) & 1) == 1;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading audio file: " + e.getMessage());
        }
    }

    public void xorTextKey() {
        for (int i = 0; i < aud_bool.length; i++) {
            text_cipher[i] = aud_bool[i] ^ key_stream[i];
        }
    }

    public void xorCipherKey() {
        for (int i = 0; i < aud_bool_len; i++) {
            text_origin[i] = text_cipher[i] ^ key_stream[i];
        }
    }

    public byte[] convertBoolToBytes(boolean[] boolArr) {
        byte[] bytes = new byte[aud_bool_len / 8];
        for (int i = 0; i < bytes.length; i++) {
            int val = 0;
            for (int bit = 0; bit < 8; bit++) {
                if (boolArr[i * 8 + bit]) {
                    val |= (1 << (7 - bit));
                }
            }
            bytes[i] = (byte) val;
        }
        return bytes;
    }

    // أضف هذه الدوال داخل كلاس A5Fun في النهاية
    public void set_session_key(String keyStr) {
        for (int i = 0; i < Math.min(64, keyStr.length()); i++) {
            session_key[i] = (keyStr.charAt(i) == '1');
        }
    }

    public void set_frame_counter(String frameStr) {
        this.frame_counter_str = frameStr;
        for (int i = 0; i < Math.min(22, frameStr.length()); i++) {
            frame_counter[i] = (frameStr.charAt(i) == '1');
        }
    }

    // دالة مساعدة لتحويل مصفوفة الـ boolean إلى byte[] للعرض
    public byte[] conBool2Aud2(boolean[] boolArr) {
        return convertBoolToBytes(boolArr);
    }
}