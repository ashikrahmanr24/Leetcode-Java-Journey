import java.util.*;
import java.util.function.*;
import java.math.*;

class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int[] nextDec = new int[n]; // nums[nextDec[i]] > nums[nextDec[i] + 1]
        nextDec[n - 1] = n;
        int p = n;
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] > nums[i + 1]) {
                p = i;
            }
            nextDec[i] = p;
        }

        int ans = 0;
        for (int k = 1; k * k <= n; k++) {
            if (n % k == 0) {
                ans += solve(k, nums, nextDec);
                if (k * k < n) {
                    ans += solve(n / k, nums, nextDec);
                }
            }
        }
        return ans;
    }

    private int solve(int k, int[] nums, int[] nextDec) {
        int lastMax = 0; 
        for (int r = k - 1; r < nums.length; r += k) {
            int l = r - k + 1;
            int m = nextDec[l];
            if (m >= r) {
                if (nums[l] < lastMax) {
                    return 0;
                }
                lastMax = nums[r];
            } else {
                if (nextDec[m + 1] < r || nums[m + 1] < lastMax || nums[r] > nums[l]) {
                    return 0;
                }
                lastMax = nums[m];
            }
        }
        return k;
    }
}