import java.util.*;

class Tangerine implements Comparable<Tangerine>{
    int size;
    int cnt;

    public Tangerine(int size, int cnt){
        this.size = size;
        this.cnt = cnt;
    }
    
    @Override
	public int compareTo(Tangerine o) {
		return o.cnt - this.cnt;
	}
}

class Solution {
    
    
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        
        HashMap<Integer, Integer> map = new HashMap<>();
        ArrayList<Tangerine> tangerines = new ArrayList<>();
        
        for(int i=0;i<tangerine.length;i++){
            if(map.containsKey(tangerine[i])){
                map.put(tangerine[i],map.get(tangerine[i])+1);
            }
                
            else{
                map.put(tangerine[i],1);
            }
        }
        
        
        for(Integer key:map.keySet()){
            tangerines.add(new Tangerine(key, map.get(key)));
        }
        
        Collections.sort(tangerines);
        int total = 0;
        for(Tangerine tan:tangerines){
            if(total>=k)
                break;
            total += tan.cnt;
            answer++;
        }
        
        return answer;
    }
}