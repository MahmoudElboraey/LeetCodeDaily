class Solution {

    private boolean valid(int x , int y , int n , int m ){
        return x >= 0 && y >= 0 && x < n && y < m;
    }

    private boolean bfs (List<List<Integer>> grid , int health){
        Deque<Pair<Integer , Integer >> q = new ArrayDeque<>();
        int [] dx = {1 , -1 , 0 , 0};
        int [] dy = {0 ,  0 , 1 , -1};
        q.addFirst(new Pair<>(0 , 0));
        int n = grid.size();
        int m = grid.get(0).size();

        int [][] dist = new int[n+1][m+1];
        for (int i = 0; i < n; i++){
            Arrays.fill(dist[i] , 0); 
        }
        dist[0][0] = health - grid.get(0).get(0);

        while(!q.isEmpty()){
            Pair<Integer , Integer > node = q.pop();
            for (int i = 0; i < 4; i++){
                int nx = dx[i] + node.getKey();
                int ny = dy[i] + node.getValue();
                if (valid(nx , ny , n , m)){
                    int value = grid.get(nx).get(ny);
                    int cost = dist[node.getKey()][node.getValue()] - value;
                  
                    if (dist[nx][ny] < cost){
                        dist[nx][ny] = cost;
                        Pair < Integer , Integer > newNode = new Pair<>(nx , ny);
                        if (value == 0){
                            q.addFirst(newNode);
                        }else {
                            q.addLast(newNode);
                        }

                    }
                }
            }
        }
        System.out.println(dist[n-1][m-1]);

        return  dist[n-1][m-1] > 0;
    }
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {

        return bfs(grid , health);

        
    }
}