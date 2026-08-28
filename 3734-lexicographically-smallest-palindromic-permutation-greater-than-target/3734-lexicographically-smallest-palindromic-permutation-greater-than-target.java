class Solution {

    public String lexPalindromicPermutation(String s, String target) {

        int n = s.length();
        int half = n / 2;

        // Count characters
        int[] count = new int[26];

        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }

        // A palindrome can have at most one odd frequency
        int odd = 0;
        int middle = -1;

        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 == 1) {
                odd++;
                middle = i;
            }
        }

        if (odd > 1) {
            return "";
        }

        // Remove the middle character if n is odd
        if (middle != -1) {
            count[middle]--;
        }

        /*
         * result = left half of the answer.
         *
         * First try to make the left half equal to
         * target's left half.
         */
        StringBuilder result = new StringBuilder(half);

        int i;

        for (i = 0; i < half; i++) {

            int c = target.charAt(i) - 'a';

            count[c] -= 2;
            result.append(target.charAt(i));

            // We don't have enough copies
            if (count[c] < 0) {
                break;
            }
        }

        /*
         * Case 1:
         * Entire target left half was possible.
         *
         * Construct the palindrome and check whether
         * it is already greater than target.
         */
        if (i == half) {

            String candidate = buildPalindrome(result, middle, n);

            if (candidate.compareTo(target) > 0) {
                return candidate;
            }
        }

        /*
         * Case 2:
         * Backtrack from right to left.
         *
         * We want to change one character to the
         * smallest possible character greater than
         * target[i].
         */
        while (result.length() > 0) {

            // Remove last character from prefix
            int current = result.charAt(result.length() - 1) - 'a';
            result.deleteCharAt(result.length() - 1);

            // Give its two copies back
            count[current] += 2;

            /*
             * Try a character slightly bigger than
             * target[current position].
             */
            for (int next = current + 1; next < 26; next++) {

                if (count[next] >= 2) {

                    // Use this bigger character
                    count[next] -= 2;
                    result.append((char) ('a' + next));

                    /*
                     * Fill the remaining left half with
                     * the smallest possible characters.
                     */
                    for (int c = 0; c < 26; c++) {

                        while (count[c] >= 2) {
                            count[c] -= 2;
                            result.append((char) ('a' + c));
                        }
                    }

                    return buildPalindrome(result, middle, n);
                }
            }
        }

        return "";
    }


    // Construct complete palindrome from left half
    private String buildPalindrome(StringBuilder left, int middle, int n) {

        StringBuilder answer = new StringBuilder(n);

        // Left half
        answer.append(left);

        // Middle character
        if (middle != -1) {
            answer.append((char) ('a' + middle));
        }

        // Right half = reverse of left half
        for (int i = left.length() - 1; i >= 0; i--) {
            answer.append(left.charAt(i));
        }

        return answer.toString();
    }
}