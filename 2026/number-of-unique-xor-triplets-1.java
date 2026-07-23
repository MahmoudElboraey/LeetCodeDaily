class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int len = nums.length;
        if (len <= 2) return len;
        int ans = 1;
        while(ans <= len){
            ans<<=1;
        }
        return ans;
        
    }
}