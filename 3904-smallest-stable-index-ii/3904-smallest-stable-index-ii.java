class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] suffMin = new int[n];
        suffMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffMin[i] = Math.min(nums[i], suffMin[i + 1]);
        }
        int prefMax = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (nums[i] > prefMax) {
                prefMax = nums[i];
            }
            if (prefMax - suffMin[i] <= k) {
                return i;
            }
        }
        return -1;
    }
}
