package leetcode.arrays_and_strings;

import java.util.HashMap;
import java.util.Map;

class Solution implements Problems {

    @Override
    public boolean isUnique(String str) {
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!map.containsKey(c)) {
                map.put(c, i);

            } else return false;
        }
        return true;
    }

    @Override
    public boolean checkPermutation(String s1, String s2) {
        return false;
    }

    @Override
    public String URLify(String str) {
        int n = str.length();
        int counter = 0;
        for (int i = 0; i < n; i++) {
            if (str.charAt(i) == ' ') counter++;
        }

        char[] chars = new char[n + counter * 2];

        int index = 0;
        for (int i = 0; i < n; i++) {
            char c = str.charAt(i);
            if (c == ' ') {
                chars[index] = '%';
                chars[index + 1] = '2';
                chars[index + 2] = '0';
                index += 3;
            } else {
                chars[index] = c;
                index++;
            }
        }
        return new String(chars);
    }

    @Override
    public String compression(String str) {
        return "";
    }

    public void zeroMatrix(int[][] matrix) {

    }
}
