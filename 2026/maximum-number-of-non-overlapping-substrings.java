class Solution {
    private int [][] pre;
    private int [] firstOc , lastOc;
    private int go(Set<Integer > zeroChars , Set<Integer> ls , int R ){
        int ans = -1;
        for (int l : ls){
            boolean take = true;
            for (int c = 0; c < 26; ++c){
                if (zeroChars.contains(c)) {
                    if (lastOc[c] < l || (firstOc[c] >= l && lastOc[c] <= R)) continue;
                    take = false;
                    break;
                }
                int count = pre[c][R]- (l == 0 ? 0 : pre[c][l-1]);
                if (count > 0){
                    take = false;
                    break;
                }

                

            }
            if (take){
                ans = l;
            }
        }
    


        return ans;
    }
    public List<String> maxNumOfSubstrings(String s) {
        firstOc = new int[26];
        int [] oc = new int[26]; 
        Arrays.fill(firstOc , -1);
        int n = s.length();
        lastOc = new int[26];
        Arrays.fill(lastOc , -1);
        pre = new int[26][n];

        for (int i = 0; i < n; ++i){
            int c = s.charAt(i)-'a';
            if (firstOc[c] == -1) firstOc[c] = i;
            oc[c]++;
            lastOc[c] = i;
            pre[c][i]++;
        }
        for (int c= 0; c < 26; ++c){
            for (int i = 1; i < n; ++i){
                pre[c][i]+= pre[c][i-1];
            }
        }

        Set<Integer> set = new HashSet<>();
        TreeSet<Integer > ls = new TreeSet<>();
        List<String> answer = new ArrayList<>();
        int mx = -1;
        for (int R = 0; R < n; ++R){
            int c = s.charAt(R)-'a';
            oc[c]--;
            if (oc[c] == 0){
                set.add(c);
                if (firstOc[c] > mx) {
                ls.add(firstOc[c]);
             
                int l = go(set , ls , R );
                if (l != -1){
                    mx = l;
                    answer.add(s.substring(l , R+1));
                    ls.headSet(l,true).clear();
                }
                }
            }

        }

        return answer; 
    }
}