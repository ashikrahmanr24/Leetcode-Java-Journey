class Solution {
    public int numberOfSets(int n, int k) {
        int N = n + k - 1;
        int R = 2 * k;
        if (R > N) return 0;
        if (R > N - R) R = N - R;
        long num = 1;
        long den = 1;
        long mod = 1000000007;
        for (int i = 1; i <= R; i++) {
            num = (num * (N - i + 1)) % mod;
            den = (den * i) % mod;
        }
        return (int) ((num * modInverse(den, mod)) % mod);
    }

    private long modInverse(long a, long m) {
        return power(a, m - 2, m);
    }

    private long power(long x, long y, long m) {
        long res = 1;
        x = x % m;
        while (y > 0) {
            if ((y & 1) == 1) res = (res * x) % m;
            y = y >> 1;
            x = (x * x) % m;
        }
        return res;
    }
}
