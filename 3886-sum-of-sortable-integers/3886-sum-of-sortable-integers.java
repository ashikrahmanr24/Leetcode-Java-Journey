class Solution {
    public boolean check(int[] nums, int len) {
        int n = nums.length;
        int minn = 0;
        
        for (int i = 0; i < n; i += len) {
            if (nums[i] < minn) return false;
            
            int maxx = nums[i];
            boolean point = false;
            
            for (int j = i + 1; j < i + len; j++) {
                if (nums[j] < minn) return false;
                
                if (nums[j - 1] > nums[j]) {
                    if (point) return false;
                    point = true;
                }
                
                maxx = Math.max(maxx, nums[j]);
            }
            
            if (point) {
                if (nums[i] < nums[i + len - 1]) return false;
            }
            
            minn = maxx;
        }
        
        return true;
    }
    
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int ans = 0;
        
        for (int k = 1; k <= n; k++) {
            if (n % k != 0) continue;
            if (check(nums, k)) ans += k;
        }
        
        return ans;
    }
}