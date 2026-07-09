class Solution {
    class DSU {
        int n ;
        int [] par , sz;
        DSU(int _n){
            this.n= _n+5;
            par = new int[n];
            sz = new int[n];
            for (int i = 0; i < n; i++){
                sz[i] = 1;
                par[i] = i;

            }
        }

        private int findP(int u){
            if (u == par[u]) return u;
            return par[u] = findP(par[u]);
        }

        private int work(int u , int v){
            u = findP(u); v = findP(v);
            if (u == v) return 0;
            int temp = u;
            if (sz[u] < sz[v]){
                u = v;
                v = temp;
            }

            par[v] = u;
            sz[u]+=sz[v];
            return 1;
            }
        }
    
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        DSU dsu = new DSU(n);
        int q = queries.length;
        boolean [] answer = new boolean[q];
        for (int i = 1; i < n; i++){
            if (nums[i]-nums[i-1] <= maxDiff){
                dsu.work(i , i-1);
            }
        }

        for (int i = 0; i < q; i++){
            int u = queries[i][0];
            int v = queries[i][1];
            answer[i] = (dsu.findP(u) == dsu.findP(v));
        }
        return answer;
    }
}