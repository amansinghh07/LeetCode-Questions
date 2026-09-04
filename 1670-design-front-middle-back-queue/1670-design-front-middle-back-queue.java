import java.util.LinkedList;

class FrontMiddleBackQueue {

    LinkedList<Integer> queue;

    public FrontMiddleBackQueue() {
        queue = new LinkedList<>();
    }
    
    public void pushFront(int val) {
        queue.addFirst(val);
    }
    
    public void pushMiddle(int val) {
        int index = queue.size() / 2;
        queue.add(index, val);
    }
    
    public void pushBack(int val) {
        queue.addLast(val);
    }
    
    public int popFront() {
        if (queue.isEmpty()) {
            return -1;
        }

        return queue.removeFirst();
    }
    
    public int popMiddle() {
        if (queue.isEmpty()) {
            return -1;
        }

        int index = (queue.size() - 1) / 2;
        return queue.remove(index);
    }
    
    public int popBack() {
        if (queue.isEmpty()) {
            return -1;
        }

        return queue.removeLast();
    }
}
