/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;
};
*/

class Solution {

    public Node flatten(Node head) {

        if (head == null) {
            return null;
        }

        Node curr = head;

        while (curr != null) {

            // If curr has no child, simply move forward
            if (curr.child == null) {
                curr = curr.next;
                continue;
            }

            // Save the original next node
            Node next = curr.next;

            // Flatten the child list
            Node child = flatten(curr.child);

            // Connect curr -> child
            curr.next = child;
            child.prev = curr;

            // Child pointer must be removed
            curr.child = null;

            // Find the tail of the flattened child list
            Node tail = child;

            while (tail.next != null) {
                tail = tail.next;
            }

            // Connect tail -> original next
            tail.next = next;

            if (next != null) {
                next.prev = tail;
            }

            curr = next;
        }

        return head;
    }
}