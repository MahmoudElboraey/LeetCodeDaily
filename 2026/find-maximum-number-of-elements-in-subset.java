class Solution {
    public int maximumLength(int[] nums) {
        int ans = 1;
        HashMap<Integer , Integer > mp = new HashMap<>();
        for (int x : nums){
            mp.put(x , 1 + mp.getOrDefault(x , 0));
        }
        int ones = mp.getOrDefault(1 , 0);
        ans = Math.max(ans , ones - (ones % 2 == 0 ?  1: 0) );

        int limit = (int)1e9;
        for (int x : nums){
            if (x == 1) continue;
            outer:
            for (int k = 2; k <= 30; ++k){ 
                int count = 0;
                for (long st = x ; st <= limit ; st *= st){
                    ++count;
                   /// System.out.println(" st is " + st + " and k is " + k + " and count is " + count);
                    if (count == k){
                        if (mp.getOrDefault((int)st , 0) < 1){
                            break outer;
                        }
                        ans = Math.max(ans , 2 * k -1);
                        break;
                    }else {
                      //  System.out.println("shit is " + mp.getOrDefault((int)st , 0) + " and st is " + st);
                        if (mp.getOrDefault((int)st , 0) < 2){
                            break outer;
                        }
                    }
                }
            }
            
        }
        return ans;
       
        
        
    }
}