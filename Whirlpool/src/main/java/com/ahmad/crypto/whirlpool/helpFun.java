package com.ahmad.crypto.whirlpool;

public class helpFun {
    private String[][] key_state = new String[][]{{"00", "00", "00", "00", "00", "00", "00", "00"}, {"00", "00", "00", "00", "00", "00", "00", "00"}, {"00", "00", "00", "00", "00", "00", "00", "00"}, {"00", "00", "00", "00", "00", "00", "00", "00"}, {"00", "00", "00", "00", "00", "00", "00", "00"}, {"00", "00", "00", "00", "00", "00", "00", "00"}, {"00", "00", "00", "00", "00", "00", "00", "00"}, {"00", "00", "00", "00", "00", "00", "00", "00"}};
    private String[] text_block;
    private String[][] prev_key_state = new String[8][8];
    private String[][] prev_state = new String[8][8];
    private String[][] state = new String[8][8];
    private String[][] subBytes_table = new String[][]{{"18", "23", "C6", "E8", "87", "B8", "01", "4F", "36", "A6", "D2", "F5", "79", "6F", "91", "52"}, {"16", "BC", "9B", "8E", "A3", "0C", "7B", "35", "1D", "E0", "D7", "C2", "2E", "4B", "FE", "57"}, {"15", "77", "37", "E5", "9F", "F0", "4A", "CA", "58", "C9", "29", "0A", "B1", "A0", "6B", "85"}, {"BD", "5D", "10", "F4", "CB", "3E", "05", "67", "E4", "27", "41", "8B", "A7", "7D", "95", "C8"}, {"FB", "EF", "7C", "66", "DD", "17", "47", "9E", "CA", "2D", "BF", "07", "AD", "5A", "83", "33"}, {"63", "02", "AA", "71", "C8", "19", "49", "C9", "F2", "E3", "5B", "88", "9A", "26", "32", "B0"}, {"E9", "0F", "D5", "80", "BE", "CD", "34", "48", "FF", "7A", "90", "5F", "20", "68", "1A", "AE"}, {"B4", "54", "93", "22", "64", "F1", "73", "12", "40", "08", "C3", "EC", "DB", "A1", "8D", "3D"}, {"97", "00", "CF", "2B", "76", "82", "D6", "1B", "B5", "AF", "6A", "50", "45", "F3", "30", "EF"}, {"3F", "55", "A2", "EA", "65", "BA", "2F", "C0", "DE", "1C", "FD", "4D", "92", "75", "06", "8A"}, {"B2", "E6", "0E", "1F", "62", "D4", "A8", "96", "F9", "C5", "25", "59", "84", "72", "39", "4C"}, {"5E", "78", "38", "8C", "C1", "A5", "E2", "61", "B3", "21", "9C", "1E", "43", "C7", "FC", "04"}, {"51", "99", "6D", "0D", "FA", "DF", "7E", "24", "3B", "AB", "CE", "11", "8F", "4E", "B7", "EB"}, {"3C", "81", "94", "F7", "9B", "13", "2C", "D3", "E7", "6E", "C4", "03", "56", "44", "7E", "A9"}, {"2A", "BB", "C1", "53", "DC", "0B", "9D", "6C", "31", "74", "F6", "46", "AC", "89", "14", "E1"}, {"16", "3A", "69", "09", "70", "B6", "C0", "ED", "CC", "42", "98", "A4", "28", "5C", "F8", "86"}};
    private int[][] constantMatrix_table = new int[][]{{1, 1, 4, 1, 8, 5, 2, 9}, {9, 1, 1, 4, 1, 8, 5, 2}, {2, 9, 1, 1, 4, 1, 8, 5}, {5, 2, 9, 1, 1, 4, 1, 8}, {8, 5, 2, 9, 1, 1, 4, 1}, {1, 8, 5, 2, 9, 1, 1, 4}, {4, 1, 8, 5, 2, 9, 1, 1}, {1, 4, 1, 8, 5, 2, 9, 1}};
    private int bin1D = 29;

    private String padding(String s) {
        int x = s.length() / 8;
        int xx = s.length() % 512;
        String var6;
        if (xx > 256) {
            var6 = s + "1";

            for(int i = 1; i < 512 - xx; ++i) {
                var6 = var6 + "0";
            }

            var6 = var6 + "0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
        } else {
            var6 = s + "1";

            for(int i = 1; i < 256 - xx; ++i) {
                var6 = var6 + "0";
            }
        }

        var6 = var6 + this.mesLengthInBinary(x);
        this.text_block = new String[var6.length() / 512];
        return var6;
    }

    private String mesLengthInBinary(int x) {
        String s = "";

        for(int i = 255; i > -1; --i) {
            if ((double)x >= Math.pow((double)2.0F, (double)i)) {
                x = (int)((double)x - Math.pow((double)2.0F, (double)i));
                s = s + "1";
            } else {
                s = s + "0";
            }
        }

        return s;
    }

    private void textBlocksDiv(String s) {
        int x = s.length() / 512;

        for(int i = 0; i < x; ++i) {
            this.text_block[i] = s.substring(i * 512, (i + 1) * 512);
        }

    }

    private void state_init(String b) {
        for(int i = 0; i < b.length(); i += 8) {
            String s = this.conIntHex(this.conBinInt(b.substring(i, i + 4))) + this.conIntHex(this.conBinInt(b.substring(i + 4, i + 8)));
            this.state[i / 64][i / 8 % 8] = s;
        }

    }

    private void makeKey0() {
        for(int i = 0; i < 8; ++i) {
            for(int j = 0; j < 8; ++j) {
                this.key_state[i][j] = "00";
            }
        }

    }

    private int conBinInt(String bb) {
        return Integer.parseInt(bb, 2);
    }

    public String conIntBin(int xx) {
        return Integer.toBinaryString(xx);
    }

    private int conHexInt(char c) {
        if (c == '0') {
            return 0;
        } else if (c == '1') {
            return 1;
        } else if (c == '2') {
            return 2;
        } else if (c == '3') {
            return 3;
        } else if (c == '4') {
            return 4;
        } else if (c == '5') {
            return 5;
        } else if (c == '6') {
            return 6;
        } else if (c == '7') {
            return 7;
        } else if (c == '8') {
            return 8;
        } else if (c == '9') {
            return 9;
        } else if (c == 'A') {
            return 10;
        } else if (c == 'B') {
            return 11;
        } else if (c == 'C') {
            return 12;
        } else if (c == 'D') {
            return 13;
        } else {
            return c == 'E' ? 14 : 15;
        }
    }

    private String conIntHex(int c) {
        if (c == 0) {
            return "0";
        } else if (c == 1) {
            return "1";
        } else if (c == 2) {
            return "2";
        } else if (c == 3) {
            return "3";
        } else if (c == 4) {
            return "4";
        } else if (c == 5) {
            return "5";
        } else if (c == 6) {
            return "6";
        } else if (c == 7) {
            return "7";
        } else if (c == 8) {
            return "8";
        } else if (c == 9) {
            return "9";
        } else if (c == 10) {
            return "A";
        } else if (c == 11) {
            return "B";
        } else if (c == 12) {
            return "C";
        } else if (c == 13) {
            return "D";
        } else {
            return c == 14 ? "E" : "F";
        }
    }

    private String binPadding(String s) {
        String sss = s;
        if (s.length() < 4) {
            for(int i = 0; i < 4 - s.length(); ++i) {
                sss = "0" + sss;
            }
        }

        return sss;
    }

    public String binPaddingUTF(String s) {
        String sss = s;
        if (s.length() < 8) {
            for(int i = 0; i < 8 - s.length(); ++i) {
                sss = "0" + sss;
            }
        }

        return sss;
    }

    private String extractBinHex(String ss) {
        String s = "";
        s = s + this.binPadding(this.conIntBin(this.conHexInt(ss.charAt(0))));
        s = s + this.binPadding(this.conIntBin(this.conHexInt(ss.charAt(1))));
        return s;
    }

    private String extractHexBin(String ss) {
        if (ss.length() < 8) {
            for(int h = ss.length(); h < 8; ++h) {
                ss = "0" + ss;
            }
        }

        String sss = this.conIntHex(this.conBinInt(ss.substring(0, 4))) + this.conIntHex(this.conBinInt(ss.substring(4, 8)));
        return sss;
    }

    private String XOR2Hex(String s1, String s2) {
        String ss1 = this.extractBinHex(s1);
        int z = this.conBinInt(ss1);
        String ss2 = this.extractBinHex(s2);
        int zz = this.conBinInt(ss2);
        int zzz = z ^ zz;
        String sss = this.conIntBin(zzz);
        String s3 = this.extractHexBin(sss);
        return s3;
    }

    private String[][] whirlpoolOneRound(String[][] sop, String[][] kop) {
        return this.addRoundKey(this.mixRows(this.shiftColumns(this.subBytes(sop))), kop);
    }

    private String[][] subBytes(String[][] st) {
        for(int i = 0; i < 8; ++i) {
            for(int j = 0; j < 8; ++j) {
                int t0 = this.conHexInt(st[i][j].charAt(0));
                int t1 = this.conHexInt(st[i][j].charAt(1));
                st[i][j] = this.subBytes_table[t0][t1];
            }
        }

        return st;
    }

    private String[][] shiftColumns(String[][] st) {
        for(int i = 1; i < 8; ++i) {
            st = this.shift_up_more(st, i);
        }

        return st;
    }

    private String[][] shift_up_more(String[][] st, int k) {
        for(int ii = 0; ii < k; ++ii) {
            st = this.shift_up_one(st, k);
        }

        return st;
    }

    private String[][] shift_up_one(String[][] st, int k) {
        String val = st[7][k];

        for(int iii = 7; iii > 0; --iii) {
            st[iii][k] = st[iii - 1][k];
        }

        st[0][k] = val;
        return st;
    }

    private String[][] mixRows(String[][] st) {
        String[][] newState = new String[8][8];

        for(int i = 0; i < 8; ++i) {
            for(int j = 0; j < 8; ++j) {
                newState[i][j] = this.mulRowCol(st[i], j);
            }
        }

        return newState;
    }

    private String mulRowCol(String[] st_row, int k) {
        String r = "00";

        for(int ii = 0; ii < 8; ++ii) {
            r = this.XOR2Hex(r, this.mulCells(st_row[ii], this.constantMatrix_table[ii][k]));
        }

        return r;
    }

    private String mulCells(String st_cell, int k_val) {
        if (k_val == 1) {
            return st_cell;
        } else if (k_val == 2) {
            return this.mul_02(st_cell);
        } else if (k_val == 4) {
            return this.mul_04(st_cell);
        } else if (k_val == 5) {
            return this.mul_05(st_cell);
        } else if (k_val == 8) {
            return this.mul_08(st_cell);
        } else {
            return k_val == 9 ? this.mul_09(st_cell) : "errrrror";
        }
    }

    private String mul_02(String st_cell) {
        String s1 = this.extractBinHex(st_cell);
        int z = this.conBinInt(s1);
        z *= 2;
        if (z > 255) {
            z -= 256;
            z ^= this.bin1D;
        }

        String ss = this.conIntBin(z);
        String s3 = this.extractHexBin(ss);
        return s3;
    }

    private String mul_04(String st_cell) {
        String res = this.mul_02(this.mul_02(st_cell));
        return res;
    }

    private String mul_05(String st_cell) {
        String res1 = this.mul_04(st_cell);
        String res = this.XOR2Hex(res1, st_cell);
        return res;
    }

    private String mul_08(String st_cell) {
        String res = this.mul_04(this.mul_02(st_cell));
        return res;
    }

    private String mul_09(String st_cell) {
        String res1 = this.mul_08(st_cell);
        String res = this.XOR2Hex(res1, st_cell);
        return res;
    }

    private String[][] addRoundKey(String[][] st1, String[][] st2) {
        String[][] st_res = new String[8][8];

        for(int i = 0; i < 8; ++i) {
            for(int j = 0; j < 8; ++j) {
                st_res[i][j] = this.XOR2Hex(st1[i][j], st2[i][j]);
            }
        }

        return st_res;
    }

    private String[][] RCiGeneration(int v) {
        String[][] sd = new String[8][8];

        for(int i = 0; i < 8; ++i) {
            sd[0][i] = this.subBytes_table[v / 2][(v * 8 + i) % 16];
        }

        for(int i = 1; i < 8; ++i) {
            for(int j = 0; j < 8; ++j) {
                sd[i][j] = "00";
            }
        }

        return sd;
    }

    private String[][] whirlpoolEnc(String[][] text_state_arr, String[][] key_state_arr) {
        text_state_arr = this.addRoundKey(text_state_arr, key_state_arr);

        for(int i = 0; i < 10; ++i) {
            key_state_arr = this.whirlpoolOneRound(key_state_arr, this.RCiGeneration(i + 1));
            text_state_arr = this.whirlpoolOneRound(text_state_arr, key_state_arr);
        }

        return text_state_arr;
    }

    public String[][] whirlpoolAlgo(String plText) {
        this.makeKey0();
        this.textBlocksDiv(this.padding(plText));
        String[][] final_state = new String[8][8];

        for(int i = 0; i < this.text_block.length; ++i) {
            this.state_init(this.text_block[i]);
            this.stored_pervious_state();
            this.stored_pervious_key();
            this.key_state = this.whirlpoolEnc(this.state, this.key_state);
            this.key_state = this.addRoundKey(this.addRoundKey(this.key_state, this.prev_key_state), this.prev_state);
            final_state = this.key_state;
        }

        return final_state;
    }

    private void stored_pervious_state() {
        for(int i = 0; i < 8; ++i) {
            for(int j = 0; j < 8; ++j) {
                this.prev_state[i][j] = this.state[i][j];
            }
        }

    }

    private void stored_pervious_key() {
        for(int i = 0; i < 8; ++i) {
            for(int j = 0; j < 8; ++j) {
                this.prev_key_state[i][j] = this.key_state[i][j];
            }
        }

    }

    public String desc_state_bin(String[][] s) {
        String ss = "";

        for(int i = 0; i < 8; ++i) {
            for(int j = 0; j < 8; ++j) {
                ss = ss + this.extractBinHex(s[i][j]);
            }
        }

        return ss;
    }

    public String desc_state_hex(String[][] s) {
        String ss = "";

        for(int i = 0; i < 8; ++i) {
            for(int j = 0; j < 8; ++j) {
                ss = ss + s[i][j];
            }
        }

        return ss;
    }
}
