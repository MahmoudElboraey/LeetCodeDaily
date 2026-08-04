class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        boolean [] visited = new boolean[101];
        int max = 0;
        int min = 100;
        for (int x : nums){
            visited[x] = true;
            max = Math.max(max , x);
            min = Math.min(min , x);
        }
        List<Integer> answer = new ArrayList<>();
        for (int i = min; i <= max; ++i){
            if (!visited[i]) answer.add(i);
        }
        return answer;
    }
}