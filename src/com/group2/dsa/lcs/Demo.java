package com.group2.dsa.lcs;

import java.util.List;
// The longest common subsequence in Java

class Demo {
    static String lcs(String S1, String S2, int m, int n) {
        int[][] lcsTable = new int[m + 1][n + 1];

        // Building the mtrix in bottom-up way
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0)
                    lcsTable[i][j] = 0;
                else if (S1.charAt(i - 1) == S2.charAt(j - 1))
                    lcsTable[i][j] = lcsTable[i - 1][j - 1] + 1;
                else
                    lcsTable[i][j] = Math.max(lcsTable[i - 1][j], lcsTable[i][j - 1]);
            }
        }

        int index = lcsTable[m][n];
        int temp = index;

        char[] lcs = new char[index + 1];
        lcs[index] = '\0';

        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (S1.charAt(i - 1) == S2.charAt(j - 1)) {
                lcs[index - 1] = S1.charAt(i - 1);
                i--;
                j--;
                index--;
            }

            else if (lcsTable[i - 1][j] > lcsTable[i][j - 1])
                i--;
            else
                j--;
        }

        StringBuilder result = new StringBuilder();
        for (int k = 0; k <= temp; k++)
            result.append(lcs[k]);

        return result.toString().length() > 0 ? result.toString() : null;
    }


    public static void main(String[] args) {
        String str = "KLOPGAB";
        String s1 = "AGGTAB";
        String s2 = "AGGABT";
        String s3 = "OBGABO";
        String s4 = "PLKKGG";
        String s5 = "ABCDEF";
        String s6 = "PLUYGAH";

        List<String> strings = List.of(s1, s2, s3, s4, s5, s6);
        String result = null;
        String lcsString = null;
        String[][] arr = new String[10][2];

        for (int i = 0; i < strings.size() - 1; i++) {
            String X = strings.get(i);
            int m = str.length();
            int n = X.length();
            result = X;
            lcsString = lcs(str, X,m,n);
            arr[i][0] = lcsString;
            arr[i][1] = lcsString.length() + "";
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i][0]+" "+arr[i][1]);
        }
        System.out.println("Length of LCS of " + result + " with string : " + lcsString);
    }
}
