class Solution {
    
    private int [] last = new int[3];

    public int numberOfSubstrings(String s) {
        int n = s.length();
        Arrays.fill(last , n);
        int ans = 0;
        for (int i = n-1; i >= 0; --i){
            last[s.charAt(i)-'a'] = i;
            int mx = 0;
            for (int c = 0; c < 3; ++c){
                mx = Math.max(mx , last[c]);
            }
            ans+= n - mx;
        }
        return ans;
        
    }
}