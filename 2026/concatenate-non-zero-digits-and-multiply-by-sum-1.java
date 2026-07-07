class Solution {

    private int sumOfDigits(int x){
        int ans = 0;
        while(x > 0){
            ans+= x % 10;
            x/= 10;
        }
        return ans;
    }

    private int formX(int n){
        String s = n + "";
        int ans = 0;
        for (char c : s.toCharArray()){
            if (c == '0') continue;
            ans = ans * 10 + (c - '0');
        }
        return ans;
    }
    public long sumAndMultiply(int n) {
        return 1L * sumOfDigits(n) * formX(n);
        
    }
}