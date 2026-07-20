class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int n = grid.length;
        int m = grid[0].length;
      
        int [] elements = new int[n * m];
        int idx = 0;
        for (int i = 0; i < n; ++i){
            for (int j = 0; j < m; ++j){
                elements[idx++] = grid[i][j];
            }
        }
        k %= (n *m);

        k = (n * m) -k;
        k%= (n * m);
         

        List<List<Integer>> answer = new ArrayList<>();
        for (int i = 0; i < n; ++i){
            ArrayList<Integer> cur = new ArrayList<>();
            for (int j = 0; j < m; ++j){
            cur.add(elements[k]);
            k = (k + 1) % (m * n);
            }
            answer.add(cur);
        }
        return answer;
        
        
    }
}