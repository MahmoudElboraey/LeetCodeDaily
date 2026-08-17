class Solution {

    private int [] preSum;
    private int n;
    private int [][] dp;

    private int getSum(int L , int R){
        return (L > 0 ? preSum[R] - preSum[L-1] : preSum[R]);
    }

    private int solve(int L , int R){
        if (L == R) return 0;

        if (dp[L][R] != -1) return dp[L][R];

        int ans = 0;
        for (int k = L ; k < R; ++k){
            // try to split here
            int partOne = getSum(L , k);
            int partTwo = getSum(k+1 , R);
            if (partOne == partTwo){
                ans = Math.max(ans , partOne + solve(L , k));
                ans = Math.max(ans , partTwo + solve(k+1 , R));
            }else if (partOne > partTwo) {
                ans = Math.max(ans , partTwo + solve(k+1 , R));
            }else {
                // partTwo > partOne 
                ans = Math.max(ans , partOne + solve(L , k));
            }
        }

        return dp[L][R] = ans;
    }


    public int stoneGameV(int[] stoneValue) {
        n = stoneValue.length;
        preSum = new int[n];
        preSum[0] = stoneValue[0];
        for (int i = 1; i < n; ++i){
            preSum[i] = preSum[i-1] + stoneValue[i];
        }
        dp = new int[n+1][n+1];
        for (int i = 0; i <= n; ++i) Arrays.fill(dp[i] , -1);

        return solve(0 , n-1);

        
    }
}