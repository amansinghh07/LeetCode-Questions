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
    public ListNode partition(ListNode head, int x) {
        ListNode dummy=new ListNode(-1);
        ListNode dummy2=new ListNode(-1);
        ListNode temp=dummy;
        ListNode temp2=dummy2;
        ListNode temp3=head;
        while(temp3!=null){
        if(temp3.val<x){
            temp.next=new ListNode(temp3.val);
            temp=temp.next;
        }else{
            temp2.next=new ListNode(temp3.val);
            temp2=temp2.next;
        }
        temp3=temp3.next;
        }
    temp.next=dummy2.next;
    return dummy.next;
    }
}