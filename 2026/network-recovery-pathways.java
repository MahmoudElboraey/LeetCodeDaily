class Solution {

    private ArrayList<long [] > g [];


    private boolean solve(int md , int n , long k , boolean [] mnwr){
        PriorityQueue<long [] > q = new PriorityQueue<>((x , y) -> Long.compare(x[1] , y[1]));

        long [] dist = new long [n];
        Arrays.fill(dist , (long)1e18);
        dist[0] = 0;
        q.add(new long []{0 , 0});

        while(!q.isEmpty()){
            long [] cur = q.poll();
            int node = (int)cur[0]; long w = cur[1];
            
            if (!mnwr[node] || w > dist[node]) continue;

            for (long [] e : g[node]){
                long newCost = w + e[1];
                if (e[1] < md) continue;
                int newNode = (int)e[0];
                if (dist[newNode] > newCost){
                    dist[newNode] = newCost;
                    q.add(new long []{newNode , newCost});
                }
            }
        }
        System.out.println(dist[n-1]);
        return dist[n-1] <= k;


    }
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        g = new ArrayList[n];
        for (int i = 0; i < n; ++i){
            g[i] = new ArrayList<>();
        }

        for (int [] e : edges){
            g[e[0]].add(new long[]{e[1] , e[2]});
        }

  
        int L = 0 ; int R = (int)1e9;
        int ans = -1;
        while(L <= R){
            int md = L + (R -L) / 2;
            if (solve(md , n , k , online)){
                ans = md;
                L = md + 1;
            }else {
                R = md -1;
            }
        }
        return ans;
        
    }
}