class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int n = s.length();
        int zeros = (int) s.chars().filter(c -> c == '0').count();
        if (zeros == n) return 0;
         
        ArrayList<int [] > blocks = new ArrayList<>();
        // char , size;
        int size = 1;
        char c = s.charAt(0);
        int ans = n - zeros;
        for (int i = 1; i < n; ++i){
            if (c == s.charAt(i)){
                size++;
            }else {
                blocks.add(new int[]{c-'0' , size});
                size = 1;
                c = (c == '0' ? '1' : '0');
            }
        }
        blocks.add(new int []{c-'0' , size});

        int len = blocks.size();
        System.out.println(ans);
        for (int i = 0; i < len; i++){
            int ch = blocks.get(i)[0];
            int count = blocks.get(i)[1];
            if (ch == 1 ){
                int candidate = n - zeros;
                if (i > 0 && i+1 < len){
                    candidate+= blocks.get(i-1)[1]; // get the left zeros
                    candidate+= blocks.get(i+1)[1]; // get the right zeros
                    ans = Math.max(ans ,  candidate);
                }
            }
        }
        return ans;


        
    }
}