import java.util.*;

class Solution {
    static class State {
        int r, c, mask, energy, moves;

        State(int r, int c, int mask, int energy, int moves) {
            this.r = r;
            this.c = c;
            this.mask = mask;
            this.energy = energy;
            this.moves = moves;
        }
    }

    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        char[][] grid = new char[m][n];
        
        int startR = -1, startC = -1;
        int litterCount = 0;
        int[][] litterId = new int[m][n];
        

        for (int i = 0; i < m; i++) {
            Arrays.fill(litterId[i], -1);
            for (int j = 0; j < n; j++) {
                grid[i][j] = classroom[i].charAt(j);
                if (grid[i][j] == 'S') {
                    startR = i;
                    startC = j;
                } else if (grid[i][j] == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }
        
        int targetMask = (1 << litterCount) - 1;
        

        int[][][] maxEnergy = new int[m][n][1 << litterCount];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(maxEnergy[i][j], -1);
            }
        }
        
        Queue<State> queue = new LinkedList<>();
        queue.offer(new State(startR, startC, 0, energy, 0));
        maxEnergy[startR][startC][0] = energy;
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        while (!queue.isEmpty()) {
            State curr = queue.poll();
            

            if (curr.mask == targetMask) {
                return curr.moves;
            }
            

            if (curr.energy < maxEnergy[curr.r][curr.c][curr.mask]) {
                continue;
            }
            
            if (curr.energy == 0) {
                continue;
            }
            
            for (int i = 0; i < 4; i++) {
                int nr = curr.r + dr[i];
                int nc = curr.c + dc[i];
                
                if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] != 'X') {
                    char cellType = grid[nr][nc];
                    int nextMask = curr.mask;
                    int nextEnergy = curr.energy - 1;
                    
                    if (cellType == 'L') {
                        nextMask |= (1 << litterId[nr][nc]);
                    } else if (cellType == 'R') {
                        nextEnergy = energy;
                    }
                    

                    if (nextEnergy > maxEnergy[nr][nc][nextMask]) {
                        maxEnergy[nr][nc][nextMask] = nextEnergy;
                        queue.offer(new State(nr, nc, nextMask, nextEnergy, curr.moves + 1));
                    }
                }
            }
        }
        
        return -1;
    }
}
