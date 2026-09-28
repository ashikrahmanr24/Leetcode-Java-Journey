class Solution {
    public int maxDepth(String s) {
        int max = 0;
        int current = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                current++;
                if (current > max) {
                    max = current;
                }
            } else if (c == ')') {
                current--;
            }
        }
        
        return max;
    }
}