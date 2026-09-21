import java.util.*;

class Solution {

    public int majorityElement(int[] nums) {

        HashMap<Integer, Integer> hs = new HashMap<>();
        for (int ch : nums) {
            if (!hs.containsKey(ch)) {
                hs.put(ch, 1);
            } else {
                int prev = hs.get(ch);
                hs.put(ch, prev + 1);
            }
        }

        int max = 0;
        int ans = 0;
        for (int k : hs.keySet()) {
            if (max < hs.get(k)) {
                max = hs.get(k);
                ans = k;
            }
        }

        return ans;
    }
}