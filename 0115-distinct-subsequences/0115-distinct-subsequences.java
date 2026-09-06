class Solution {
    public int numDistinct(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if (sLen < tLen) {
            return 0;
        }
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        int[] dp = new int[tLen + 1];
        dp[0] = 1;
        for (int i = 1; i <= sLen; i++) {
            char sChar = sChars[i - 1];
            int start = Math.min(tLen, i);
            for (int j = start; j >= 1; j--) {
                if (sLen - i < tLen - j) {
                    continue;
                }
                
                if (sChar == tChars[j - 1]) {
                    dp[j] += dp[j - 1];
                }
            }
        }

        return dp[tLen];
    }
}
