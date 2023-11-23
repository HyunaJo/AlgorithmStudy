import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        HashMap<String, String> nicknames = new HashMap<>();
        int recordCnt = record.length;
        
        int answerCnt = 0;
        ArrayList<String> commands = new ArrayList<>();
        for(int i=0;i<recordCnt;i++){
            String[] info = record[i].split(" ");
            
            if(info[0].equals("Change")){ // 닉네임 변경
                nicknames.put(info[1],info[2]);
            }
            else{ // 입장, 퇴장
                if(info[0].equals("Enter")){ // 입장
                    nicknames.put(info[1],info[2]);
                }
                answerCnt++;
                commands.add(info[0]+" "+info[1]);
            }
        }
        
        String[] answer = new String[answerCnt];
        for(int i=0;i<answerCnt;i++){
            String[] command = commands.get(i).split(" ");
            if(command[0].equals("Enter")){ // 입장
                answer[i] = nicknames.get(command[1])+"님이 들어왔습니다.";
            }
            else{ // 퇴장
                answer[i] = nicknames.get(command[1])+"님이 나갔습니다.";
            }
        }
        
        return answer;
    }
}