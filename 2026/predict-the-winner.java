class Solution {
    private int [][][] dp; // what is the max score i can get if i reduced the array to L , R 

    private int solve(int L , int R , int sign , int [] a){
        if (L > R) return 0;

        if (dp[L][R][sign] != -1) return dp[L][R][sign];

        int s = (sign == 0 ? 1 : -1);
        // if sign is zero this me triying to win i need to get the max 

        int ch1 = a[L] * s + solve(L+1 , R , sign ^ 1 , a);
        int ch2 = a[R] * s + solve(L , R-1 , sign ^ 1 , a);
        int ans = Math.max(ch1 , ch2);
        if (sign == 1) ans = Math.min(ch1 , ch2);
        return dp[L][R][sign] = ans;
    }
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        dp = new int[n][n][2];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; ++j){
                for (int k = 0; k < 2; ++k){
                    dp[i][j][k] = -1;
                }
            }
        }
        int ans = solve(0 , n-1 , 0 , nums);
        return ans >= 0;
        
    }
}