class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        char[] arr = num.toCharArray();
        
        int sumDiff = 0;
        int qDiff = 0;
        int mid = n / 2;
        
        // Process the left half
        for (int i = 0; i < mid; i++) {
            if (arr[i] == '?') {
                qDiff++;
            } else {
                sumDiff += arr[i] - '0';
            }
        }
        
        // Process the right half
        for (int i = mid; i < n; i++) {
            if (arr[i] == '?') {
                qDiff--;
            } else {
                sumDiff -= arr[i] - '0';
            }
        }
        
        // 1. If the total number of '?' is odd, Alice always wins.
        // 2. Bob can only balance the sums if the difference matches exactly 4.5 per pair of '?'.
        return qDiff % 2 != 0 || sumDiff != -qDiff * 9 / 2;
    }
}
