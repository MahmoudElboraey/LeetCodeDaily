class Solution {

    private int [] dp;


    private int solve(int n){
        if (n == 0) return 0;

        if (dp[n] != -1) return dp[n];

        for (int i = 1; i * i <= n; ++i){
            int square = i * i;

            if (solve(n - square) == 0){
                dp[n] = 1;
                return 1;
            }
        }
        return dp[n] = 0;
    }
    public boolean winnerSquareGame(int n) {
        dp = new int[n+1];
        Arrays.fill(dp , -1);
        return solve(n) > 0;
        
    }
}