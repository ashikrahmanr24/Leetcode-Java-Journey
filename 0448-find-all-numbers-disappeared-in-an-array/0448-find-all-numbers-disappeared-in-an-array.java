import java.util.*;
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        //Creating a list to store solution (list is dynamic)
        List<Integer> ans = new ArrayList<>();

        for(int i=0;i<nums.length;i++) {
            int index = Math.abs(nums[i])-1;
            nums[index] = -Math.abs(nums[index]);
        }
        for(int i=0;i<nums.length;i++) {
            if(nums[i]>0) {
                ans.add(i+1);
            }
        }
        return ans;
    }
}