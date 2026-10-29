import java.util.*;

class Solution {

    int[] head, to, next;
    int edgeCount;

    int[] parent, depth, size, heavy;
    int[] chainHead, pos;
    int currentPos;

    int[] segTree;

    public List<Boolean> palindromePath(int n, int[][] edges,
                                        String s, String[] queries) {

        // Build adjacency list
        head = new int[n];
        Arrays.fill(head, -1);

        to = new int[2 * (n - 1)];
        next = new int[2 * (n - 1)];

        for (int[] e : edges) {
            addEdge(e[0], e[1]);
            addEdge(e[1], e[0]);
        }

        parent = new int[n];
        depth = new int[n];
        size = new int[n];
        heavy = new int[n];

        Arrays.fill(parent, -1);
        Arrays.fill(heavy, -1);

        // Find subtree sizes and heavy child
        dfs(0, -1);

        chainHead = new int[n];
        pos = new int[n];
        currentPos = 0;

        // Heavy-Light Decomposition
        decompose(0, 0);

        // Build array according to HLD positions
        int[] values = new int[n];

        for (int i = 0; i < n; i++) {
            values[pos[i]] = 1 << (s.charAt(i) - 'a');
        }

        // Segment tree
        segTree = new int[4 * n];
        build(1, 0, n - 1, values);

        char[] chars = s.toCharArray();

        List<Boolean> answer = new ArrayList<>();

        for (String query : queries) {

            String[] parts = query.split(" ");

            if (parts[0].equals("update")) {

                int node = Integer.parseInt(parts[1]);
                char newChar = parts[2].charAt(0);

                chars[node] = newChar;

                int mask = 1 << (newChar - 'a');

                update(
                    1,
                    0,
                    n - 1,
                    pos[node],
                    mask
                );

            } else {

                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);

                int mask = queryPath(u, v);

                // At most one character can have odd frequency
                boolean possible =
                    mask == 0 || (mask & (mask - 1)) == 0;

                answer.add(possible);
            }
        }

        return answer;
    }

    // ---------- Graph ----------

    void addEdge(int u, int v) {
        to[edgeCount] = v;
        next[edgeCount] = head[u];
        head[u] = edgeCount++;
    }

    // ---------- First DFS ----------

    void dfs(int node, int par) {

        parent[node] = par;
        size[node] = 1;

        int maxSize = 0;

        for (int e = head[node]; e != -1; e = next[e]) {

            int child = to[e];

            if (child == par)
                continue;

            depth[child] = depth[node] + 1;

            dfs(child, node);

            size[node] += size[child];

            if (size[child] > maxSize) {
                maxSize = size[child];
                heavy[node] = child;
            }
        }
    }

    // ---------- Heavy-Light Decomposition ----------

    void decompose(int node, int chain) {

        chainHead[node] = chain;
        pos[node] = currentPos++;

        // Continue through heavy child
        if (heavy[node] != -1) {
            decompose(heavy[node], chain);
        }

        // Start new chains for light children
        for (int e = head[node]; e != -1; e = next[e]) {

            int child = to[e];

            if (child == parent[node] || child == heavy[node])
                continue;

            decompose(child, child);
        }
    }

    // ---------- Segment Tree ----------

    void build(int node, int left, int right, int[] arr) {

        if (left == right) {
            segTree[node] = arr[left];
            return;
        }

        int mid = (left + right) / 2;

        build(node * 2, left, mid, arr);
        build(node * 2 + 1, mid + 1, right, arr);

        segTree[node] =
            segTree[node * 2] ^
            segTree[node * 2 + 1];
    }

    void update(int node, int left, int right,
                int index, int value) {

        if (left == right) {
            segTree[node] = value;
            return;
        }

        int mid = (left + right) / 2;

        if (index <= mid) {
            update(node * 2, left, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, right, index, value);
        }

        segTree[node] =
            segTree[node * 2] ^
            segTree[node * 2 + 1];
    }

    int query(int node, int left, int right,
              int ql, int qr) {

        if (qr < left || right < ql)
            return 0;

        if (ql <= left && right <= qr)
            return segTree[node];

        int mid = (left + right) / 2;

        return query(node * 2, left, mid, ql, qr)
             ^ query(node * 2 + 1, mid + 1, right, ql, qr);
    }

    // ---------- Tree Path Query ----------

    int queryPath(int u, int v) {

        int result = 0;

        while (chainHead[u] != chainHead[v]) {

            // Always move the deeper chain upward
            if (depth[chainHead[u]] <
                depth[chainHead[v]]) {

                int temp = u;
                u = v;
                v = temp;
            }

            result ^= query(
                1,
                0,
                pos.length - 1,
                pos[chainHead[u]],
                pos[u]
            );

            u = parent[chainHead[u]];
        }

        // Same chain
        if (depth[u] > depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        result ^= query(
            1,
            0,
            pos.length - 1,
            pos[u],
            pos[v]
        );

        return result;
    }
}