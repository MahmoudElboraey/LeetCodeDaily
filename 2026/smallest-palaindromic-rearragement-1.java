class Solution {
    public String smallestPalindrome(String s) {
        int [] freq = new int[26];
        for (char c : s.toCharArray()){
            freq[c-'a']++;
        }
        int n = s.length();
        StringBuilder answer = new StringBuilder(n);
        answer.setLength(n);
        for (int c = 0; c < 26; ++c){
            char ch = (char)('a' + c);
            if (freq[c] % 2 == 1) {
                answer.setCharAt(n / 2 , ch);
                break;
            }
        }
        int idx = 0;
        for (int c = 0; c < 26; ++c){
            char ch = (char)('a' + c);
            for (int j = 0; j < freq[c] / 2; ++j) answer.setCharAt(idx++ , ch);
        }

       
        int start = (n -2) / 2;
        System.out.println(answer.toString());
        if (n % 2 == 1) idx++;

        for (int i = 0; i < n / 2; i++){
            answer.setCharAt(idx++ , answer.charAt(start--));
        }
        return answer.toString();
        
    }
}