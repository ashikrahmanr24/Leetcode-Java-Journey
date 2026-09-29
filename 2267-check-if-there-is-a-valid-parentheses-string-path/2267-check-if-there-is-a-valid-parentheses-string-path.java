class Solution {
    private boolean[][][] visited;

    public boolean hasValidPath(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        if ((m + n - 1) % 2 != 0 || grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }

        int maxBal = (m + n) / 2;
        visited = new boolean[m][n][maxBal + 1];

        return dfs(grid, 0, 0, 0, m, n, maxBal);
    }

    private boolean dfs(char[][] grid, int r, int c, int bal, int m, int n, int maxBal) {
        bal += (grid[r][c] == '(' ? 1 : -1);
        if (bal < 0 || bal > maxBal) return false;

        if (r == m - 1 && c == n - 1) {
            return bal == 0;
        }

        if (visited[r][c][bal]) return false;
        visited[r][c][bal] = true;

        if (r + 1 < m && dfs(grid, r + 1, c, bal, m, n, maxBal)) return true;
        if (c + 1 < n && dfs(grid, r, c + 1, bal, m, n, maxBal)) return true;

        return false;
    }
}