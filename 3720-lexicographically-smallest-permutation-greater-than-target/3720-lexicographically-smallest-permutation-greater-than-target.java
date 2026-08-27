class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            count[s.charAt(i) - 'a']++;
        }

        // Step 1: Find the maximum prefix length of target that can be formed by s
        int L = 0;
        int[] tempCount = count.clone();
        while (L < n) {
            int idx = target.charAt(L) - 'a';
            if (tempCount[idx] > 0) {
                tempCount[idx]--;
                L++;
            } else {
                break;
            }
        }

        // Step 2: Search from right to left for the first valid position to exceed target
        // We can at most match a prefix up to length min(n - 1, L)
        for (int i = Math.min(n - 1, L); i >= 0; i--) {
            // Recompute available characters if we only match target up to i-1
            int[] currentCount = count.clone();
            for (int j = 0; j < i; j++) {
                currentCount[target.charAt(j) - 'a']--;
            }

            int targetCharIdx = target.charAt(i) - 'a';
            // Find the smallest character strictly greater than target.charAt(i)
            int chosenIdx = -1;
            for (int c = targetCharIdx + 1; c < 26; c++) {
                if (currentCount[c] > 0) {
                    chosenIdx = c;
                    break;
                }
            }

            // If a valid character is found, construct the final answer
            if (chosenIdx != -1) {
                StringBuilder sb = new StringBuilder();
                // Append the matching prefix
                sb.append(target.substring(0, i));
                // Append the strictly greater character
                sb.append((char) ('a' + chosenIdx));
                currentCount[chosenIdx]--;

                // Append all remaining characters in ascending order (smallest possible tail)
                for (int c = 0; c < 26; c++) {
                    while (currentCount[c] > 0) {
                        sb.append((char) ('a' + c));
                        currentCount[c]--;
                    }
                }
                return sb.toString();
            }
        }

        return "";
    }
}
