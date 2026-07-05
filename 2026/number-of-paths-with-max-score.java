class Solution {
    private int mod = (int)1e9+7;

    private int add (int a, int b){
        return (a % mod + b % mod) % mod;
    }

    private boolean valid (int r , int c , int rows , int cols){
        return r >= 0 && c >= 0 && r < rows && c < cols;
    }

    private int getValue(char c){
        return switch(c){
                    case 'S' -> 0;
                    case 'E' -> 0;
                    default -> c - '0';
        };
    }

    private void solveGraph(List<String>board , int[][] maxSum , int[][] count){

        int [] dx = {-1 , 0 , -1};
        int [] dy = {0 , -1 , -1}; // left  , up , diagonally
        int rows = board.size();
        int cols = board.get(0).length();
        count[rows-1][cols-1] = 1;
        for (int r = rows-1; r >=0; --r){
            for (int c = cols -1; c >= 0 ; --c){
                

                for (int k = 0; k < 3; ++k){
                    int newR = r + dx[k];
                    int newC = c + dy[k];
                    if (!valid(newR , newC , rows , cols) || board.get(newR).charAt(newC) == 'X') continue;
                    int newW = maxSum[r][c] + getValue(board.get(newR).charAt(newC));
                    if (newW > maxSum[newR][newC]){
                        maxSum[newR][newC] = newW;
                        count[newR][newC] = count[r][c];
                    }
                    else if (newW == maxSum[newR][newC]){
                        count[newR][newC] = add(count[newR][newC] , count[r][c]);
                    }
                    
                }
            }
        }
    }


    public int[] pathsWithMaxScore(List<String> board) {
        int rows = board.size();
        int cols = board.get(0).length();
        int [][] maxSum = new int[rows][cols];
        int [][] count = new int [rows][cols];
        solveGraph(board , maxSum , count);
        if (count[0][0] == 0) {
            return new int []{0 , 0};
        }
        return new int[]{maxSum[0][0] , count[0][0]};



        
    }
}