class Solution {

    private List<String> out;
    private String num;
    private int target;
    private int n;

    public List<String> addOperators(String num, int target) {
        out = new ArrayList<>();
        this.num = num;
        this.target = target;
        this.n = num.length();
        solve(0 , new StringBuilder() , 0L , 0L);
        return out;
    }

    private void solve(int i , StringBuilder ans , long value , long prev){
        if (i == n){
            if (value == target) out.add(ans.toString());
            return ;
        }

        int len = ans.length();
        for (int end = i; end < n; ++end){
            if (end > i && num.charAt(i) == '0') break; // to prevent case like this 02
            long v = Long.parseLong(num.substring(i , end+1));

            if (i == 0){
                ans.append(v);
                solve(end+1 , ans , v , v);
                ans.setLength(len);

            }else {
                // + 
                ans.append("+").append(v);
                solve(end+1 , ans ,  value + v , v);
                ans.setLength(len);


                // -
                ans.append("-").append(v);
                solve(end+1 , ans , value -v , -v);
                ans.setLength(len);



                // 
                ans.append("*").append(v);
                solve(end+1 , ans , - prev + value + prev * v , prev * v);
                ans.setLength(len);

            }
        }

    }
}