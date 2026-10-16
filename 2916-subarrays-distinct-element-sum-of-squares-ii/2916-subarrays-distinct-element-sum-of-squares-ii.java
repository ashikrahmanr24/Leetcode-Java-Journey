class Solution {
    class Node {
        long sum;
        long lazy;
    }

    Node[] tree;

    void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = new Node();
            return;
        }
        int mid = (start + end) / 2;
        build(2 * node, start, mid);
        build(2 * node + 1, mid + 1, end);
        tree[node] = new Node();
    }

    void push(int node, int start, int end) {
        if (tree[node].lazy != 0) {
            long l = tree[node].lazy;
            int mid = (start + end) / 2;
            
            tree[2 * node].sum = (tree[2 * node].sum + l * (mid - start + 1)) % 1000000007;
            tree[2 * node].lazy = (tree[2 * node].lazy + l) % 1000000007;
            
            tree[2 * node + 1].sum = (tree[2 * node + 1].sum + l * (end - mid)) % 1000000007;
            tree[2 * node + 1].lazy = (tree[2 * node + 1].lazy + l) % 1000000007;
            
            tree[node].lazy = 0;
        }
    }

    void update(int node, int start, int end, int l, int r, long val) {
        if (start > r || end < l) return;
        if (start >= l && end <= r) {
            tree[node].sum = (tree[node].sum + val * (end - start + 1)) % 1000000007;
            tree[node].lazy = (tree[node].lazy + val) % 1000000007;
            return;
        }
        push(node, start, end);
        int mid = (start + end) / 2;
        update(2 * node, start, mid, l, r, val);
        update(2 * node + 1, mid + 1, end, l, r, val);
        tree[node].sum = (tree[2 * node].sum + tree[2 * node + 1].sum) % 1000000007;
    }

    long query(int node, int start, int end, int l, int r) {
        if (start > r || end < l) return 0;
        if (start >= l && end <= r) return tree[node].sum;
        push(node, start, end);
        int mid = (start + end) / 2;
        long p1 = query(2 * node, start, mid, l, r);
        long p2 = query(2 * node + 1, mid + 1, end, l, r);
        return (p1 + p2) % 1000000007;
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;
        tree = new Node[4 * n];
        build(1, 0, n - 1);

        java.util.Map<Integer, Integer> last = new java.util.HashMap<>();
        long currentSum = 0;
        long totalSum = 0;
        long mod = 1000000007;

        for (int j = 0; j < n; j++) {
            int i = last.getOrDefault(nums[j], -1);
            last.put(nums[j], j);

            long sumQuery = query(1, 0, n - 1, i + 1, j);
            currentSum = (currentSum + 2 * sumQuery + (j - i)) % mod;
            totalSum = (totalSum + currentSum) % mod;

            update(1, 0, n - 1, i + 1, j, 1);
        }

        return (int) totalSum;
    }
}