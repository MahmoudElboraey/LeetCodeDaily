class Solution {
    private boolean solve(int x , int t){
        int prod = 1;
        while(x > 0){
            prod*= x % 10;
            x/=10;
            prod%=t;
        }
        return prod == 0;
    }
    public int smallestNumber(int n, int t) {
        int start = n;
        while (!solve(start , t)){
            ++start;
        }
        return start;
    }
}