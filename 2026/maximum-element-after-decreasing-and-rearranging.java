class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);
        int ans = 0;
        for (int x : arr){
            if (x > ans){
                ans++;
            }
        }
        return ans;
        
    }
}