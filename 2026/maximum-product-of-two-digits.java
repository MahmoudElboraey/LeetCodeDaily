class Solution {
    public int maxProduct(int n) {
        int maxDigit = 0;
        int secondMaxDigit = 0;
        while (n > 0){
            int d = n % 10;
            if (d >= secondMaxDigit){
                secondMaxDigit = d;
                if (maxDigit < d){
                    int temp = maxDigit;
                    maxDigit = d;
                    secondMaxDigit = temp;
                }
            }
            n/=10;
        }
        return maxDigit * secondMaxDigit;
        
    }
}