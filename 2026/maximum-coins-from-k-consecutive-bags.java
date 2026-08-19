class Solution {
    public long maximumCoins(int[][] coins, int k) {
        Arrays.sort(coins , (x , y) -> Integer.compare(x[0] , y[0]));
        int n = coins.length;

        long [] preSum = new long[n+1];

        for (int i = 0; i < n; ++i){
            int L = coins[i][0];
            int R = coins[i][1];
            int value = coins[i][2];
            preSum[i+1] = preSum[i] + value *1L * (R - L + 1);
        }

        long ans = 0;


        for (int i = 0; i < n; ++i){
            int L = coins[i][0];
            int R = coins[i][1];
            int value = coins[i][2];
            long cand = 0;

            int sz = R - L + 1;

            // try to take from L too L + k -1;
            int last = L + k -1;
            // find last L such that L <= last;
            int idx = i;
            int st = i , en = n-1;
            while(st <= en){
                int md = (st + en) / 2;
                if (coins[md][0] <= last){
                    idx = md;
                    st = md + 1;
                }else {
                    en = md -1;
                }
            }

            if (sz >= k){
                cand = Math.min(k , R - L + 1) * 1L * value;
            }else
            
            {
                cand = preSum[idx] - preSum[i];
                int remain = Math.min(last , coins[idx][1]) - coins[idx][0] + 1;
                cand += remain * 1L * coins[idx][2];
            }
          //  System.out.println("cand is " + cand + " idx is " + idx);
            ans = Math.max(ans , cand);
            cand = 0;
//-----------------------------------------------------------------------------------
            // try to take from R to R -k + 1;

            st = 0 ; en = i;
            last = R - k + 1; 
            idx = i;
            // find the L in the range last , R 
            while (st <= en){
                int md = (st + en) / 2;
                if (coins[md][0] >= last){
                    idx = md; 
                    en = md -1;
                }else  {
                    st = md + 1;
                }
            }


        {
                // what i have right now 
                // range such that all it's L  >= last 
                // this means idx -1 may have some 

            // System.out.println(sz + " i " + i + " " + k + " last " + last);

            if (sz >= k){
                cand = Math.min(k , sz) * 1L * value;

            }else {

                cand = preSum[i+1] - preSum[idx];
                idx-=1;
                if (idx >= 0){
                    // try to get the remaining part 
                    int remain = coins[idx][1] - Math.max(last , coins[idx][0]) + 1;
                    if (remain > 0){
                        cand+= remain * 1L * coins[idx][2];
                    }
                }
            }
        }


            //System.out.println("cand is " + cand + " idx is " + idx);


            ans = Math.max(ans , cand);
        }
        return ans;
        
    }
}