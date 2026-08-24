class Solution {
     public boolean sumGame(String num) {
      int n = num.length(), d = 0, q1 = 0, q2 = 0;
      for (int i = 0; i < n; i++) {
          char c = num.charAt(i);
          boolean first = i < n / 2;                                                                                                                                            
          if (c == '?') { if (first) q1++; else q2++; }
          else d += first ? (c - '0') : -(c - '0');
      }
      if ((q1 + q2) % 2 == 1) return true;                                                                                                         
      return 2 * d != 9 * (q2 - q1);  
  }

}