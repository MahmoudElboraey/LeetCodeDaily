class Solution {

    private boolean [] visited;

    private Map<Integer , List<Integer> > graph;

    private void dfs(int node){
        if (visited[node]) return;
        visited[node] = true;

        for (int ch : graph.get(node)){
            if (!visited[ch]){
                dfs(ch);
            }
        }
    }
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        visited = new boolean [n];
        graph = new HashMap<>();
        for (int i = 0; i < n; ++i){
            graph.put(i , new ArrayList<>());
        }
        // build the graph
        int m = invocations.length;
        for (int i = 0; i < m; i++){
            int u = invocations[i][0];
            int v = invocations[i][1];
            graph.get(u).add(v);
        }
        dfs(k);
        boolean canBeRemoved = true;

        x:for (int i = 0; i < n; ++i){
           
            if (visited[i]) continue;
            for (int ch : graph.get(i)){
                if (visited[ch]){
                    canBeRemoved = false;
                    break x;
                }
            }
        }


        List<Integer> answer = new ArrayList<>();
        for (int i = 0;i < n; i++){
           if (canBeRemoved && visited[i]) continue;
            answer.add(i);
        }
        return answer;

        
    }
}