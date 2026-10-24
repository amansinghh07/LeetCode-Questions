class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
         int n=asteroids.length;
         Arrays.sort(asteroids);
         long currentMass=mass;
         for(int i=0;i<n;i++){
            if(asteroids[i]<=currentMass){
                currentMass+=asteroids[i];
            }else{
                return false;
            }
         }
         return true;
    }
}