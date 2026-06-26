class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int [] freq = new int[n];
        for (int i = 0; i < n; i++){
            if (target == nums[i]) freq[i] = 1;
            freq[i]+= (i > 0 ? freq[i-1] : 0);
        }
        int ans = 0;
        for (int L = 0; L < n; L ++){
            for (int R = L; R < n; ++R){
                int len = (R -L + 1) / 2 +1;
                int have = freq[R] - (L > 0 ? freq[L-1] : 0);
                if (have >= len) {
                    ans++;
                   /// System.out.println("have is " + have + " L and R are " + L + " fd " + R);
                }
            
            }
        }
        return ans;
        
    }
}