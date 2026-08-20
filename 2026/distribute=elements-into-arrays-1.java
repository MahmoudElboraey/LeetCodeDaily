class Solution {
    public int[] resultArray(int[] nums) {
        int n = nums.length;
        int [] result = new int[n];
        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();
        arr1.add(nums[0]);
        arr2.add(nums[1]);
        int i = 2;
        while(i < n){
            if (arr1.getLast() > arr2.getLast()){
                arr1.add(nums[i]);
            }else {
                arr2.add(nums[i]);
            }
            i++;
        }
        i = 0;
        for (int x : arr1) result[i++] = x;
        for (int x : arr2) result[i++] = x;
        return result;
    }
}