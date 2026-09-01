class Solution {

    private int sz , n , m , fullE;

    private boolean valid(int x, int y){
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    private static int max = (int)1e6;
  
    private int [][][][] dp; // r , c , e , mask

    private int [][] marked;
    // i need to calcaulate the minimum number of moves is equivlant to the

    private int bfs (int sr , int sc , String [] grid){
        int n = grid.length;
        int m = grid[0].length();
        ArrayDeque<int [] > q = new ArrayDeque<>();

        q.add (new int []{sr , sc , fullE , 0});

        dp[sr][sc][fullE][0] = 0;

        int [] dx = {1 , 0 , -1 , 0};
        int [] dy = {0 , 1 , 0 , -1};


        while (!q.isEmpty()){
            int [] current = q.poll(); // r , c , e , mask 
            sr = current[0];
            sc = current[1];
            int mask = current[3];
            int e = current[2];
            if (mask == (1 << sz)-1) return dp[sr][sc][e][mask];

            for (int d = 0; d < 4; ++d){
                int nx = sr + dx[d];
                int ny = sc + dy[d];
                if (!valid(nx , ny)|| grid[nx].charAt(ny) == 'X') continue;

                int newMask = marked[nx][ny] >= 0 ? mask | (1 << marked[nx][ny]) : mask;
                int newE = (grid[nx].charAt(ny) == 'R')? fullE : e -1;

                if (e > 0 && 
                    dp[nx][ny][newE][newMask] > dp[sr][sc][e][mask] + 1){
                
                    dp[nx][ny][newE][newMask] = dp[sr][sc][e][mask] + 1;

                    q.add (new int []{nx , ny , newE , newMask});
                }
            }

        }
        return -1;
    }

    private void init(){
        int mask = (1 << sz);
        dp = new int[n][m][fullE+1][mask];
        for (int i = 0; i < n; ++i){
            for (int j = 0; j < m ; ++j){
                for (int k = 0; k <= fullE ; ++k){
                    for (int m = 0; m < mask ; ++m){
                        dp[i][j][k][m] = max;
                    }
                }
                
            }
        }

    }



    public int minMoves(String[] classroom, int energy) {
        n = classroom.length;
        m = classroom[0].length();
        marked = new int[n][m];
        fullE = energy;
        sz = 0;

        int rr = -1;
        int cc = -1;
        for (int i = 0; i < n; ++i){
            for (int j = 0; j < m; ++j){
                char c = classroom[i].charAt(j);
                marked[i][j] = -1;
                if (c == 'L'){
                    marked[i][j] = sz++;
                }
                if (c == 'S'){
                    rr = i;
                    cc = j;
                }


            }
        }
        
        init();

        int ans = bfs(rr , cc , classroom);
        return ans >= max ? -1 : ans;


        
    }
}



// class Solution {


//     private int [][][][] dist;
//     private boolean valid(int x, int y , int n , int m){
//         return x >= 0 && x < n && y >= 0 && y < m;
//     }

//     private ArrayList<int [] > cells;
//     private ArrayList<int []> rcells;

//     private static int max = (int)1e6;

//     private int [][][][] dp;

//     private int fullE;

//     private void bfs (int sr , int sc , String [] grid){
//         int n = grid.length;
//         int m = grid[0].length();
//         ArrayDeque<int [] > q = new ArrayDeque<>();
//         q.add (new int []{sr , sc});
//         dist[sr][sc][sr][sc] = 0;
//         int [] dx = {1 , 0 , -1 , 0};
//         int [] dy = {0 , 1 , 0 , -1};
//         while (!q.isEmpty()){
//             int [] current = q.poll();
//             for (int d = 0; d < 4; ++d){
//                 int nx = current[0] + dx[d];
//                 int ny = current[1] + dy[d];
//                 if (valid(nx , ny , n , m ) && dist[sr][sc][nx][ny]
//                 > dist[sr][sc][current[0]][current[1]] + 1 && grid[nx].charAt(ny) != 'X'){
//                     dist[sr][sc][nx][ny] =dist[sr][sc][current[0]][current[1]] + 1;
//                     q.add (new int []{nx , ny});
//                 }
//             }

//         }
//     }

//     private boolean on(int bit , int mask){
//         return ((1 << bit) & mask) != 0;
//     }




//     private int solve(int curMask , int e ,  int sr , int sc, int sz){
//         if (curMask == (1 << sz) -1) return 0;

//         if (dp[sr][sc][e][curMask] != -1) return dp[sr][sc][e][curMask];
//         if (e == 0){
//             return dp[sr][sc][e][curMask]= max;
//         }

//         int best = max;

//         for (int i = 0; i < sz; ++i){
//             if (on(i , curMask)) continue;
//             int [] current = cells.get(i);
//             int nr = current[0];
//             int nc = current[1];

//             int rE = dist[sr][sc][nr][nc];
//             // move directly to the L cell
//             if (rE <= e){
//                 int ch1 = rE + solve(curMask | (1 << i) , e - rE , nr , nc , sz);
//                 best = Math.min(best , ch1);
//             }

//             // move to any of reset cells and them from reset cells to current cell

//             for (int [] reset : rcells){
//                 int r = reset[0];
//                 int c = reset[1];
//                 rE = dist[sr][sc][r][c]; 
//                 if (rE <= e){
//                     int ch = rE + solve(curMask , fullE , r , c , sz);
//                     best = Math.min(best , ch);
//                 }
//             }

//         }

//         return dp[sr][sc][e][curMask] = best;

//     }



//     public int minMoves(String[] classroom, int energy) {
//         int rows = classroom.length;
//         int cols = classroom[0].length();
//         fullE = energy;

       
//         dist = new int[rows ][cols][rows][cols];
//         for (int i = 0; i < rows; ++i){
//             for (int j = 0; j < cols ; ++j){
//                 for (int k = 0; k < rows; ++k){
//                     for (int m = 0; m < cols ; ++m){
//                         dist[i][j][k][m] = max;
//                     }
//                 }
//             }
//         }

//         cells = new ArrayList<>();
//         rcells = new ArrayList<>();
//         int rr = -1;
//         int cc = -1;
//         for (int i = 0; i < rows; ++i){
//             for (int j = 0; j < cols; ++j){
//                 char c = classroom[i].charAt(j);
//                 if (c == 'X' || c == '.') continue;
//                 bfs(i , j , classroom);
//                 if (c == 'R') rcells.add(new int[]{i , j});
//                 if (c == 'L') cells.add (new int[]{i , j});
//                 if (c == 'S'){
//                     rr = i;
//                     cc = j;
//                 }


//             }
//         }

//         int sz = cells.size();
//         int mask = (1 << sz);
//         dp = new int[rows][cols][51][mask];
//         for (int i = 0; i < rows; ++i){
//             for (int j = 0; j < cols; ++j){
//                 for (int e = 0; e <= 50; ++e){
//                 for (int c = 0; c < mask; ++c){
//                     dp[i][j][e][c] = -1;
//                 }
//                 }
                
//             }
//         }

//         int ans = solve(0 , energy , rr , cc , sz);

//         return ans >= max ? -1 : ans;


        
//     }
// }