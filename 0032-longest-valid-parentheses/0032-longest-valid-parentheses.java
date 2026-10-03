class Solution {
    public int longestValidParentheses(String s) {
        int n = s.length();
        if (n == 0) return 0;
        
        int[] stack = new int[n + 1];
        int top = 0;
        stack[top] = -1;
        
        int maxLength = 0;
        
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                stack[++top] = i;
            } else {
                top--;
                if (top < 0) {
                    stack[++top] = i;
                } else {
                    int len = i - stack[top];
                    if (len > maxLength) {
                        maxLength = len;
                    }
                }
            }
        }
        
        return maxLength;
    }
}
