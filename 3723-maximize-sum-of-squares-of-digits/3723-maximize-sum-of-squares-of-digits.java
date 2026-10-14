class Solution {
    public String maxSumOfSquares(int num, int sum) {
        if (sum < 0 || sum > 9 * num) {
            return "";
        }
        char[] ans = new char[num];
        for (int i = 0; i < num; i++) {
            int d = Math.min(9, sum);
            ans[i] = (char) ('0' + d);
            sum -= d;
        }
        return new String(ans);
    }
}