class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        int ans = 0;
        int[] lastCount = new int[26];
        char[] chars = s.toCharArray();      
        for (char c : chars) {
            int idx = c - 'a';
            int currentAdded = (ans + 1 - lastCount[idx] + MOD) % MOD;
            int nextAns = (ans + currentAdded) % MOD;
            lastCount[idx] = (ans + 1) % MOD;         
            ans = nextAns;
        }
        
        return ans;
    }
}
