class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        if (n <= 2) return n;

        int minIdx = 0;
        int maxIdx = 0;

        // Find the positions of min and max elements
        for (int k = 1; k < n; k++) {
            if (nums[k] < nums[minIdx]) {
                minIdx = k;
            }
            if (nums[k] > nums[maxIdx]) {
                maxIdx = k;
            }
        }

        // Ensure i is the smaller index and j is the larger index
        int i = Math.min(minIdx, maxIdx);
        int j = Math.max(minIdx, maxIdx);

        // Scenario 1: Remove both from the front
        int op1 = j + 1;

        // Scenario 2: Remove both from the back
        int op2 = n - i;

        // Scenario 3: Remove smaller from front, larger from back
        int op3 = (i + 1) + (n - j);

        // Return the minimum of all three possibilities
        return Math.min(op1, Math.min(op2, op3));
    }
}
