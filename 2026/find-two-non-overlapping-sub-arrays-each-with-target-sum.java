class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        ArrayList<int [] > ranges = new ArrayList<>();
        int R = 0;
        int curSum = 0;
        for (int L = 0; L < n; ){
            while(R < n && curSum < target){
                curSum+= arr[R++];
            }

            if (target == curSum){
                ranges.add(new int[]{L , R-1});
                //System.out.println(L + " dfsf " + R);
            }

            curSum-= arr[L++];
        }

        Collections.sort(ranges , (x , y) -> Integer.compare(x[1] , y[1]));

        int ans = Integer.MAX_VALUE;
        int sz = ranges.size();
        int [] preMin = new int[sz];
        if (sz > 0){
            preMin[0] = ranges.get(0)[1] - ranges.get(0)[0] + 1;
        }
        for (int i = 1; i < sz; ++i){
            int left = ranges.get(i)[0];
            int right = ranges.get(i)[1];

            int lo = 0; 
            int hi = i-1;
            int idx = -1;
            while(lo <= hi){
                int md = (lo + hi) / 2;
                int value = ranges.get(md)[1];
                if (value >= left){
                    hi = md -1;
                }else {
                    idx = md;
                    lo = md + 1;
                }
            }
            //System.out.println(idx);

            if (idx != -1){
                ans = Math.min(ans , preMin[idx] + right - left + 1 );
            }

            preMin[i] = Math.min(preMin[i-1] , right - left + 1);
        }

        return ans == Integer.MAX_VALUE ? -1  : ans;
        
    }
}