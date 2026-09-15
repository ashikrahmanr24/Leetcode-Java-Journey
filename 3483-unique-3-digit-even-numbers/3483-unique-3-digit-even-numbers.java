class Solution {
    public int totalNumbers(int[] digits) {
        int[] freq = new int[10];

        for (int d : digits) {
            freq[d]++;
        }

        int count = 0;

        for (int num = 100; num <= 999; num += 2) {
            int ones = num % 10;
            int tens = (num / 10) % 10;
            int hundreds = num / 100;

            freq[hundreds]--;
            freq[tens]--;
            freq[ones]--;

            if (freq[hundreds] >= 0 &&
                freq[tens] >= 0 &&
                freq[ones] >= 0) {
                count++;
            }

            freq[hundreds]++;
            freq[tens]++;
            freq[ones]++;
        }

        return count;
    }
}