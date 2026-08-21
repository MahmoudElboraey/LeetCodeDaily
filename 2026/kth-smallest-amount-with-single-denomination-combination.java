class Solution {

    private boolean isOn(int num , int bit){
        return (num & (1 << bit)) > 0;
    }

    private long f(long md , int [] coins){
        // this method count the number of elements we can produce less than or equal to md
        // using inclusion and exclusion principle 
        long ans = 0;
        int sz = coins.length;
        int loopSize = (1 << sz);
        for (int mask = 1; mask < loopSize ; ++mask){
            int c = 0;
            long lcm = 1;
            for (int bit = 0; bit < sz; ++bit){
                if (isOn(mask , bit)){
                    c++;
                    lcm = lcm(lcm , coins[bit]);
                }
            }
            int sign = (isOn(c , 0) ? 1 : -1);
            ans += sign * md / lcm;

        }

        return ans;
    }

    private long lcm(long a , long b){
        return a / gcd(a , b) * b;
    }

    private long gcd(long a , long b){
        while (b != 0){
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
    public long findKthSmallest(int[] coins, int k) {


        long L = 1; long R = (long)1e15;
        long ans = 1;
        while(L <= R){
            long md = L + (R - L) / 2;
            long count = f(md , coins);
            if (count < k){
                L = md + 1;
            }else if (count > k) {
                R = md -1;
            }else {
                ans = md;
                R = md -1;
            }
        }
        return ans;
        
    }
}