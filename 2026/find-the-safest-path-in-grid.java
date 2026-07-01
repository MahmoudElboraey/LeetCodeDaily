class Solution {
    private int n;
    private int [][] dist;
    private  int [][] vis;
    private int [] dx = {0 , 0 , -1 , 1};
    private int [] dy = {-1 , 1 , 0 , 0};

    private boolean valid (int x , int y){
        return x >= 0 && y >=0 && x < n && y < n;
    }

    private void bfs(Deque<Pair <Integer , Integer > > q ){
        while(!q.isEmpty()){
            Pair <Integer , Integer > node = q.removeFirst();
            for (int d = 0; d < 4; ++d){
                int nr = node.getKey() + dx[d];
                int nc = node.getValue() + dy[d];
                int di = dist[node.getKey()][node.getValue()];
                if (valid(nr , nc) && dist[nr][nc] > di + 1){
                    dist[nr][nc] = di +1 ;
                    q.addLast(new Pair(nr , nc));
                }
            }

        }

    }
    
    private void init(){
        for (int i = 0; i < n; ++i){
            for (int j = 0; j < n; ++j){
                vis[i][j] = n * n + 5;
            }
        }
    }

    private boolean solve(int md){
        init();
        Deque<Pair<Integer, Integer> > q = new ArrayDeque<>();
        if (dist[0][0] >= md){
            q.addLast(new Pair(0 , 0));
            vis[0][0] = 0;
        }
        while(!q.isEmpty()){
            Pair <Integer , Integer > node = q.removeFirst();
            for (int d = 0; d < 4; ++d){
                int nr = node.getKey() + dx[d];
                int nc = node.getValue() + dy[d];
                int di = vis[node.getKey()][node.getValue()];
                if (valid(nr , nc) && vis[nr][nc] > di + 1 && dist[nr][nc] >= md){
                    vis[nr][nc] = 1 + di;
                    q.addLast(new Pair(nr , nc));
                }
            }

        }

        return vis[n-1][n-1] != n *n + 5;

    }

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        n = grid.size();
        Deque<Pair<Integer, Integer> > q = new ArrayDeque<>();
        dist = new int [n][n];
        vis = new int[n][n];
        for (int i = 0; i < n; ++i){
            for (int j = 0; j < n; ++j){
                int v = grid.get(i).get(j);
                dist[i][j] = n * n + 5;
                if (v == 1) {
                    q.addLast(new Pair(i , j));
                    dist[i][j] = 0;
                }
                
            }
        }
        bfs(q);
        int ans = 0;
        int L = 0 ; int R = n * n;
        while(L <= R){
            int md = (L + R) / 2;
            if (solve(md)) L = (ans = md) + 1;
            else R = md -1;
        }

        return ans;
    }
}