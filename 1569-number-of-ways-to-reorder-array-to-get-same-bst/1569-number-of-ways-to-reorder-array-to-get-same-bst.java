class Solution {
    private static final long MOD = 1_000_000_007;
    private static final long[][] C = new long[1001][1001];

    static {
        for (int i = 0; i <= 1000; i++) {
            C[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                C[i][j] = (C[i - 1][j - 1] + C[i - 1][j]) % MOD;
            }
        }
    }

    public int numOfWays(int[] nums) {
        List<Integer> list = new ArrayList<>(nums.length);
        for (int x : nums) {
            list.add(x);
        }
        return (int)((dfs(list) - 1 + MOD) % MOD);
    }

    private long dfs(List<Integer> nums) {
        int n = nums.size();
        if (n <= 2) {
            return 1;
        }

        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        int root = nums.get(0);

        for (int i = 1; i < n; i++) {
            int val = nums.get(i);
            if (val < root) {
                left.add(val);
            } else {
                right.add(val);
            }
        }

        long leftWays = dfs(left);
        long rightWays = dfs(right);

        long comb = C[n - 1][left.size()];
        long ans = (comb * leftWays) % MOD;
        ans = (ans * rightWays) % MOD;
        return ans;
    }
}