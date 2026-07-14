class KthLargest {
    int K;
    PriorityQueue<Integer>pq;
    public KthLargest(int k, int[] nums) {
        K=k;
        pq=new PriorityQueue<>();
        for(int i:nums){
            if(pq.size()<K){
                pq.offer(i);
            }else if(i>=pq.peek()){
                pq.poll();
                pq.offer(i);
            }
        }
    }
    
    public int add(int val) {
        if(pq.size()<K){
            pq.offer(val);
        }else if(val>=pq.peek()){
            pq.poll();
            pq.offer(val);
        }
        return pq.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */