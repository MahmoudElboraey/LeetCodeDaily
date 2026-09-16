class Solution {

    private int mod = (int)1e9+7;

    private int [][][] dp;

    private int add(int a , int b){
        return (a + b) % mod;
    }
    private int N  , K;
    private int solve(int i , int count , int st){
        if (count == K) return 1;
        if (i > N) return 0;
        
       
        if (dp[i][count][st] != -1) return dp[i][count][st];


        int ch1 = 0;
        int ch2 = 0;
        int ch3 = 0;

        if (st == 0){
            ch1 = solve(i+1 , count , 0);
            ch2 = solve(i+1 , count, 1); // go start
        }else {
            ch1 = solve(i+1 , count , st);
            ch2 = solve(i, count + 1, 0); 
        }


        return dp[i][count][st] =  add(ch1 , ch2) ;

    }
    public int numberOfSets(int n, int k) {
        dp = new int[n+1][k+1][2];
        this.N = n;
        this.K = k;
        for (int i = 0; i <= n; ++i){
            for(int j = 0; j <=k ;++j){
                Arrays.fill(dp[i][j] , -1);
            }
        }
        // st -> means did i start 
        return solve(1 , 0 , 0);
        
    }
}