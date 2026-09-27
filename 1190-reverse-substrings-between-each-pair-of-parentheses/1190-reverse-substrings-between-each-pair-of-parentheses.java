class Solution {
    public String reverseParentheses(String s) {
        int n = s.length();
        char[] a = s.toCharArray();
        int[] p = new int[n];
        int[] st = new int[n];
        int t = 0;

        for (int i = 0; i < n; i++) {
            if (a[i] == '(') st[t++] = i;
            else if (a[i] == ')') {
                int j = st[--t];
                p[i] = j;
                p[j] = i;
            }
        }

        char[] res = new char[n];
        int k = 0, i = 0, d = 1;

        while (i >= 0 && i < n) {
            char c = a[i];

            if (c == '(' || c == ')') {
                i = p[i];
                d = -d;
            } else {
                res[k++] = c;
            }

            i += d;
        }

        return new String(res, 0, k);
    }
}