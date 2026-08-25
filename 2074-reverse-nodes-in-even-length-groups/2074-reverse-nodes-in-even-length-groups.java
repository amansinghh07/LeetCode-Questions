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
    public ListNode reverseEvenLengthGroups(ListNode head) {
        ListNode prevGroup=null;
        ListNode curr=head;
        int groupSize=1;
        while(curr != null){
            int actualSize=0;
            ListNode temp=curr;
            while(temp != null && actualSize < groupSize){
                temp=temp.next;
                actualSize++;
            }
            if(actualSize % 2 ==0){
                ListNode groupNext=temp;
                ListNode prev=groupNext;
                ListNode node=curr;
                for(int i=0;i<actualSize;i++){
                    ListNode front=node.next;
                    node.next=prev;
                    prev=node;
                    node=front;
                }
                if(prevGroup != null){
                    prevGroup.next=prev;
                } else{
                    head=prev;
                }
                prevGroup=curr;
                curr=groupNext;
            } else{
                for(int i=0;i<actualSize;i++){
                    prevGroup=curr;
                    curr=curr.next;
                }
            }
            groupSize++;
        }
        return head;
    }
}