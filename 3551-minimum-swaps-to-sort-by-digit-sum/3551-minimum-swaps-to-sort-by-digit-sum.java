import java.util.*;

class Solution {

    class Element {
        int number;
        int digitSum;
        int originalIndex;

        Element(int number, int digitSum, int originalIndex) {
            this.number = number;
            this.digitSum = digitSum;
            this.originalIndex = originalIndex;
        }
    }

    public int sumDigit(int num) {
        int sum = 0;

        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }

        return sum;
    }

    public int minSwaps(int[] nums) {

        int n = nums.length;

        Element[] arr = new Element[n];

        // Create objects
        for (int i = 0; i < n; i++) {
            arr[i] = new Element(
                nums[i],
                sumDigit(nums[i]),
                i
            );
        }

        // Sort by digit sum, then by number
        Arrays.sort(arr, (a, b) -> {
            if (a.digitSum != b.digitSum) {
                return Integer.compare(a.digitSum, b.digitSum);
            }

            return Integer.compare(a.number, b.number);
        });

        // pos[originalIndex] = current position
        int[] pos = new int[n];

        for (int i = 0; i < n; i++) {
            pos[arr[i].originalIndex] = i;
        }

        int swaps = 0;

        for (int i = 0; i < n; i++) {

            // Already correct
            if (arr[i].originalIndex == i) {
                continue;
            }

            int correctPosition = pos[i];

            // Swap current element with the element
            // that originally belongs at position i
            Element temp = arr[i];
            arr[i] = arr[correctPosition];
            arr[correctPosition] = temp;

            // Update positions
            pos[arr[correctPosition].originalIndex] = correctPosition;
            pos[arr[i].originalIndex] = i;

            swaps++;
        }

        return swaps;
    }
}