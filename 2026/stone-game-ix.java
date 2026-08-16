class Solution {
    private int [] freq ;
    public boolean stoneGameIX(int[] stones) {
        freq = new int[3];
        for (int value : stones){
            freq[value % 3]++;
        }
        // alice starts with 1 , bob starts with 1
        // alice start with 1 , bob starts with 2
        // alice starts with 2 , bob starts with 2 
        // alice starts with 2 , bob starts with 1


        if (freq[0] % 2 != 0){
            return Math.abs(freq[1] - freq[2]) > 2;
        }

        return Math.min(freq[1] , freq[2]) >= 1; 

        
    }
}