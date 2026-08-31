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
        // Fast fail: requires at least 3 nodes
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        int firstIdx = -1;
        int prevIdx = -1;
        int minDistance = Integer.MAX_VALUE;
        
        int idx = 1;
        ListNode prev = head;
        ListNode curr = head.next;

        while (curr.next != null) {
            int cv = curr.val;
            int pv = prev.val;
            int nv = curr.next.val;

            // Single unified conditional check to reduce branching overhead
            if ((cv > pv && cv > nv) || (cv < pv && cv < nv)) {
                if (firstIdx == -1) {
                    firstIdx = idx;
                } else {
                    int dist = idx - prevIdx;
                    if (dist < minDistance) {
                        minDistance = dist;
                    }
                }
                prevIdx = idx;
            }

            prev = curr;
            curr = curr.next;
            idx++;
        }

        // If fewer than two critical points were found
        if (firstIdx == prevIdx) {
            return new int[]{-1, -1};
        }

        return new int[]{minDistance, prevIdx - firstIdx};
    }
}
