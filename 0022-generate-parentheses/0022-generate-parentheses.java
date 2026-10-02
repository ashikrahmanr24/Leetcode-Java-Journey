import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        char[] current = new char[2 * n];
        backtrack(result, current, 0, 0, 0, n);
        return result;
    }

    private void backtrack(List<String> result, char[] current, int index, int open, int close, int max) {
        if (index == max * 2) {
            result.add(new String(current));
            return;
        }

        if (open < max) {
            current[index] = '(';
            backtrack(result, current, index + 1, open + 1, close, max);
        }

        if (close < open) {
            current[index] = ')';
            backtrack(result, current, index + 1, open, close + 1, max);
        }
    }
}
