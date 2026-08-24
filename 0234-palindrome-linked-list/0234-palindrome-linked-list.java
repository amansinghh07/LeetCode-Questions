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
    public boolean isPalindrome(ListNode head) {
        if(head== null || head.next == null){
            return true;
        }
      ListNode fast=head;
      ListNode slow=head;
      while(fast.next!=null && fast.next.next!=null){
        slow=slow.next;
        fast=fast.next.next;
      }
      ListNode newHead=reverseLinkedList(slow.next);
      ListNode first=head;
      ListNode second=newHead;
      while(second!=null){
        if(first.val!=second.val){
            reverseLinkedList(newHead);
            return false;
        }
        first=first.next;
        second=second.next;
      }
      reverseLinkedList(newHead);
      return true;
    }
    private ListNode reverseLinkedList(ListNode head){
        ListNode prev=null;
        while(head!=null){
            ListNode front=head.next;
            head.next=prev;
            prev=head;
            head=front;
        }
        return prev;
    }
}