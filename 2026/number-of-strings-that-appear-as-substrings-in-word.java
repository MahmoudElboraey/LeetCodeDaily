class Solution {
    public int numOfStrings(String[] patterns, String word) {
        int ans = 0;
        for (String current : patterns){
            if (word.contains(current)) ans++;
        }
        return ans;
        
    }
}