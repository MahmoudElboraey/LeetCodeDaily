 class Solution {

    private boolean can(long t){
        for (int x : new int []{2 ,3 , 5 , 7}){
            while (t % x == 0){
                t/=x;
            }
        }
        return t == 1;
    }

    private long gcd(long a , long b){
        if (b < a){
            long temp = b;
            b = a;
            a = temp;
        }
        while (b != 0){
            long newR = a % b;
            a = b;
            b = newR;
        }
        return a; 
    }

    private long consume(long r , int d) {
        return r / gcd(r , d);
    }

    private int minDigits(long r){
        int c = 0;
        int d = 0;
        int a = 0;
        int b = 0;
        while(r % 2 == 0) {
            r/=2; a++;
        }
        while(r % 3 == 0){
            b++; r/=3;
        }
        while(r % 5 == 0){
            c++; 
            r/=5;
        }
        while(r % 7 == 0){
            d++;
            r/=7;
        }
        return c + d + f23(a , b);

    }
    private static final int [][] digits = {
        {1 , 0} , // 2
        {0 , 1} , // 3
        {2 , 0} , // 4
        {1 , 1} , // 6
        {3 , 0} , // 8
        {0 , 2} , // 9
    };
    private int f23(int a, int b ){
        if (a <= 0 && b <= 0) return 0;
        if (memo[a][b] != -1) return memo[a][b];
        int best = Integer.MAX_VALUE;
        for (int [] dig : digits){
            int na = Math.max(a - dig[0] , 0);
            int nb = Math.max(b -dig[1] , 0);
            if (a ==na && b == nb) continue;
            best = Math.min(best , f23(na , nb));
        }
        return memo[a][b] = 1 + best;
    }
    private int [][] memo;

    private void fill(long nr , int k , StringBuilder ans , int n){
        for (int i = k; i < n; i++){
            for (int d = 1; d <10; ++d){
                long cur = consume(nr , d);
                if (minDigits(cur) <= n - i -1){
                    ans.append(d);
                    nr = cur;
                    break;
                }
            }
        }
    }

    public String smallestNumber(String num, long t) {
        if (!can(t)){
            return "-1";
        }

        memo = new int[50][50];
        for (int [] row : memo) Arrays.fill(row , -1);
        int n = num.length();

        long [] rem = new long[n+1];
        rem[0] = t;
        int firstZero = n;
        for (int i = 0; i < n;++i){
            int d = num.charAt(i) -'0';
            if (d == 0){
                firstZero = i;
                break;
            }
            rem[i+1] = consume(rem[i] , d);
        }
        if (firstZero == n && rem[n] == 1) return num;



        // try to keep the prefix with length L
        for (int i = Math.min(firstZero , n-1); i >= 0; i--){
            for (int d = (num.charAt(i)-'0') + 1; d < 10; ++d) {
                long nr = consume(rem[i] , d);
                if (minDigits(nr) <= n-1-i){
                    StringBuilder sb = new StringBuilder(num.substring(0 , i));
                    sb.append(d);
                    fill(nr , i+1 , sb , n);
                    return sb.toString();
                }
            }
        }

        int newLen = Math.max(n+1 , minDigits(t));
        StringBuilder answer = new StringBuilder();
        fill(t , 0 , answer , newLen);
        return answer.toString();
    }
}
