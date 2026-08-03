class Solution {
    // dp[i] = the difference between max score of alex - max score of bob if we reach to stone i
    // dp[i] = Math of (stone (i , i+1 , i+2) - dp[x] , x : is the ith +1 stone)


    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int [] dp = new int[n+1];
        Arrays.fill(dp , Integer.MIN_VALUE);
        dp[n] = 0;

        
        for (int i = n-1; i >= 0; i--){
            int sum = 0;
            for (int k = 0; k < 3 && i +k < n; ++k){
                sum+= stoneValue[i+k];
                dp[i] = Math.max(dp[i] , sum - dp[i+k+1] );
            }
        }
        String ans = "Tie";
        if (dp[0] > 0){
            ans = "Alice";
        }else if (dp[0] < 0){
            ans = "Bob";
        }
        return ans;
        
    }
}