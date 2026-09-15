class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        int[] lastPosition = new int[26];
        
        for (int i = 1; i <= n; i++) {
            char ch = s.charAt(i - 1);
            int charIndex = ch - 'a';
            dp[i] = (dp[i - 1] * 2) % MOD;
            if (lastPosition[charIndex] > 0) {
                int prevIdx = lastPosition[charIndex];
                dp[i] = (dp[i] - dp[prevIdx - 1] + MOD) % MOD;
            }
            lastPosition[charIndex] = i;
        }
        return (dp[n] - 1 + MOD) % MOD;
    }
}
