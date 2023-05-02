import java.util.*;

class Solution {
    public int[] solution(String msg) {
        int[] answer = {};
        HashMap<String, Integer> map = new HashMap<>();
        for(int i=0;i<26;i++){
            map.put(Character.toString('A'+i),i+1);
        }
        
        int checkIdx = 0;
        ArrayList<Integer> tempAnswers = new ArrayList<>();
        while(checkIdx<msg.length()){
            String checkString = "";
            boolean isAdd2Answer = false;
            
            for(int i=checkIdx;i<msg.length();i++){
                Character tempChar = msg.charAt(i);
                if(!map.containsKey(checkString+tempChar)){ // 만약에 map에 없는 단어면
                    tempAnswers.add(map.get(checkString));
                    map.put(checkString+tempChar,map.size()+1); // map에 추가 후 break
                    checkIdx += checkString.length();
                    isAdd2Answer = true;
                    break;
                }
                checkString += tempChar;
            }
            if(!isAdd2Answer){
                tempAnswers.add(map.get(checkString));
                checkIdx += checkString.length();
            }
        }

        answer = new int[tempAnswers.size()];
        for(int i=0;i<tempAnswers.size();i++)
            answer[i] = tempAnswers.get(i);
        return answer;
    }
}