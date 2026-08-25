class Solution {
    public int missingMultiple(int[] nums, int k) {
        Set<Integer > set = new HashSet<>();
        for (int x : nums){
            set.add(x);
        }
        int x = 1;
        while(set.contains(x * k)){
            ++x;
        }
        return x * k;
        
    }
}