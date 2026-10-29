import java.util.*;

class Solution {

    private static final int LOG = 17;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {

        // ---------- Build tree ----------
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        int[][] parent = new int[LOG][n];
        for (int[] row : parent) Arrays.fill(row, -1);

        int[] depth = new int[n];
        int[] tin = new int[n];
        int[] tout = new int[n];
        int[] timer = {0};

        // ---------- DFS ----------
        dfs(0, -1, graph, parent, depth, tin, tout, timer);

        // ---------- Binary lifting ----------
        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                int p = parent[k - 1][i];
                if (p != -1)
                    parent[k][i] = parent[k - 1][p];
            }
        }

        // ---------- Fenwick tree ----------
        Fenwick fenwick = new Fenwick(n);

        char[] labels = s.toCharArray();
        for (int i = 0; i < n; i++) {
            int m = mask(labels[i]);
            fenwick.add(tin[i], m);
            fenwick.add(tout[i] + 1, m);
        }

        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            String[] p = q.split(" ");

            if (p[0].equals("update")) {

                int u = Integer.parseInt(p[1]);
                char c = p[2].charAt(0);

                int delta = mask(labels[u]) ^ mask(c);
                labels[u] = c;

                fenwick.add(tin[u], delta);
                fenwick.add(tout[u] + 1, delta);

            } else {

                int u = Integer.parseInt(p[1]);
                int v = Integer.parseInt(p[2]);
                int w = lca(u, v, parent, depth);

                int m = fenwick.sum(tin[u])
                        ^ fenwick.sum(tin[v])
                        ^ mask(labels[w]);

                ans.add(m == 0 || (m & (m - 1)) == 0);
            }
        }

        return ans;
    }

    // ---------- DFS helper ----------
    private void dfs(int u, int p,
                     List<Integer>[] g,
                     int[][] parent,
                     int[] depth,
                     int[] tin,
                     int[] tout,
                     int[] timer) {

        tin[u] = timer[0]++;
        parent[0][u] = p;

        for (int v : g[u]) {
            if (v == p) continue;
            depth[v] = depth[u] + 1;
            dfs(v, u, g, parent, depth, tin, tout, timer);
        }

        tout[u] = timer[0] - 1;
    }

    // ---------- LCA ----------
    private int lca(int u, int v, int[][] parent, int[] depth) {

        if (depth[u] < depth[v]) {
            int t = u; u = v; v = t;
        }

        int d = depth[u] - depth[v];
        for (int k = 0; k < LOG; k++)
            if ((d & (1 << k)) != 0)
                u = parent[k][u];

        if (u == v) return u;

        for (int k = LOG - 1; k >= 0; k--) {
            if (parent[k][u] != parent[k][v]) {
                u = parent[k][u];
                v = parent[k][v];
            }
        }

        return parent[0][u];
    }

    private int mask(char c) {
        return 1 << (c - 'a');
    }

    // ---------- Fenwick Tree ----------
    static class Fenwick {
        int n;
        int[] bit;

        Fenwick(int n) {
            this.n = n;
            bit = new int[n + 1];
        }

        void add(int i, int val) {
            for (i++; i <= n; i += i & -i)
                bit[i] ^= val;
        }

        int sum(int i) {
            int r = 0;
            for (i++; i > 0; i -= i & -i)
                r ^= bit[i];
            return r;
        }
    }
}