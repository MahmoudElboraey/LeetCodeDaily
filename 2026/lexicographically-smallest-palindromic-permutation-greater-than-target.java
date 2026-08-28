class Solution {
    private boolean canBePal(String s){
        int [] cnt = new int[26];
        for (char c : s.toCharArray()){
            cnt[c-'a']++;
        }
        int odd = 0;
        for (int i = 0; i < 26; ++i){
            if ((cnt[i] & 1) != 0) odd++;
        }
        return odd <= 1;
    }

    private boolean isPal(String s){
        int n = s.length();
        for (int i = 0; i < n / 2; ++i){
            if (s.charAt(i) != s.charAt(n-i-1)) return false;
        }
        return true;
    }

    private String canFormPal(String s , int keptLen , char c , int [] remain , char oddChar){
        int n = s.length();
        StringBuilder answer = new StringBuilder();
        while(answer.length() < n) answer.append(' ');

        for (int i = 0; i < keptLen; ++i) answer.setCharAt( i , s.charAt(i));
        answer.setCharAt(keptLen , c);
        if (keptLen == n-1) {
            String cand = answer.toString();
            return isPal(cand) ? cand : null;
        }
        

        if ((n & 1) != 0){
            // try to put the odd char in it's postion
            int pos = n / 2;
            if (keptLen >= pos){
                if (answer.charAt(pos) != ' ' && answer.charAt(pos) != oddChar) return null;
            }else {
                answer.setCharAt(pos , oddChar);
                remain[(oddChar-'a')]--;
            }
          //  System.out.println(" executed ");
        }
      //  System.out.println(answer.toString());

        int i = 0; int j = n -1;
    
        while(i < j){
            int cc = answer.charAt(i)-'a';
         //   System.out.println("i is "+i + " and j is " +j);
            if (answer.charAt(i) != ' ' && answer.charAt(j) != ' '){
               if (answer.charAt(i) != answer.charAt(j)) return null;
            }
            else if (answer.charAt(i) != ' '){
              //  System.out.println("cc is and remain " + cc + " " + remain[cc]);
                if (remain[cc] > 0){
                    answer.setCharAt(j , (char)(cc + 'a'));
                    remain[cc]--;
                }else {
                    return null;
                }
            }else {
                // find any char >= 2;
                boolean done = false;
                for (int k = 0; k < 26; ++k){
                    if (remain[k] >= 2){
                        remain[k]-=2;
                        char x = (char) (k + 'a');
                        answer.setCharAt(i , x);
                        answer.setCharAt(j , x);
                        done = true;
                        break;
                    }
                }
                if (!done){
                    return null;
                }
            }
            ++i;
            --j;
        }
        return answer.toString();



    }
    public String lexPalindromicPermutation(String s, String target) {
        if (!canBePal(s)) return "";

        int [] cnt = new int[26];
        for (char c : s.toCharArray()){
            cnt[c-'a']++;
        }
        int n = s.length();
        char oddChar = '?';
        for (int i = 0; i < 26; ++i) { 
            if ((cnt[i] & 1) != 0){
                oddChar = (char)(i+'a');
                break;
            }
        }

       
        int [] run = cnt.clone();
        String ans = "";
        for (int i = 0; i < n; ++i){
            // try to put at index i a char > target[i];

            int chT = target.charAt(i)-'a';

            for (int c = chT+1; c < 26; ++c){ // i kept prefix of lenth i 
                    // i will put the c at the inde i
                if (run[c] > 0){
                    // try to put c at index i 
                    // go and form the palindrome 
                    run[c]--;
                    String cand = canFormPal(target , i , (char)(c + 'a') , run.clone() , oddChar);
                    //System.out.println("current i is " + i + " sex is " + cand);

                    if (cand != null) {
                        if (ans.isEmpty() || ans.compareTo(cand) > 0){
                            ans = cand;
                        }

                    }
                    run[c]++;
                    
                }


            }

            if (run[chT] > 0){ // keep the current prefix as it is 
                run[chT]--;
            }else {
                break;
            }

        }

        return ans;
        
    }
}