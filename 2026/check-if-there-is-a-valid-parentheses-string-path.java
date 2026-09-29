class Solution {
    private int n ,  m;
    private int convert(char c){
        return c == '(' ? 1 : -1;
    }

    private boolean valid(int i , int j){
        return i >= 0 && j >= 0 && i < n && j < m;
    }


    public boolean hasValidPath(char[][] grid) {
        n = grid.length;
        m = grid[0].length;
        int [][] dp = new int[m][205];
      
        for (int i = n-1; i >= 0; i--){
            int [][] curDp = new int[m][205];
            for (int j = m-1; j >= 0; --j){
                int s = convert(grid[i][j]);
                if (i == n-1 && j == m-1 && s == -1){
                    curDp[j][1] = 1;
                    continue;
                }
                for (int sum = 0; sum <= 200; ++sum){

                    
                    if (valid(i+1 , j) && sum + s >= 0){
                        curDp[j][sum] |= dp[j][sum + s];
                    }

                    if (valid(i , j+1) && sum + s >= 0){
                        curDp[j][sum] |= curDp[j+1][sum + s];
                    }
                }
            }
            dp = curDp;
        }

        return dp[0][0] == 1;
    }


}