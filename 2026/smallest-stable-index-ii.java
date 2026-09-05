
class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int [] sufMin = new int[n];
        sufMin[n-1] =  nums[n-1];
        for (int i = n-2; i >= 0; --i){
            sufMin[i] = Math.min(nums[i] , sufMin[i+1]);
        }

        int max = nums[0];
        for (int i = 0; i < n; ++i){
            max = Math.max(max , nums[i]);

            if (max - sufMin[i] <= k) return i;
        }
        return -1;
        
    }
}