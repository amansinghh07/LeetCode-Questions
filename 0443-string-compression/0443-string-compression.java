class Solution {
    public int compress(char[] chars) {
        int i=0;
        int index=0;
        while(i<chars.length){
            char current=chars[i];
            int count=0;
            while(i<chars.length && chars[i]==current){
                i++;
                count++;
            }
            chars[index++]=current;
            if(count>1){
                String freq=String.valueOf(count);
                for(char ch: freq.toCharArray()){
                    chars[index++]=ch;
                }
            }
        }
        return index;
    }
}