class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        Map<Character , Integer > ss = new HashMap<>();
        for (char c : s.toCharArray()){
            ss.put(c , ss.getOrDefault(c , 0) + 1);
        }

        int keptPrefix = -1;
        char picked = '?';

        for (int i = 0; i < n; ++i){
            // try to put at the index i char > target[i]
            // keep the previous prefix of lenth i 
            // the maximum prefix length i can keep is n - 1 

            char chT = target.charAt(i);
            boolean can = false;
            for (char c = ++chT ; c <= 'z'; ++c){
                if (ss.getOrDefault(c , 0) > 0){
                    keptPrefix = i;
                    can = true;
                    picked = c;
                    break;
                }
            }
            if (ss.getOrDefault(--chT , 0) > 0){
                ss.put(chT , ss.get(chT) - 1);
            }else {
                break;
            }


        }

        if (keptPrefix == -1) return "";
        // picked char at i 
        // the rest should be put for a to z in the result string 

        StringBuilder answer = new StringBuilder();
        ss.clear();
        for (char c : s.toCharArray()){
            ss.put(c , ss.getOrDefault(c , 0) + 1);

        }

        for (int i = 0; i < keptPrefix ; ++i){
            char c = target.charAt(i);
            ss.put(c , ss.get(c) - 1);
            answer.append(c);

        }
        answer.append(picked);
        ss.put(picked , ss.get(picked) - 1);
        for (char c = 'a'; c <= 'z'; ++c){
            while(ss.getOrDefault(c , 0) > 0){
                answer.append(c);
                ss.put(c , ss.get(c) - 1);
            }
        }
        return answer.toString();
    }
}