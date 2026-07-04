class Solution {

    private ArrayList<int []> [] g;

    private int [][] dist;
    private int nodes;

    private void buildGraph(int [][] roads){
        int n = roads.length;
        for (int i = 0; i < n; i++){
            int u = roads[i][0];
            int v = roads[i][1];
            int w = roads[i][2];
            g[u].add(new int []{v , w});
            g[v].add(new int[]{u, w});
        }
        
    }

    private void dij(int node){
        int idx = (node ==1 ? 0 : 1);
        PriorityQueue<int [] > q = new PriorityQueue<>((x , y) -> Integer.compare(x[1] , y[1]) );
        q.add(new int []{node , Integer.MAX_VALUE});
        while(!q.isEmpty()){
            int [] current = q.poll();
            int curNode = current[0];
            int curW = current[1];
            if (curW > dist[idx][curNode]) continue;
            for (int [] edge :  g[curNode]){
                int newNode = edge[0];
                int newW = edge[1] ;
                if (dist[idx][newNode] > newW){
                    dist[idx][newNode] = newW;
                    q.add(new int []{newNode , newW});
                }
            }
        }
        
    }
    public int minScore(int n, int[][] roads) {
        g = new ArrayList[n+1];
        for (int i = 1; i <= n; ++i){
            g[i] = new ArrayList<>();
        }
        dist = new int[2][n+1];
        for (int i = 0; i < 2; ++i){
            for (int j = 1; j <= n; ++j){
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        nodes = n;
        buildGraph(roads);
        dij(1);
        dij(n);
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= n; ++i){
            System.out.println(dist[0][i] + " shit is " + dist[1][i]);
            ans = Math.min(ans , Math.min(dist[0][i] , dist[1][i]));
        }
        return ans;

        
    }
}