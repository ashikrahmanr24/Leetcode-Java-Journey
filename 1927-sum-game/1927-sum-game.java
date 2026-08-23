class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int sumL = 0, sumR = 0;
        int qL = 0, qR = 0;

        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);
            if (i < n / 2) {
                if (c == '?') {
                    qL++;
                } else {
                    sumL += c - '0';
                }
            } else {
                if (c == '?') {
                    qR++;
                } else {
                    sumR += c - '0';
                }
            }
        }

        // Alice wins if total '?' count is odd (she gets the last move)
        if ((qL + qR) % 2 != 0) {
            return true;
        }

        // Each extra pair of '?' on one side can contribute a net total of 9 points
        return (sumL - sumR) * 2 != (qR - qL) * 9;
    }
}