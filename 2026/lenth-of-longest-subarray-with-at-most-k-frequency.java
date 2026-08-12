class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer , Integer > mp = new HashMap<>();

        int ans = 1;
        int L = 0;
        int n = nums.length;
        for (int R = 0; R < n; R++){
            int curElement = nums[R];
            mp.put(curElement, mp.getOrDefault(curElement , 0) + 1);

            while(mp.get(curElement) > k){
                int oth = nums[L];
                mp.put(oth , mp.get(oth) - 1);

                ++L;
            }

            ans = Math.max(ans , R - L + 1);
        }
        return ans;
        
    }
}