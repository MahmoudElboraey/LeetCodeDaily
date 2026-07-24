class Solution {
    public int uniqueXorTriplets(int[] nums) {
       
        Set<Integer> set = new HashSet<>();


        for (int a : nums){
            for (int b : nums){
                set.add(a ^ b);
            }
        }

        int ans = 0;
        int p2 = 32 - Integer.numberOfLeadingZeros(1500);
        p2 = (1 << p2);

        for (int v = 0; v <= p2; v++){
            for (int x : nums){
                int other = x ^ v;
                if (set.contains(other)){
                    ans++;
                    break;
                }
            }
        }
        return ans;
        
    }
}