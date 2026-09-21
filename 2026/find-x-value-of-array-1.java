class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] ans = new long[k];
        long[] dp = new long[k];
        // the number of sub arrays ending at the current position 
        // whose product % k == r

        for (int num : nums) {
            int x = num % k;
            long[] newDp = new long[k];

           
            newDp[x] = 1;

          
            for (int r = 0; r < k; r++) {
                newDp[(int)((long) r * x % k)] += dp[r];
            }

          
            for (int r = 0; r < k; r++) {
                ans[r] += newDp[r];
            }

            dp = newDp;
        }

        return ans;
    }
}