import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> targetIndices(int[] nums, int target) {
        int lessThanTargetCount = 0;
        int targetCount = 0;
        for (int num : nums) {
            if (num < target) {
                lessThanTargetCount++;
            } else if (num == target) {
                targetCount++;
            }
        }
        
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < targetCount; i++) {
            result.add(lessThanTargetCount + i);
        }
        
        return result;
    }
}