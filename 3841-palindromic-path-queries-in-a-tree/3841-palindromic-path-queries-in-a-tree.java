import java.util.*;

class Solution {
    List<Integer>[] adj;
    char[] sArr;
    int[] in, out, depth, nodeOfIndex;
    int timer = 0;
    int[][] up; // binary lifting for LCA
    int LOG;
    int[] toRootMask;
    
    // Segment Tree for range updates / point queries or XOR range updates
    class SegmentTree {
        int n;
        int[] tree, lazy;
        
        SegmentTree(int n) {
            this.n = n;
            tree = new int[4 * n];
            lazy = new int[4 * n];
        }
        
        void push(int node) {
            if (lazy[node] != 0) {
                tree[2 * node] ^= lazy[node];
                lazy[2 * node] ^= lazy[node];
                tree[2 * node + 1] ^= lazy[node];
                lazy[2 * node + 1] ^= lazy[node];
                lazy[node] = 0;
            }
        }
        
        void updateRange(int L, int R, int val, int node, int l, int r) {
            if (L <= l && r <= R) {
                tree[node] ^= val;
                lazy[node] ^= val;
                return;
            }
            push(node);
            int mid = (l + r) / 2;
            if (L <= mid) updateRange(L, R, val, 2 * node, l, mid);
            if (R > mid) updateRange(L, R, val, 2 * node + 1, mid + 1, r);
            tree[node] = tree[2 * node] ^ tree[2 * node + 1];
        }
        
        int query(int idx, int node, int l, int r) {
            if (l == r) return tree[node];
            push(node);
            int mid = (l + r) / 2;
            if (idx <= mid) return query(idx, 2 * node, l, mid);
            else return query(idx, 2 * node + 1, mid + 1, r);
        }
    }
    
    private void dfs(int u, int p, int d, int mask) {
        in[u] = ++timer;
        nodeOfIndex[timer] = u;
        depth[u] = d;
        toRootMask[u] = mask ^ (1 << (sArr[u] - 'a'));
        up[u][0] = p;
        for (int i = 1; i <= LOG; i++) {
            up[u][i] = up[up[u][i - 1]][i - 1];
        }
        for (int v : adj[u]) {
            if (v != p) {
                dfs(v, u, d + 1, toRootMask[u]);
            }
        }
        out[u] = timer;
    }
    
    private int lca(int u, int v) {
        if (depth[u] < depth[v]) {
            int t = u; u = v; v = t;
        }
        for (int i = LOG; i >= 0; i--) {
            if (depth[u] - (1 << i) >= depth[v]) {
                u = up[u][i];
            }
        }
        if (u == v) return u;
        for (int i = LOG; i >= 0; i--) {
            if (up[u][i] != up[v][i]) {
                u = up[u][i];
                v = up[v][i];
            }
        }
        return up[u][0];
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }
        sArr = s.toCharArray();
        in = new int[n + 1];
        out = new int[n + 1];
        depth = new int[n];
        nodeOfIndex = new int[n + 1];
        toRootMask = new int[n];
        LOG = 32 - Integer.numberOfLeadingZeros(n);
        up = new int[n][LOG + 1];
        
        dfs(0, 0, 0, 0);
        
        SegmentTree st = new SegmentTree(n + 1);
        for (int i = 0; i < n; i++) {
            st.updateRange(in[i], in[i], toRootMask[i], 1, 1, n);
        }
        
        List<Boolean> ans = new ArrayList<>();
        for (String qStr : queries) {
            String[] parts = qStr.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char newChar = parts[2].charAt(0);
                char oldChar = sArr[u];
                if (oldChar != newChar) {
                    int diffMask = (1 << (oldChar - 'a')) ^ (1 << (newChar - 'a'));
                    st.updateRange(in[u], out[u], diffMask, 1, 1, n);
                    sArr[u] = newChar;
                }
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int LCA = lca(u, v);
                
                int maskU = st.query(in[u], 1, 1, n);
                int maskV = st.query(in[v], 1, 1, n);
                int maskLCA = 1 << (sArr[LCA] - 'a');
                
                int pathMask = maskU ^ maskV ^ maskLCA;
                ans.add(Integer.bitCount(pathMask) <= 1);
            }
        }
        return ans;
    }
}
