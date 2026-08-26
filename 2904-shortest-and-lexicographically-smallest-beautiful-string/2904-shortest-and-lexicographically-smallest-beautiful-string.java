class Solution {
    public String shortestBeautifulSubstring(String s, int k) {

        int n = s.length();

        // Store positions of all 1s
        int[] ones = new int[n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                ones[count++] = i;
            }
        }

        // Not enough 1s
        if (count < k) {
            return "";
        }

        int minLength = Integer.MAX_VALUE;
        String answer = "";

        // Check every group of k consecutive 1s
        for (int i = 0; i <= count - k; i++) {

            int start = ones[i];
            int end = ones[i + k - 1];

            int length = end - start + 1;

            String current = s.substring(start, end + 1);

            if (length < minLength) {
                minLength = length;
                answer = current;
            } 
            else if (length == minLength && current.compareTo(answer) < 0) {
                answer = current;
            }
        }

        return answer;
    }
}