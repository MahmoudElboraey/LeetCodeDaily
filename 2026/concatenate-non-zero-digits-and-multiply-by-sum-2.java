class Solution {

    private int getSum(int L , int R , int [] preSum){
        return preSum[R] - preSum[L];
    }

    private final int mod = (int)1e9+7;

    private int mul (int a, int  b){
        return (int) ((1L * (a % mod) * (b % mod)) % mod);
    }

    private int add (int a, int b){
        return ((a % mod) + (b % mod) ) % mod;
    }

    private int sub (int a, int b){
        return ((a % mod) - (b % mod) + mod) % mod;
    }

    public int[] sumAndMultiply(String s, int[][] queries) {

        int n = s.length();
        int [] prefSum = new int[n+5];
        int [] pow10 = new int[n+5];
        int [] x = new int[n+5];
        int [] nonZeros = new int[n+5];
        pow10[0] = 1;
        for (int i = 0; i < n; i++){
            int d = s.charAt(i) -'0';
            prefSum[i+1] = d + prefSum[i];
            pow10[i+1] = mul(pow10[i] , 10);
            nonZeros[i+1] = nonZeros[i] + (d == 0 ? 0 : 1);
            x[i+1] = d > 0 ? add(mul(x[i] , 10) , d) : x[i]; 
        }


        int q = queries.length;
        int [] answer = new int[q];
        for (int i = 0; i < q; i++){
            int L = queries[i][0];
            int R = queries[i][1] + 1;
            int cnt = nonZeros[R] - nonZeros[L];
            int sum = getSum(L , R , prefSum);
            int valueOfX = sub(x[R] , mul(x[L] , pow10[cnt]));
            answer[i] = mul(valueOfX , sum);
        }

        return answer;

        
    }
}