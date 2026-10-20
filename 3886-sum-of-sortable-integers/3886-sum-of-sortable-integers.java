import java.util.Arrays;

class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        int ans = 0;
        for (int k = 1; k * k <= n; ++k) {
            if (n % k == 0) {
                if (check(nums, sorted, n, k)) {
                    ans += k;
                }
                int k2 = n / k;
                if (k2 != k) {
                    if (check(nums, sorted, n, k2)) {
                        ans += k2;
                    }
                }
            }
        }
        return ans;
    }

    private boolean check(int[] nums, int[] sorted, int n, int k) {
        for (int i = 0; i < n; i += k) {
            if (!match(nums, sorted, i, i + k)) {
                return false;
            }
        }
        return true;
    }

    private boolean match(int[] nums, int[] sorted, int l, int r) {
        int k = r - l;
        int diffCount = 0;
        int start = -1;
        for (int i = 0; i < k; ++i) {
            if (nums[l + i] != sorted[l + i]) {
                diffCount++;
                if (start == -1) {
                    start = i;
                }
            }
        }
        if (diffCount == 0) {
            return true;
        }
        int firstMismatch = start;
        int nextMismatch = -1;
        for (int i = firstMismatch + 1; i < k; ++i) {
            if (nums[l + i] != sorted[l + i]) {
                nextMismatch = i;
                break;
            }
        }
        if (nextMismatch == -1) {
            return false;
        }
        int minVal = nums[l + firstMismatch];
        int maxVal = nums[l + nextMismatch];
        int pivot = -1;
        for (int i = 0; i < k; ++i) {
            int next = (i + 1) % k;
            if (nums[l + i] > nums[l + next]) {
                if (pivot != -1) {
                    return false;
                }
                pivot = next;
            }
        }
        if (pivot == -1) {
            for (int i = 0; i < k; ++i) {
                if (nums[l + i] != sorted[l + i]) {
                    return false;
                }
            }
            return true;
        }
        for (int i = 0; i < k; ++i) {
            if (nums[l + (pivot + i) % k] != sorted[l + i]) {
                return false;
            }
        }
        return true;
    }
}