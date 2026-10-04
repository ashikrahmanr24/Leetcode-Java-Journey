class Solution {
    public boolean checkValidString(String s) {
        int low = 0;
        int high = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                low++;
                high++;
            } else if (c == ')') {
                low = Math.max(0, low - 1);
                high--;
            } else {
                low = Math.max(0, low - 1);
                high++;
            }
            if (high < 0) {
                return false;
            }
        }
        return low == 0;
    }
}