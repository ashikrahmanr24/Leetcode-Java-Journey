class Solution {
    public boolean uniformArray(int[] nums1) {
        int minOdd = Integer.MAX_VALUE;
        int minEven = Integer.MAX_VALUE;
        for (int num : nums1) {
            if ((num & 1) == 1) {
                if (num < minOdd) {
                    minOdd = num;
                }
            } else {
                if (num < minEven) {
                    minEven = num;
                }
            }
        }
        if (minOdd == Integer.MAX_VALUE) {
            return true;
        }
        return minEven > minOdd;
    }
}
