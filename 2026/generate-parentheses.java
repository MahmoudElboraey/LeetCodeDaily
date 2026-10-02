class Solution {

    private List<String> ans;
    private StringBuilder cur;
    private int n;

    private void solve(int i , int sum){
        if (i == n){
            if (sum == 0){
                ans.add(cur.toString());
            }
            return;
        }

        // add (

        int sz = cur.length();
        cur.append("(");
        solve(i+1 , sum+1);
        cur.setLength(sz);


        // add ) 
        if (sum -1 >= 0){
            cur.append(")");
            solve(i+1 , sum-1);
            cur.setLength(sz);
        }



    }
    public List<String> generateParenthesis(int n) {
        ans = new ArrayList<>();
        cur = new StringBuilder();
        this.n = 2 * n;
        solve(0 , 0);
        return ans;
    }
}