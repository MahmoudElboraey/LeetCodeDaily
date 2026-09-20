class Solution {
    public int reverseDegree(String s) {
        int ans = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i){
            ans+= (i+1) * (26-(s.charAt(i)-'a'));
        }
        return ans;
        
    }
}