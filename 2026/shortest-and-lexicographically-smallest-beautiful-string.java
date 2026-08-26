class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int j = 0;
        String answer = "";
        int ones = 0;
        for (int i = 0; i < n; i++){
            while (j < n&& ones < k)  ones+= s.charAt(j++)-'0';

            if (ones == k&& s.charAt(i) == '1'){
                String current = s.substring(i , j);

                if (answer.isEmpty() || current.length() < answer.length() || 
                (current.length() == answer.length() && current.compareTo(answer) < 0)){
                    answer = current;
                }
            }
            ones-= s.charAt(i)-'0';

        }
        return answer;
        
    }
}