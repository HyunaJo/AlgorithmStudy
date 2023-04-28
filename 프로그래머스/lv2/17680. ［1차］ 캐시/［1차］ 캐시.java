import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        if(cacheSize == 0) // cachaeSize가 0인 경우
            answer = 5*cities.length; // 무조건 cache miss
        else{
            Deque<String> deque = new ArrayDeque<>();
        
            for(int i=0;i<cities.length;i++){
                String city = cities[i].toLowerCase();

                if(deque.contains(city)){ // deque에 도시 이름 있는 경우
                    // 해당 도시 이름 맨 뒤로 보내기
                    deque.remove(city);
                    deque.addLast(city);
                    answer += 1;
                }
                else{ // deque에 도시 이름 없는 경우
                    if(deque.size() == cacheSize){ // 이미 cachsize만큼 넣어둔 경우
                        // 가장 앞에 있는 도시 이름 빼고 새로운 도시 이름 넣기
                        deque.removeFirst();
                        deque.addLast(city);
                        answer += 5;
                    }
                    else{
                        deque.addLast(city);
                        answer += 5;
                    }
                }
            }
        }
        
        return answer;
    }
}