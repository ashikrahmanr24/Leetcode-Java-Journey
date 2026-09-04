class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) return -1;

        // Step 1: Precompute suffix minimums
        int[] suffixMin = new int[n];
        suffixMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(nums[i], suffixMin[i + 1]);
        }

        // Step 2: Iterate from left to right tracking the prefix maximum
        int currentMax = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            currentMax = Math.max(currentMax, nums[i]);
            
            // Step 3: Check instability score condition
            if (currentMax - suffixMin[i] <= k) {
                return i; // Return immediately at the first valid (smallest) index
            }
        }

        return -1;
    }
}
