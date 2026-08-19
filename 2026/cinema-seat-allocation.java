class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        int sz = reservedSeats.length;
        int ans = 0;
        Arrays.sort(reservedSeats , (a , b) -> Integer.compare(a[0] , b[0]));
        int rows = 0;
        int i = 0;
        while(i < sz){
            int j = i;
            int [] curRow = new int[11];
            while(j < sz && reservedSeats[i][0] == reservedSeats[j][0]) {
                int pos = reservedSeats[j][1];
                curRow[pos] = 1;
                ++j;
            }
            for (int k =1; k <= 10; ++k){
                curRow[k] += curRow[k-1];
            }

            int g1 = curRow[5] - curRow[1];
            int g2 = curRow[7] - curRow[3];
            int g3 = curRow[9] - curRow[5];
            if (g1 == 0 && g3 == 0) ans+= 2;
            else if (g1 == 0 || g2 == 0 || g3 == 0) ans++;
          //  System.out.println(rows + " dfsarrfd " + g1 + " df " + g2 + " gd " + g3);

            i = j;
            rows++;
        }
       // System.out.println(ans  + "   " + rows);
        ans+= (n - rows) * 2;
        return ans;
        
    }
}