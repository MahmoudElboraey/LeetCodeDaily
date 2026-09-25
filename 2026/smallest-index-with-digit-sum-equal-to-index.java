class Solution {
    static private int sumOfDigits(int x){
        int ans = 0;
        while (x > 0){
            ans+= x % 10;
            x/=10;
        }
        return ans;
    }
    public int smallestIndex(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ++i){
            if (i == sumOfDigits(nums[i])) return i;
        }
        return -1;
        
    }
}