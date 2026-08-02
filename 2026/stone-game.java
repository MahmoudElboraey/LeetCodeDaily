class Solution {
    public boolean stoneGame(int[] piles) {
        
    int [][] dp; 
    int n = piles.length;
    dp = new int[n][n];
    for (int i = 0; i < n; i++) dp[i][i] = piles[i];

    for (int L = n-1; L >= 0; --L){ 
        for (int R = L+1; R <  n; ++R){
            dp[L][R] = Math.max(piles[L] - dp[L+1][R] , piles[R] - dp[L][R-1]);
                
        }
    }
    return dp[0][n-1] >= 0;
        
    

        
    }
}