class Solution {
    private int [] f;
    public int minimumPushes(String word) {
        f = new int[26];
        for (var ch : word.toCharArray()){
            f[ch-'a']++;
        }

        Integer [] id = new Integer [26];
        for (int i = 0; i < 26; ++i) id[i] = i;


      Arrays.sort(id, (a, b) -> Integer.compare(f[b], f[a]));
      int k = 0;
      int current = 1;
      int ans = 0;
      for (var cur : id){
        ++k;
        if (k == 9){
            ++current;
            k = 1;
        }
        ans+= f[cur] * current;
       // System.out.println((char)(cur+'a') + "  "+ f[cur] + "  "+current);
      }
      return ans;
        
    }
}