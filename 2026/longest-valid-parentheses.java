public class Solution {
    public int longestValidParentheses(String s) {
        int sz = s.length();
        if (sz == 0) return 0;
        int [] stack = new int[sz];
        int [] dp = new int[sz];
        int ptr = -1;
        int ans = 0;
        for (int i = 0; i < sz; ++i){
            char c = s.charAt(i);
            if (c == '('){
                stack[++ptr] = i;
            }else {
                if (ptr == -1) continue;
                int j = stack[ptr];
                dp[i] = i - j + 1 + (j > 0 ? dp[j-1] : 0);
                ans = Math.max(ans , dp[i]);
                --ptr; 
            }
        }
        return ans;
    }
} {
    
}
