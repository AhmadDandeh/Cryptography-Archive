package com.ahmad.crypto.des;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class helpFun {
    ArrayList Texts64 = new ArrayList();
    public String[] language = new String[]{"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "0a", "0b", "0c", "0d", "0e", "0f", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "1a", "1b", "1c", "1d", "1e", "1f", "20", "21", "22", "23", "24"};
    public char[] helpLan = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    public int[][] DropT = new int[][]{{57, 49, 41, 33, 25, 17, 9, 1}, {58, 50, 42, 34, 26, 18, 10, 2}, {59, 51, 43, 35, 27, 19, 11, 3}, {60, 52, 44, 36, 63, 55, 47, 39}, {31, 23, 15, 7, 62, 54, 46, 38}, {30, 22, 14, 6, 61, 53, 45, 37}, {29, 21, 13, 5, 28, 20, 12, 4}};
    public int[] RoundK = new int[]{1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};
    public int[][] CompressionT = new int[][]{{14, 17, 11, 24, 1, 5, 3, 28}, {15, 6, 21, 10, 23, 19, 12, 4}, {26, 8, 16, 7, 27, 20, 13, 2}, {41, 52, 31, 37, 47, 55, 30, 40}, {51, 45, 33, 48, 44, 49, 39, 56}, {34, 53, 46, 42, 50, 36, 29, 32}};
    public String LiftKey;
    public String RightKey;
    public String LiftBlock;
    public String RightBlock;
    public String[] Keys = new String[16];
    public String[] KeysHex = new String[16];
    public int[][] InitialPerT = new int[][]{{58, 50, 42, 34, 26, 18, 10, 2}, {60, 52, 44, 36, 28, 20, 12, 4}, {62, 54, 46, 38, 30, 22, 14, 6}, {64, 56, 48, 40, 32, 24, 16, 8}, {57, 49, 41, 33, 25, 17, 9, 1}, {59, 51, 43, 35, 27, 19, 11, 3}, {61, 53, 45, 37, 29, 21, 13, 5}, {63, 55, 47, 39, 31, 23, 15, 7}};
    public int[][] FinalPerT = new int[][]{{40, 8, 48, 16, 56, 24, 64, 32}, {39, 7, 47, 15, 55, 23, 63, 31}, {38, 6, 46, 14, 54, 22, 62, 30}, {37, 5, 45, 13, 53, 21, 61, 29}, {36, 4, 44, 12, 52, 20, 60, 28}, {35, 3, 43, 11, 51, 19, 59, 27}, {34, 2, 42, 10, 50, 18, 58, 26}, {33, 1, 41, 9, 49, 17, 57, 25}};
    public int[][] ExpanPerT = new int[][]{{32, 1, 2, 3, 4, 5}, {4, 5, 6, 7, 8, 9}, {8, 9, 10, 11, 12, 13}, {12, 13, 14, 15, 16, 17}, {16, 17, 18, 19, 20, 21}, {20, 21, 22, 23, 24, 25}, {24, 25, 26, 27, 28, 29}, {28, 29, 30, 31, 32, 1}};
    public int[][] sBox1 = new int[][]{{14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7}, {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8}, {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0}, {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}};
    public int[][] sBox2 = new int[][]{{15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10}, {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5}, {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15}, {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}};
    public int[][] sBox3 = new int[][]{{10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8}, {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1}, {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7}, {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}};
    public int[][] sBox4 = new int[][]{{7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15}, {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9}, {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4}, {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}};
    public int[][] sBox5 = new int[][]{{2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9}, {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6}, {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14}, {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}};
    public int[][] sBox6 = new int[][]{{12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11}, {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8}, {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6}, {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}};
    public int[][] sBox7 = new int[][]{{4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1}, {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6}, {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2}, {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}};
    public int[][] sBox8 = new int[][]{{13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7}, {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2}, {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8}, {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}};
    public int[][] PBox = new int[][]{{16, 7, 20, 21, 29, 12, 28, 17}, {1, 15, 23, 26, 5, 18, 31, 10}, {2, 8, 24, 14, 32, 27, 3, 9}, {19, 13, 30, 6, 22, 11, 4, 25}};

    public String permut(String s, int[][] a, int rows) {
        String result = "";

        for(int i = 0; i < rows; ++i) {
            for(int j = 0; j < 8; ++j) {
                result = result + s.charAt(a[i][j] - 1);
            }
        }

        return result;
    }

    public void split(String s, int x) {
        if (x == 28) {
            this.LiftKey = s.substring(0, 28);
            this.RightKey = s.substring(28);
        } else if (x == 32) {
            this.LiftBlock = s.substring(0, 32);
            this.RightBlock = s.substring(32);
        }

    }

    public String combine(int x) {
        if (x == 28) {
            return this.LiftKey + this.RightKey;
        } else {
            return x == 32 ? this.LiftBlock + this.RightBlock : null;
        }
    }

    public String Keyhex2bin(String ss) {
        String s = ss.toLowerCase();
        String binKey = "";

        for(int i = 0; i < s.length(); ++i) {
            String c = s.charAt(i) + "";
            int k = Integer.parseInt(c, 16);

            for(c = Integer.toBinaryString(k); c.length() != 4; c = '0' + c) {
            }

            binKey = binKey + c;
        }

        return binKey;
    }

    public String Keybin2hex(String s) {
        String hexKey = "";

        for(int i = 0; i < s.length(); i += 4) {
            String c = s.substring(i, i + 4);
            hexKey = hexKey + Integer.toHexString(this.bin2hex(c));
        }

        return hexKey;
    }

    public int bin2hex(String s) {
        int x = 0;

        for(int i = 0; i < s.length(); ++i) {
            String cc = s.substring(s.length() - (i + 1), s.length() - i);
            x += Integer.parseInt(cc) * (int)Math.pow((double)2.0F, (double)i);
        }

        return x;
    }

    public String Keys64(String s) {
        String Key64 = "";
        if (s.length() == 16) {
            Key64 = this.Keyhex2bin(s);
        } else {
            s = s.substring(0, 16);
            Key64 = this.Keyhex2bin(s);
        }

        return Key64;
    }

    public String shiftLeft(String s, int numOfShifts) {
        String ss = "";

        for(int i = 0; i < numOfShifts; ++i) {
            ss = "";
            char t = s.charAt(0);

            for(int j = 1; j < 28; ++j) {
                ss = ss + s.charAt(j);
            }

            ss = ss + t;
            s = ss;
        }

        return ss;
    }

    public void KeysHex() {
        for(int i = 0; i < 16; ++i) {
            this.KeysHex[i] = this.Keybin2hex(this.Keys[i]);
        }

    }

    public void KeysGeneration(String s) {
        this.split(this.permut(this.Keys64(s), this.DropT, 7), 28);

        for(int k = 0; k < 16; ++k) {
            this.LiftKey = this.shiftLeft(this.LiftKey, this.RoundK[k]);
            this.RightKey = this.shiftLeft(this.RightKey, this.RoundK[k]);
            this.Keys[k] = this.permut(this.combine(28), this.CompressionT, 6);
        }

        this.KeysHex();
    }

    public void mixer(int k) {
        String t = this.RightBlock;
        this.LiftBlock = this.exclusiveOr(32, this.LiftBlock, this.function(t, k));
    }

    public String function(String s, int k) {
        return this.permut(this.substitute(this.exclusiveOr(48, this.permutExp(s), this.Keys[k])), this.PBox, 4);
    }

    public String exclusiveOr(int x, String s1, String s2) {
        String result = "";

        for(int i = 0; i < x; ++i) {
            String c1 = s1.charAt(i) + "";
            String c2 = s2.charAt(i) + "";
            if (c1.equals(c2)) {
                result = result + 0;
            } else {
                result = result + 1;
            }
        }

        return result;
    }

    public String permutExp(String s) {
        String result = "";

        for(int i = 0; i < 8; ++i) {
            for(int j = 0; j < 6; ++j) {
                result = result + s.charAt(this.ExpanPerT[i][j] - 1);
            }
        }

        return result;
    }

    public void swapper() {
        String t = this.LiftBlock;
        this.LiftBlock = this.RightBlock;
        this.RightBlock = t;
    }

    public String substitute(String s) {
        String value = "";
        int row = 2 * Integer.parseInt(s.charAt(0) + "") + Integer.parseInt(s.charAt(5) + "");
        int col = 8 * Integer.parseInt(s.charAt(1) + "") + 4 * Integer.parseInt(s.charAt(2) + "") + 2 * Integer.parseInt(s.charAt(3) + "") + Integer.parseInt(s.charAt(4) + "");
        value = value + this.sBoxConvert(this.sBox1, row, col);
        row = 2 * Integer.parseInt(s.charAt(6) + "") + Integer.parseInt(s.charAt(11) + "");
        col = 8 * Integer.parseInt(s.charAt(7) + "") + 4 * Integer.parseInt(s.charAt(8) + "") + 2 * Integer.parseInt(s.charAt(9) + "") + Integer.parseInt(s.charAt(10) + "");
        value = value + this.sBoxConvert(this.sBox2, row, col);
        row = 2 * Integer.parseInt(s.charAt(12) + "") + Integer.parseInt(s.charAt(17) + "");
        col = 8 * Integer.parseInt(s.charAt(13) + "") + 4 * Integer.parseInt(s.charAt(14) + "") + 2 * Integer.parseInt(s.charAt(15) + "") + Integer.parseInt(s.charAt(16) + "");
        value = value + this.sBoxConvert(this.sBox3, row, col);
        row = 2 * Integer.parseInt(s.charAt(18) + "") + Integer.parseInt(s.charAt(23) + "");
        col = 8 * Integer.parseInt(s.charAt(19) + "") + 4 * Integer.parseInt(s.charAt(20) + "") + 2 * Integer.parseInt(s.charAt(21) + "") + Integer.parseInt(s.charAt(22) + "");
        value = value + this.sBoxConvert(this.sBox4, row, col);
        row = 2 * Integer.parseInt(s.charAt(24) + "") + Integer.parseInt(s.charAt(29) + "");
        col = 8 * Integer.parseInt(s.charAt(25) + "") + 4 * Integer.parseInt(s.charAt(26) + "") + 2 * Integer.parseInt(s.charAt(27) + "") + Integer.parseInt(s.charAt(28) + "");
        value = value + this.sBoxConvert(this.sBox5, row, col);
        row = 2 * Integer.parseInt(s.charAt(30) + "") + Integer.parseInt(s.charAt(35) + "");
        col = 8 * Integer.parseInt(s.charAt(31) + "") + 4 * Integer.parseInt(s.charAt(32) + "") + 2 * Integer.parseInt(s.charAt(33) + "") + Integer.parseInt(s.charAt(34) + "");
        value = value + this.sBoxConvert(this.sBox6, row, col);
        row = 2 * Integer.parseInt(s.charAt(36) + "") + Integer.parseInt(s.charAt(41) + "");
        col = 8 * Integer.parseInt(s.charAt(37) + "") + 4 * Integer.parseInt(s.charAt(38) + "") + 2 * Integer.parseInt(s.charAt(39) + "") + Integer.parseInt(s.charAt(40) + "");
        value = value + this.sBoxConvert(this.sBox7, row, col);
        row = 2 * Integer.parseInt(s.charAt(42) + "") + Integer.parseInt(s.charAt(47) + "");
        col = 8 * Integer.parseInt(s.charAt(43) + "") + 4 * Integer.parseInt(s.charAt(44) + "") + 2 * Integer.parseInt(s.charAt(45) + "") + Integer.parseInt(s.charAt(46) + "");
        value = value + this.sBoxConvert(this.sBox8, row, col);
        return value;
    }

    public String sBoxConvert(int[][] a, int x, int y) {
        String help = "";
        int z = a[x][y];
        help = help + z / 8;
        z -= z / 8 * 8;
        help = help + z / 4;
        z -= z / 4 * 4;
        help = help + z / 2;
        z -= z / 2 * 2;
        help = help + z;
        return help;
    }

    public String Cipher(String msg) {
        this.split(this.permut(msg, this.InitialPerT, 8), 32);

        for(int i = 0; i < 16; ++i) {
            this.mixer(i);
            if (i != 15) {
                this.swapper();
            }
        }

        return this.permut(this.combine(32), this.FinalPerT, 8);
    }

    public String ChooseFile() {
        JFileChooser fc = new JFileChooser();
        fc.showOpenDialog((Component)null);
        return fc.getSelectedFile().toString();
    }

    public String OpenReadFile(String s, int k) {
        try {
            BufferedReader inp = new BufferedReader(new FileReader(s));
            String line = "";

            for(int i = 0; i < k; ++i) {
                line = line + inp.readLine();
            }

            inp.close();
            return line;
        } catch (Exception var6) {
            System.out.println("here");
            return null;
        }
    }

    public int LenFile(String s) {
        int k = 0;

        try {
            BufferedReader inp;
            for(inp = new BufferedReader(new FileReader(s)); inp.readLine() != null; ++k) {
            }

            inp.close();
        } catch (Exception var4) {
        }

        return k;
    }

    public void WriteInFile(String s) {
        try {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(JOptionPane.showInputDialog("Please enter the file name") + ".txt", false), "windows-1252");
            writer.append(s);
            writer.close();
        } catch (Exception var3) {
            JOptionPane.showMessageDialog((Component)null, "Error opening the file");
        }

    }

    public void WriteInFile8(String s) {
        try {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(JOptionPane.showInputDialog("Please enter the file name") + ".txt", false), "UTF-8");
            writer.append(s);
            writer.close();
        } catch (Exception var3) {
            JOptionPane.showMessageDialog((Component)null, "Error opening the file");
        }

    }

    public String Padding(String s) {
        if (s.length() % 64 == 0) {
            return s;
        } else {
            for(int i = 0; i < s.length() % 64; ++i) {
                s = s + '0';
            }

            return s;
        }
    }

    public void devText(String s) {
        this.Texts64.clear();

        for(int i = 0; i < s.length() / 64; ++i) {
            String ss = s.substring(i * 64, (i + 1) * 64);
            this.Texts64.add(ss);
        }

    }

    public String BinToText(String s) {
        String output = "";

        for(int i = 0; i <= s.length() - 8; i += 8) {
            int k = Integer.parseInt(s.substring(i, i + 8), 2);
            output = output + (char)k;
        }

        return output;
    }

    public String convertText(String s) {
        String sHex = "";

        for(int i = 0; i < s.length(); ++i) {
            System.out.println("here");
            char c = s.charAt(i);
            String l = this.charNumHex(this.charNum(c));
            if (l.equals("z")) {
                sHex = "";
                System.out.println("yes");
                i = s.length();
            } else {
                sHex = sHex + l;
            }
        }

        return sHex;
    }

    public int charNum(char c) {
        for(int i = 0; i < this.helpLan.length; ++i) {
            if (this.helpLan[i] == c) {
                return i;
            }
        }

        return -1;
    }

    public String charNumHex(int x) {
        if (x != -1) {
            return this.language[x];
        } else {
            JOptionPane.showMessageDialog((Component)null, "The Message contains char not in the language", "Hint", 0);
            return "z";
        }
    }

    public boolean testKey(String s) {
        if (s.length() < 16) {
            JOptionPane.showMessageDialog((Component)null, "The Key should be 16", "Hint", 0);
            return false;
        } else {
            return true;
        }
    }
}
