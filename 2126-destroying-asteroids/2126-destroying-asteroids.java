class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
         int n=asteroids.length;
         PriorityQueue<Integer>pq=new PriorityQueue<>();
         for(int asteroid:asteroids){
            pq.offer(asteroid);
         }
         long currentMass=mass;
         while(!pq.isEmpty()){
            int asteroid=pq.poll();
            if(currentMass<asteroid){
                return false;
            }
            currentMass+=asteroid;
         }
         return true;
    }
}