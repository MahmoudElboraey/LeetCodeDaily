class Solution {

   

       private long permCount(int[] cnt, int len) {
      final long CAP = 2_000_000L;  
      long res = 1;
      int filled = 0;
      for (int i = 0; i < 26; i++) {
          for (int j = 1; j <= cnt[i]; j++) {
              filled++;
              res = res * filled / j;     
              if (res > CAP) return CAP;  
          }
      }
      return res;
  }
    public String smallestPalindrome(String s, int k) {
        int [] freq = new int[26];
        int n = s.length();
        // ways = n /2 !
        for (char ch : s.toCharArray()){
            freq[ch-'a']++;
        }

        
      
        int halfLen = 0;
        String mid = "";
        for (char c = 'a'; c <= 'z'; ++c){
            int count = freq[c-'a'];
            halfLen+= count / 2;
            if (count % 2 == 1) mid = String.valueOf(c);
            freq[c-'a']/=2;
        }
      
        StringBuilder answer = new StringBuilder();

        for (int pos = 0; pos < halfLen ; ++pos){
            boolean placed = false;
            for (char c = 'a'; c <= 'z'; ++c){
                int ch = c-'a';
                if (freq[ch] == 0) continue;
                freq[ch]--;

                long ways = permCount(freq , halfLen - pos -1);
                if (k <= ways){
                    answer.append(c);
                    placed = true;
                    break;

                }
                k-=ways;
                freq[ch]++;
               

            }
            if (!placed) return ""; 
        }
      
        
        System.out.println(answer.toString());

        return answer.toString() + mid + answer.reverse().toString();
        
    }
}