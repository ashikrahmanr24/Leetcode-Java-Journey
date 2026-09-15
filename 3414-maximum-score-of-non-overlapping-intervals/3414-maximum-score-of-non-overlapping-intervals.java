import java.util.*;

class Solution {

    static class State {
        long score;
        int[] ids;

        State(long score, int[] ids) {
            this.score = score;
            this.ids = ids;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        // [left, right, weight, originalIndex]
        long[][] arr = new long[n][4];

        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0);
            arr[i][1] = intervals.get(i).get(1);
            arr[i][2] = intervals.get(i).get(2);
            arr[i][3] = i;
        }

        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0]) {
                return Long.compare(a[0], b[0]);
            }
            return Long.compare(a[1], b[1]);
        });

        // next[i] = first interval whose left > arr[i].right
        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            int lo = i + 1;
            int hi = n;

            while (lo < hi) {
                int mid = lo + (hi - lo) / 2;

                if (arr[mid][0] > arr[i][1]) {
                    hi = mid;
                } else {
                    lo = mid + 1;
                }
            }

            next[i] = lo;
        }

        /*
         * dp[c][i] = best answer using intervals from i onward,
         * while selecting at most c intervals.
         */
        State[][] dp = new State[5][n + 1];

        for (int c = 0; c <= 4; c++) {
            dp[c][n] = new State(0, new int[0]);
        }

        for (int i = n - 1; i >= 0; i--) {

            dp[0][i] = new State(0, new int[0]);

            for (int c = 1; c <= 4; c++) {

                // Option 1: skip current interval
                State skip = dp[c][i + 1];

                // Option 2: take current interval
                State rest = dp[c - 1][next[i]];

                long takeScore = arr[i][2] + rest.score;

                int[] takeIds = insertSorted(
                    rest.ids,
                    (int) arr[i][3]
                );

                State take = new State(takeScore, takeIds);

                dp[c][i] = better(skip, take);
            }
        }

        return dp[4][0].ids;
    }

    private State better(State a, State b) {

        if (a.score > b.score) {
            return a;
        }

        if (b.score > a.score) {
            return b;
        }

        // Same score -> lexicographically smallest
        if (lexSmaller(a.ids, b.ids)) {
            return a;
        }

        return b;
    }

    private boolean lexSmaller(int[] a, int[] b) {
        int len = Math.min(a.length, b.length);

        for (int i = 0; i < len; i++) {
            if (a[i] != b[i]) {
                return a[i] < b[i];
            }
        }

        return a.length < b.length;
    }

    private int[] insertSorted(int[] arr, int value) {
        int[] result = new int[arr.length + 1];

        int i = 0;

        while (i < arr.length && arr[i] < value) {
            result[i] = arr[i];
            i++;
        }

        result[i] = value;

        while (i < arr.length) {
            result[i + 1] = arr[i];
            i++;
        }

        return result;
    }
}