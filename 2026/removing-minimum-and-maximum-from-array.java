class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        if (n == 1) return n;
        int minValue = Integer.MAX_VALUE;
        int minIndex = -1;
        int maxValue = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int i = 0; i < n; ++i){
            int value = nums[i];
            if (value > maxValue){
                maxValue = value;
                maxIndex = i;
            }

            if (value < minValue){
                minValue = value;
                minIndex = i;
            }
        }

        int left = Math.min(minIndex , maxIndex);
        int right = Math.max(minIndex , maxIndex);

        int ans = right + 1;
        ans = Math.min(ans , n - left);
        ans = Math.min(ans , n - (right - left - 1));
        return ans;
        
    }
}