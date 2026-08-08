
  class Solution {
      public int[] validSequence(String word1, String word2) {
          int n = word1.length(), m = word2.length();

          // suf[i] = max index p such that word2[i..m-1] is a subsequence of word1[p..n-1]
          // -1 means impossible
          int[] suf = new int[m + 1];
          suf[m] = n;
          int j = n - 1;
          for (int i = m - 1; i >= 0; --i) {
              while (j >= 0 && word1.charAt(j) != word2.charAt(i)) j--;
              if (j < 0) {
                  suf[i] = -1;
              } else {
                  suf[i] = j;
                  j--;
              }
          }
  
          int[] ans = new int[m];
          int i = 0;
          boolean used = false;
          for (int k = 0; k < n && i < m; ++k) {
              if (word1.charAt(k) == word2.charAt(i)) {
                  ans[i++] = k;
              } else if (!used && suf[i + 1] != -1 && k + 1 <= suf[i + 1]) {
                  ans[i++] = k;
                  used = true;
              }
          }

          return i == m ? ans : new int[]{};
      }
  }