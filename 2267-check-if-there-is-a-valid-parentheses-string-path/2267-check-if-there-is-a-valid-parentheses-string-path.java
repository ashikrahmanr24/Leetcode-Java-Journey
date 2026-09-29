class Solution {
    private int m, n;
    private char[][] grid;
    private boolean[][][] visited;

    public boolean hasValidPath(char[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;
        this.grid = grid;
        
        int maxLen = m + n - 1;
        
        // Pruning 1: The total length of the path must be even to form a valid pair of brackets
        if (maxLen % 2 != 0) return false;
        
        // Pruning 2: The path must start with '(' and end with ')'
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') return false;
        
        // Max possible open brackets at any point cannot exceed half the total path length
        int maxBalance = maxLen / 2;
        
        // visited[r][c][balance]
        this.visited = new boolean[m][n][maxBalance + 1];

        return dfs(0, 0, 0);
    }

    private boolean dfs(int r, int c, int balance) {
        // Update balance: +1 for '(', -1 for ')'
        balance += (grid[r][c] == '(') ? 1 : -1;

        // Pruning 3: Invalid balance (must not be negative, must not exceed max possible open brackets)
        if (balance < 0 || balance > (m + n - 1) / 2) {
            return false;
        }

        // Target reached
        if (r == m - 1 && c == n - 1) {
            return balance == 0;
        }

        // Pruning 4: If this exact state was already processed and failed, return false
        if (visited[r][c][balance]) {
            return false;
        }
        
        // Mark state as visited
        visited[r][c][balance] = true;

        // Try moving down
        if (r + 1 < m && dfs(r + 1, c, balance)) {
            return true;
        }
        
        // Try moving right
        if (c + 1 < n && dfs(r, c + 1, balance)) {
            return true;
        }

        return false;
    }
}