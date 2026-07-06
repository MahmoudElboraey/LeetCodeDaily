class Solution {
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals , (x , y) -> {
            if (x[0] == y[0]) return Integer.compare(y[1] , x[1]);
            return Integer.compare(x[0] , y[0]);
        });
        int n = intervals.length;
        int ans = n;
        int L = 0;
        while(L < n){
            int R = L+ 1;
            System.out.println("L is " + L + " and R is " + R);
            while (R< n && intervals[L][1] >= intervals[R][1]){
                ans--;
                ++R;
                
            }
            L = R;
        }
        return ans;
        
    }
}