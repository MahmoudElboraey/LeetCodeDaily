

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        int index = 0;
        int [] answer = new int[2];
        answer[0] = Integer.MAX_VALUE;
        answer[1] = Integer.MIN_VALUE;
        // 0 --> get min
        // 1 --> get max

        int firstIndex = -1;
        int previousIndex = -1;
        int preValue = -1;

        ListNode cur = head;
        while(cur != null){
            int value = cur.val;

            if (preValue != -1 && cur.next != null){
                int nextValue = cur.next.val;
                if ((value > preValue && value > nextValue) || (value < preValue && value < nextValue)) {
                    // this is a critical shit 
                    System.out.println(index + " " + previousIndex + " " + firstIndex);
                    if (firstIndex != -1){
                        answer[0] = Math.min(answer[0] , index - previousIndex);
                        answer[1] = Math.max(answer[1] , index - firstIndex);
                       
                    }else{
                        firstIndex = index;
                    }
                    previousIndex = index;
                    

                }

            }


            index++;
            preValue = value;
            cur = cur.next;
        }
        
        if (answer[0] == Integer.MAX_VALUE) Arrays.fill(answer , -1);
        return answer;
    }
}