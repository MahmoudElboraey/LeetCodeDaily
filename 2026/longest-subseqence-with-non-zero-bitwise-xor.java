class Solution {
    public int longestSubsequence(int[] nums) {
        int n = nums.length;
        int xor = 0;
        for (int x : nums) xor^= x;
        int zeros = (int) Arrays.stream(nums).filter(x -> x == 0).count();
        if (zeros == n) return 0;
        return xor == 0 ? n-1 : n;
        
    }
}