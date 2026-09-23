class Solution {

    private int getMinOps(int [] suf , int st , int x){
        int n = suf.length;
        int L = st; int R = n - 1;
        
        while(L <= R){
            int md = L + (R - L) / 2;
            if (suf[md] > x){
                L = md + 1;
            }else if (suf[md] < x){
                R = md -1;
            }else {
                assert(suf[md] == x);
                return n - md;
            }
        }
        return -1;
    }

    public int minOperations(int[] nums, int x) {
        int n = nums.length;
        
        int ans = Integer.MAX_VALUE;
        int [] suf = new int[n];
        suf[n-1] = nums[n-1];
        for (int i = n-2; i >= 0; --i) suf[i] = nums[i] + suf[i+1];

        int cand = getMinOps(suf , 0 , x);
        if (cand != -1) ans = Math.min(ans , cand);
        for (int i = 0; i < n; ++i){
 
                x-= nums[i];
                if (x == 0) ans = Math.min(ans , i + 1);
                int can = getMinOps(suf , i+1 , x);
                if (can != -1) ans = Math.min(ans , can + i + 1);
            
        }
        return ans > n ? -1 : ans;
        
    }
}