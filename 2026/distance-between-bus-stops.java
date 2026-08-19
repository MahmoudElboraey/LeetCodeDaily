class Solution {

    private int solve(int [] dist , int st , int en){
        int ans = 0;
        int n = dist.length;
        while(st != en){
            ans+= dist[st];
            st = (st + 1) % n;
        }
        return ans;
    }

    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        int answerOfFirstPath = solve(distance , start , destination);
        int sum = Arrays.stream(distance).sum();
        //System.out.println(answerOfFirstPath + " " + sum);
        return Math.min(sum-answerOfFirstPath , answerOfFirstPath);
        
    }
}