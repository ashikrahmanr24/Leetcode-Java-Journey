class Solution {
    int m, n;

    public int countIslands(int[][] grid, int k) {
        m = grid.length;
        n = grid[0].length;

        Queue<int[]> queue = new ArrayDeque<>();
        int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {
                    // start BFS to count islands
                    // queue.offer(new int[]{i, j});
                    // long sum = grid[i][j];
                    // grid[i][j] = 0;

                    // while (!queue.isEmpty()) {
                    //     int[] cur = queue.poll();

                    //     for (int[] dir : dirs) {
                    //         int x = dir[0] + cur[0];
                    //         int y = dir[1] + cur[1];

                    //         if (x < 0 || x >= m || y < 0 || y >= n || grid[x][y] == 0) {
                    //             continue;
                    //         }

                    //         sum += grid[x][y];
                    //         queue.offer(new int[]{x, y});

                    //         grid[x][y] = 0;
                    //     }
                    // }

                    //start dfs
                    long sum = dfs(grid, i, j);

                    if (sum % k == 0) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private long dfs(int[][] grid, int row, int col) {
        if (row < 0 || row >= m || col < 0 || col >= n || grid[row][col] == 0) {
            return 0;
        }

        long sum = grid[row][col];

        // mark visited
        grid[row][col] = 0;

        sum += dfs(grid, row + 1, col);
        sum += dfs(grid, row - 1, col);
        sum += dfs(grid, row, col + 1);
        sum += dfs(grid, row, col - 1);

        return sum;
    }
}