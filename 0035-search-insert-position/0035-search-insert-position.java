class Solution {
    public int searchInsert(int[] nums, int target) {
        int count=0;
        int i;
        for(i=0;i<nums.length;i++){
            if(nums[i]==target){
                return i;
            } else if(nums[i]>target) {
                break;
                
            }
            
        }
        return i;

    }
}