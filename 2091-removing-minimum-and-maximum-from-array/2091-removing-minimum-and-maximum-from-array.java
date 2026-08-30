class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        if (n <= 2) return n;

        int minIdx = 0;
        int maxIdx = 0;

        // Find positions of the minimum and maximum elements in a single pass
        for (int k = 1; k < n; k++) {
            if (nums[k] < nums[minIdx]) {
                minIdx = k;
            } else if (nums[k] > nums[maxIdx]) { // 'else if' saves redundant checks
                maxIdx = k;
            }
        }

        // Determine which index is closer to the start (left) and end (right)
        int left = (minIdx < maxIdx) ? minIdx : maxIdx;
        int right = (minIdx > maxIdx) ? minIdx : maxIdx;

        // Strategy 1: Delete both from the left side
        int opt1 = right + 1;
        
        // Strategy 2: Delete both from the right side
        int opt2 = n - left;
        
        // Strategy 3: Delete left from the left side and right from the right side
        int opt3 = (left + 1) + (n - right);

        // Find the absolute minimum of the three options using ternary operations
        int minDel = (opt1 < opt2) ? opt1 : opt2;
        return (minDel < opt3) ? minDel : opt3;
    }
}
