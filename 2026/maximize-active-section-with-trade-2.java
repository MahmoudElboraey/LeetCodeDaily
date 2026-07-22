class Solution {

    class SegmentTree {
        private int n;
        private int [] tree;
        public SegmentTree(int _n){
            n = 1;
            _n= _n+5;
            while(n <= _n) n*=2;
            tree = new int[2 * n];
        }

        private void upd(int node, int nl , int nr ,  int p , int v ){
            if (nl+1 == nr){
                tree[node] = v;
                return;
            }

            int mid = (nl + nr) / 2;
            if (p < mid){
                upd(2 * node + 1 , nl , mid , p , v);
            }else {
                upd (2 * node + 2 , mid , nr , p , v);
            }
            tree[node] = Math.max(tree[2 * node + 1] , tree[2 * node + 2]);
        }

        public void upd(int p , int v){
            upd(0 , 0 , n , p , v);
        }

        private int query(int node , int nl , int nr , int ql , int qr){
            if (nl >= ql && qr >= nr) return tree[node];
            int mid = (nl + nr) / 2;

            if (qr <= mid) return query (2 * node + 1 , nl , mid , ql , qr);
            if (ql >= mid) return query(2 * node + 2 , mid , nr , ql , qr);

            return Math.max(query(2 * node + 1 , nl , mid , ql , qr) , query(2 * node + 2 , mid , nr , ql , qr));
        }

        public int query(int l , int r){
            return query(0 , 0 , n , l , r+1);
        }
    }

    private int findFirst(ArrayList<int [] > zeros , int qL , int qR){
        int ans = -1;
        int L = 0; int R = zeros.size() -1;
        while(L <= R){
            int mid = (L + R) / 2;
            int value = zeros.get(mid)[1];
            if (value >= qL){
                ans = mid;
                R = mid -1;
            }else {
                L = mid + 1;
            }
        }
        return ans;

    }
    
    private int findLast (ArrayList<int [] > zeros , int qL , int qR){

        int ans = -1;
        int L = 0; int R = zeros.size() -1;
        while (L <= R){
            int mid = (L + R) / 2;
            int value = zeros.get(mid)[0];
            if (value <= qR){
                ans = mid;
                L = mid + 1;
            }else {
                R = mid -1;
            }
        }
        return ans;
    }
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        int q = queries.length;
        ArrayList<int [] > zeros = new ArrayList<>();
        int ones = 0;
        for (int i = 0; i < n; ){
            if (s.charAt(i) == '1'){
                ones++;
                i++;
                continue;
            }
            int j = i;
            while (j < n && s.charAt(j) == '0') ++j;
            zeros.add(new int[]{i , j-1 , j - i});
            i = j;
        }
        int sz = zeros.size();

        SegmentTree tree = new SegmentTree(sz);
        for (int i = 0; i +1 < sz; ++i){
            tree.upd(i , zeros.get(i)[2] + zeros.get(i+1)[2]);
        }

        List<Integer> answer = new ArrayList<>();

        for (int i = 0; i < q; ++i){
            int ql = queries[i][0];
            int qr = queries[i][1];
            int lo = findFirst(zeros , ql , qr);
            int hi = findLast(zeros , ql , qr);
            if (lo == -1 || hi == -1 || lo > hi){
                answer.add(ones);
                continue;
            }
            assert(lo <= hi);
            int cnt = hi - lo + 1;
            System.out.println("sex " + lo + " a7a " + hi);
            if (cnt == 1){
                answer.add(ones);
                continue;
            }
            int firstBlock = Math.min(qr , zeros.get(lo)[1] ) - Math.max(ql , zeros.get(lo)[0]) + 1;
            int lastBlock = Math.min(qr , zeros.get(hi)[1] ) - Math.max(ql , zeros.get(hi)[0] )+ 1;
            if (cnt == 2){
                answer.add(ones + firstBlock + lastBlock);
                continue;
            }
            int cand =  firstBlock + zeros.get(lo+1)[2];
            cand = Math.max(cand ,  lastBlock + zeros.get(hi-1)[2]);
            if (lo+1 <= hi-2){
                cand = Math.max(cand , tree.query(lo+1 , hi-2));
            }

            answer.add(cand + ones);
        }
        return answer;

        
    }
}