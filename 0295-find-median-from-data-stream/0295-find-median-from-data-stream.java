class MedianFinder {
    PriorityQueue<Integer>mx;
     PriorityQueue<Integer>mn;
    public MedianFinder() {
    mx=new PriorityQueue<>(Collections.reverseOrder());
    mn=new PriorityQueue<>();
    }

    public void addNum(int num) {
      if(mx.isEmpty() || num<=mx.peek())
        mx.offer(num);
        else mn.offer(num);
        if(mx.size() > mn.size() + 1){
            mn.offer(mx.poll());
        }else if(mn.size() > mx.size()){
            mx.offer(mn.poll());
        }
    }

    public double findMedian() {
     if(mx.size()==mn.size())
     return (mx.peek() + mn.peek())/2.0;
     return mx.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */