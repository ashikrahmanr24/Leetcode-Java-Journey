class Solution {
    public int reverseDegree(String s) {
        int totalSum = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            int reversedValue = 26 - (c - 'a');
            int position = i + 1;
            totalSum += reversedValue * position;
        }
        
        return totalSum;
    }
}
