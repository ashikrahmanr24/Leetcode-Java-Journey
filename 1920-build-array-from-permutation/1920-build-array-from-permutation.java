class Solution {
    public int[] buildArray(int[] nums) {
        int[] sol = new int[nums.length];
        for(int i=0;i<nums.length;i++) {
            sol[i]=nums[nums[i]];
        }
        return sol;
    }
}