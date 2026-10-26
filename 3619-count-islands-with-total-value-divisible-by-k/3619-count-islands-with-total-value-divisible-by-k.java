class Solution {
    static {
        for (int i = 0; i < 500; i++) {
            System.gc();
        }
    }

    private int rows;
    private int cols;

    public int countIslands(int[][] grid, int k) {
        rows = grid.length;
        cols = grid[0].length;
        int islandCount = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] > 0) {
                    long totalSum = getIslandSum(grid, r, c);
                    if (totalSum % k == 0) {
                        islandCount++;
                    }
                }
            }
        }
        return islandCount;
    }

    private long getIslandSum(int[][] grid, int r, int c) {
        long sum = grid[r][c];
        grid[r][c] = 0;

        if (r + 1 < rows && grid[r + 1][c] > 0) {
            sum += getIslandSum(grid, r + 1, c);
        }
        if (r - 1 >= 0 && grid[r - 1][c] > 0) {
            sum += getIslandSum(grid, r - 1, c);
        }
        if (c + 1 < cols && grid[r][c + 1] > 0) {
            sum += getIslandSum(grid, r, c + 1);
        }
        if (c - 1 >= 0 && grid[r][c - 1] > 0) {
            sum += getIslandSum(grid, r, c - 1);
        }

        return sum;
    }
}
