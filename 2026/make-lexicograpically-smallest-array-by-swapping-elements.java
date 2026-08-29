class Solution {

    private class DSU {
        private int [] parent;
        private int n;

        public DSU(int n){
            this.n = n;
            parent = IntStream.range(0 , n).toArray();
        }

        public int findParent(int p){
            if (parent[p] == p) return p;
            return parent[p] = findParent(parent[p]);
        }

        public void union(int u , int v){
            u = findParent(u);
            v = findParent(v);
            if (u == v) return;
            parent[u] = v;
        }
    }
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        DSU dsu = new DSU(n);
        Integer [] ids = IntStream.range(0 , n).boxed().toArray(Integer[]::new);
        Arrays.sort(ids , (x , y) -> Integer.compare(nums[x] , nums[y]));
        int last = -1;
        for (int x : ids){
            int value = nums[x];
            if (last != -1 && value - nums[last] <= limit){
                dsu.union(x , last);
            }
            last = x;
        }

        int [] answer = new int[n];
        Map<Integer , ArrayList<Integer> > mp = new HashMap<>();
        for (int id = 0; id < n; ++id){
            mp.computeIfAbsent(dsu.findParent(id) , k-> new ArrayList<Integer>()).add(id);
        }
        for (Map.Entry<Integer , ArrayList<Integer> > entry : mp.entrySet()){
            ArrayList<Integer>values = entry.getValue();
            Collections.sort(values);
            ArrayList<Integer> numValues = new ArrayList<>();
            for (int x : values) numValues.add(nums[x]);
            int sz = numValues.size();
            Collections.sort(numValues);
            for (int i = 0; i < sz; ++i){
                answer[values.get(i)] = numValues.get(i);
            }

        }
        return answer;
        
    }
}