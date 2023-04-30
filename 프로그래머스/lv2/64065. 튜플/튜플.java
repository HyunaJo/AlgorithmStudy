import java.util.*;

class Num implements Comparable<Num>{
    int num;
    int cnt;
    
    public Num(int num, int cnt){
        this.num = num;
        this.cnt = cnt;
    }
    
    @Override
    public int compareTo(Num o){
        return o.cnt - this.cnt;
    }
    
}

class Solution {
    public int[] solution(String s) {
        int[] answer = {};
        s = s.replace("{","").replace("}","");
        String[] strNums = s.split(",");
        Map<Integer,Integer> map = new HashMap<>();
        
        for(int i=0;i<strNums.length;i++){
            int num = Integer.parseInt(strNums[i]);
            if(map.containsKey(num))
                map.put(num,map.get(num)+1);
            else
                map.put(num,1);
        }
        
        ArrayList<Num> nums = new ArrayList(); 
        for(int key:map.keySet()){
            nums.add(new Num(key, map.get(key)));
        }
        
        Collections.sort(nums);
        answer = new int[nums.size()];
        for(int i=0;i<nums.size();i++)
            answer[i] = nums.get(i).num;
        
        return answer;
    }
}