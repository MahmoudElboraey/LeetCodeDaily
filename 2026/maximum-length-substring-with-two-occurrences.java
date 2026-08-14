class Solution {

    private boolean valid(String s){
        int [] f = new int[26];
        for (char c : s.toCharArray()){
            if (++f[c-'a'] > 2){
                return false;
            }
        }
        return true;
    }
    public int maximumLengthSubstring(String s) {
        int n = s.length();
        int ans = 0;
        outer: for (int sz = n; sz > 0; sz--){
            for (int i = 0; i + sz <= n; ++i){
                String current = s.substring(i , i+ sz);

                if (valid(current)){
                    ans = sz;
                    break outer;
                }

            }
        }
        return ans;
    }
}