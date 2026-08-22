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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyNode=new ListNode(-1);
        ListNode tempHead=dummyNode;
        ListNode temp1=l1,temp2=l2;
        int carry=0;
        while(temp1!=null || temp2 !=null){
            int val1=(temp1!=null)?temp1.val:0;
            int val2=(temp2!=null)?temp2.val:0;
            int sum=val1+val2+carry;
            int digit=sum%10;
            carry=sum/10;
            tempHead.next=new ListNode(digit);
            tempHead=tempHead.next;
            if(temp1!=null) temp1=temp1.next;
            if(temp2!=null) temp2=temp2.next;
        }
        if(carry>0){
            tempHead.next=new ListNode(carry);
        }
        return dummyNode.next;
    }
}