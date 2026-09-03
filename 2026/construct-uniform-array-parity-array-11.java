class Solution {
    public boolean uniformArray(int[] nums1) {
        int freq [] = new int[2];
        int n = nums1.length;
        int minIndex = 0;
        for (int i = 0; i < n; ++i){
            freq[nums1[i] % 2] ++;
            if (nums1[minIndex] > nums1[i]) minIndex = i;
        }

        int smallest = nums1[minIndex];
        return smallest % 2 == 1 || freq[0] == n ? true : false;
    }
}