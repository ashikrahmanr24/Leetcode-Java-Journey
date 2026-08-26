class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int mid = n / 2;
        int sumDiff = 0;
        int qDiff = 0;

        for (int i = 0; i < n; i++) {
            char ch = num.charAt(i);
            if (i < mid) {
                if (ch == '?') {
                    qDiff++;
                } else {
                    sumDiff += ch - '0';
                }
            } else {
                if (ch == '?') {
                    qDiff--;
                } else {
                    sumDiff -= ch - '0';
                }
            }
        }

        // Alice wins if the total number of '?' is odd
        if ((qDiff & 1) != 0) {
            return true;
        }

        // Bob wins (returns false) only if he can perfectly balance the sums
        return sumDiff != -qDiff * 9 / 2;
    }
}
