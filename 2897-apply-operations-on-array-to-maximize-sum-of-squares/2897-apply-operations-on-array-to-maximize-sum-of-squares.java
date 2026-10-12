class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int[] cnt = new int[30];
        for (int x : nums) {
            for (int i = 0; i < 30; i++) {
                if (((x >> i) & 1) == 1) {
                    cnt[i]++;
                }
            }
        }
        
        long[] a = new long[nums.size()];
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < cnt[i]; j++) {
                a[j] |= (1L << i);
            }
        }
        
        long ans = 0;
        long mod = 1_000_000_007;
        for (int i = 0; i < k; i++) {
            ans = (ans + (a[i] * a[i]) % mod) % mod;
        }
        
        return (int) ans;
    }
}