class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int rank = 1;
        int n = arr.length;
        if (n == 0) return new int[0];
        Integer [] ids = IntStream.range(0 , n).boxed().toArray(Integer[]::new);
        Arrays.sort(ids , (x ,y) -> Integer.compare(arr[x] , arr[y]));
        int [] answer = new int[n];
        answer[ids[0]] = rank;
        for (int i = 1; i < n; i++){
            int prev = ids[i-1];
            int cur = ids[i];
            if (arr[cur] != arr[prev]) rank++;
            answer[cur] = rank;
        }
        return answer;
        
    }
}