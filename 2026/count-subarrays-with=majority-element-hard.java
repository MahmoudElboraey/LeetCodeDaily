class Solution {

    private class SegmentTree {
        private int n = 1;
        private int offset;
        private int [] Tree;

        public SegmentTree(int size){
            offset = size;
            size *=2+5;
            while(n < size){
                n*=2;
            }
            Tree = new int [2 * n];
        }

        private void add(int node , int L , int R , int pos , int v){
            if (L+1 == R){
                Tree[node]+=v;
                return;
            }

            int md = (L + R) / 2;
            if (pos < md){
                add(2 * node + 1 , L , md , pos , v);
            }else {
                add (2 * node + 2 , md , R , pos , v);
            }
            Tree[node] = Tree[2 * node + 1] + Tree[2 * node + 2];

        }
        public void add(int x){
            x+=offset;
            assert(x >= 0);
            add(0 , 0 , n , x , 1);
        }

        private int get (int node , int nl , int nr , int l , int r){
            if (nl >= l && r >= nr ) return Tree[node];
            int md = (nl+nr) / 2;
            if (l >= md) return get (2 * node + 2 , md , nr , l , r);
            if (md >= r) return get (2 * node + 1 , nl , md , l , r);
            return get (2 * node +1 , nl , md , l , r) + get (2 * node + 2 , md , nr , l , r);

        }

        public int get (int x){
            x+=offset;
            //if (x == n) return 0;
            return get(0 , 0 , n , x , n);
        }


    }

    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int [] freq = new int[n+5];
        for (int i = 0; i < n; i++){
            if (target == nums[i]) freq[i+1] = 1;
            freq[i+1]+= freq[i];
        }
        long ans = 0;
        SegmentTree segmentTree = new SegmentTree(n+1);
        for (int R = n; R >= 0; --R){
            ans+= segmentTree.get(2 * freq[R] - R+1);
            segmentTree.add(2 * freq[R] - R);
        }
        return ans;
        
    }
}