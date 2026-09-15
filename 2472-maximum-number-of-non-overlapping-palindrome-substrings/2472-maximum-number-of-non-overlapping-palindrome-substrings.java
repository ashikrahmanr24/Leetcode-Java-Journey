class Solution {
    public int maxPalindromes(String s, int k) {
        if (k == 1) return s.length();
        char[] c = s.toCharArray();
        int n = c.length;
        int[] dp = new int[n + 1];
        for (int i = k; i <= n; i++) {
            dp[i] = dp[i - 1];
            if (isPalindrome(c, i - k, i - 1)) {
                dp[i] = Math.max(dp[i], dp[i - k] + 1);
            }
            else if (i > k && isPalindrome(c, i - k - 1, i - 1)) {
                dp[i] = Math.max(dp[i], dp[i - k - 1] + 1);
            }
        }
        
        return dp[n];
    }
    private boolean isPalindrome(char[] c, int l, int r) {
        while (l < r) {
            if (c[l] != c[r]) return false;
            l++;
            r--;
        }
        return true;
    }
}
