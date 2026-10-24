class Solution {
    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
     
     String start=arriveAlice.compareTo(arriveBob)>0?arriveAlice:arriveBob;
     String end=leaveAlice.compareTo(leaveBob)<0?leaveAlice:leaveBob;
     if(start.compareTo(end)>0) return 0;
     int[] days={31,28,31,30,31,30,31,31,30,31,30,31};
     return Math.max(0,getDays(days,end)-getDays(days,start)+1);
    }
    private int getDays(int[] days,String date){
        int day=Integer.parseInt(date.substring(3,5));
        int month=Integer.parseInt(date.substring(0,2));
        for(int i=0;i<month-1;i++){
            day+=days[i];
        }
        return day;
    }
}