class Solution {
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = nums[n-1] * nums[n-2] * nums[n-3];
        ans = Math.max(ans , nums[0] * nums[1] * nums[2]);
        ans = Math.max(ans , nums[n-1] * nums[n-2] * nums[0]);
        ans = Math.max(ans , nums[0] * nums[1] * nums[n-1]);
        return ans;

    }
}