class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        int[] ones1 = new int[n * n];
        int[] ones2 = new int[n * n];
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) {
                    ones1[count1++] = (i << 6) | j;
                }
                if (img2[i][j] == 1) {
                    ones2[count2++] = (i << 6) | j;
                }
            }
        }
        int[][] transformCounts = new int[61][61];
        int maxOverlap = 0;
        for (int i = 0; i < count1; i++) {
            int r1 = ones1[i] >> 6;
            int c1 = ones1[i] & 63;
            
            for (int j = 0; j < count2; j++) {
                int r2 = ones2[j] >> 6;
                int c2 = ones2[j] & 63;
                int rowDiff = r1 - r2 + 30;
                int colDiff = c1 - c2 + 30;
                
                transformCounts[rowDiff][colDiff]++;
                if (transformCounts[rowDiff][colDiff] > maxOverlap) {
                    maxOverlap = transformCounts[rowDiff][colDiff];
                }
            }
        }
        
        return maxOverlap;
    }
}
