
  class Solution {
      public int stoneGameVIII(int[] stones) {
          int n = stones.length;
          long[] p = new long[n];
          p[0] = stones[0];
          for (int i = 1; i < n; i++) p[i] = p[i - 1] + stones[i];
  
          long dp = p[n - 1];                      
          for (int i = n - 2; i >= 1; i--)
              dp = Math.max(dp, p[i] - dp);       
          return (int) dp;
      }
  }