class Solution {
    private int [][][][]dp;
    private int n;
    private int ans;
    // private int solve(int p , int i , int last , int [] piles){
    //     if (i == n) return 0;

    //     if (dp[p][i][last] != -1) return dp[p][i][last];

    //     int ret = Integer.MAX_VALUE * (p > 0 ? -1 : 1);
    //     int sum = 0;
    //     int R = Math.min(n , i + 2 * last );
    //     for (int k = i; k < R ; ++k){
    //         sum+= piles[k];
    //         int ch = solve(p ^ 1 , k+1 ,Math.max(last ,  k - i + 1) , piles);
    //         if (p > 0){
    //             // alex is playing i need to get the min for pop
    //             ret = Math.max(ret , sum +ch);
    //         }else {
    //             // when pop is playing i need to get the min for alex 
    //             ret = Math.min(ret , -sum + ch);
    //         }
    //     }
    //     return dp[p][i][last]= ret;
    // }
    // private void build(int p , int i , int last , int [] piles){
    //     if (i == n) return ;

    //     int opt = solve(p , i  , last , piles);

    //     int ret = Integer.MAX_VALUE * (p > 0 ? -1 : 1);
    //     int sum = 0;
    //     int R = Math.min(n , i + 2 * last );
    //     for (int k = i; k < R ; ++k){
    //         sum+= piles[k];
    //         int ch = solve(p ^ 1 , k+1 ,Math.max(last ,  k - i + 1) , piles);
    //         if (p > 0){
    //             // alex is playing i need to get the min for pop
    //             ret = sum +ch;
    //         }else {
    //             // when pop is playing i need to get the min for alex 
    //             ret = -sum + ch;
    //         }
    //         if (ret == opt){
    //             if (p == 1) ans+= sum;
    //           //  System.out.println(i + " " + k + "  " + p + "  " + last  + "  " + R);
    //              build(p ^ 1 , k+1 , Math.max(last ,  k - i + 1) , piles);
    //              return;
    //         }

    //     }
    // }
    private int solveDp(int [] piles){
        for (int i = 0; i < 2; ++i) for (int k = 0; k <=n; ++k) 
        for (int j = 0; j < 2; ++j ) dp[i][n][k][j]= 0;

        for (int i = n-1; i >= 0; --i){
            for (int sz = 1;  sz <= n; ++sz){
                for (int p = 0; p < 2; ++p){
                    int ret = Integer.MAX_VALUE * (p > 0 ? -1 : 1);
                    int sum = 0; int retVal = 0;
                    int R = Math.min(n , i + 2 * sz);
                    for (int k = i; k < R ; ++k){
                         sum+= piles[k];
                         //int ch = solve(p ^ 1 , k+1 ,Math.max(last ,  k - i + 1) , piles);
                         int ch = dp[p ^ 1][k+1][Math.max(sz , k - i + 1)][0];
                         int ansVal = dp[p ^ 1][k+1][Math.max(sz , k - i + 1)][1];
                         if (p > 0){
                           // ret = Math.max(ret , sum +ch);
                            if (sum + ch > ret){
                                ret= sum + ch;
                                retVal = sum + ansVal;
                            }//else if (sum + ch == ret){
                            //     retVal = Math.max(retVal , sum+ ansVal);
                            // }
                         }
                         else {
                            if (ret > -sum + ch){
                                ret = -sum + ch;
                                retVal = ansVal;
                            }
                            // else if (ret == -sum + ch){
                            //     retVal = Math.max(retVal , ansVal);
                            // }
                           // ret = Math.min(ret , -sum + ch);
                         }
                    }
                    dp[p][i][sz][0]=ret;
                    dp[p][i][sz][1] = retVal;
                    
                }
            }
        }

        return dp[1][0][1][1];
    }

    public int stoneGameII(int[] piles) {
        n = piles.length; ans = 0;
        dp = new int [2][n+1][n+1][2];
        //for (int i = 0; i < 2; ++i) for (int j = 0; j < n; ++j) for (int k = 0; k <= n; ++k) dp[i][j][k] = -1;
       System.out.println(solveDp(piles));
       // for (int i = 0; i < 2; ++i) for (int j = 0; j <= n; ++j) for (int k = 0; k <= n; ++k) dp[i][j][k] = -1;
       // build(1 , 0 , 1, piles);
        return solveDp(piles);
        
    }
}