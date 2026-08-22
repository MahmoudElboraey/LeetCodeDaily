class Solution {
    private int sumOfDigits(int n){
        int ans = 0;
        while (n > 0){
            ans += n % 10;
            n/=10;
        }
        return ans;
    }

    private int digitsProduct(int n){
        int ans = 1;
        while (n > 0){
            ans *= ( n % 10);
            n/=10;
        }
        return ans;
    }
    public boolean checkDivisibility(int n) {
        return n % (sumOfDigits(n) + digitsProduct(n)) == 0;
        
    }
}