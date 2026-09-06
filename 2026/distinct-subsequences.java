class Solution {

    private int [][] dp;
    private String sRef , tRef;
    private int sLen , tLen;


    private int solve(int i , int j){
        if (j == tLen) return 1;
        if (i == sLen) return 0;

        if (dp[i][j] != -1) return dp[i][j];


        int ch1 = solve(i+1 , j);

        int ch2 = 0;

        if (sRef.charAt(i) == tRef.charAt(j)){
            ch2 = solve(i+1 , j+1);
        }

        return dp[i][j] = ch1 + ch2;
    }


    
    public int numDistinct(String s, String t) {
        sLen = s.length();
        tLen = t.length();
        dp = new int[sLen][tLen];
        for (int i = 0; i < sLen; ++ i){
            for (int j = 0; j < tLen; ++j){
                dp[i][j] = -1;
            }
        }

        sRef = s;
        tRef = t;
        return solve(0 , 0);
    }
}