class Solution {

    private int gcd(int a , int b){
        if (a < b){
            int temp = b;
            b = a;
            a = temp;
        }
        while (b != 0){
            int newRemainder = a % b;
            a = b;
            b = newRemainder;
        }
        return a;
    }
    public int findGCD(int[] nums) {
        int max = Arrays.stream(nums).max().orElseThrow();
        int min = Arrays.stream(nums).min().orElseThrow();
        
        return gcd(max , min);
        
    }
}