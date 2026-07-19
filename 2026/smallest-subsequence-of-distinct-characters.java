class Solution {
    public String smallestSubsequence(String s) {
        int n = s.length();
        Map<Character , TreeSet<Integer> > g = new HashMap<>();
        for (int i = 0; i < n; i++){
            char c = s.charAt(i);
            g.computeIfAbsent(c , k-> new TreeSet<>()).add(i);
        }

        Set<Character > set = new HashSet<>(g.keySet());


        int distinct = g.size();
        StringBuilder answer = new StringBuilder("");
        int last = -1;
        while(distinct-- > 0){
          

            // can i pick the current character 
            for (char c = 'a'; c <= 'z'; ++c){
                if (!set.contains(c)) continue;
                boolean can = true;
                // try to pick it if success break , else try another char 
                Integer next = g.get(c).higher(last);
                if (next == null) continue;

                // max element in the remainiing chars still not null okay 

                for (char ch : set){
                    if (ch == c) continue;
                  
                    Integer nex = g.get(ch).higher(next);

                    if (nex == null){
                        can = false;
                        break;
                    }
                } 
                if (can) {
                    answer.append(c);
                    set.remove(c);
                    last = next;
                    break;
                }
            }
        }
        





        return answer.toString();
      
    }
}