import java.util.*;
import java.io.*;

class Traffic{
    int startTime;
    int endTime;
    
    public Traffic(int startTime, int endTime){
        this.startTime = startTime;
        this.endTime = endTime;
    }
}

class Solution {
    int N; // lines[] 데이터 개수
    Traffic[] traffic; // 트래픽 정보들
    
    public int solution(String[] lines) {
        N = lines.length;
        traffic = new Traffic[N];
        
        for(int i=0;i<N;i++){
            String[] data = lines[i].split(" ");
            int endTime = countTime(data[1]); // 끝난 시간
            int time = (int)(Double.parseDouble(data[2].replace("s",""))*1000); // 걸린 시간
            traffic[i] = new Traffic(endTime-time+1,endTime);
        }
        
        int answer = 0;
        for(int i=0;i<N;i++){
            int completeCnt = 1;
            for(int j=i+1;j<N;j++){
                if(traffic[j].startTime<traffic[i].endTime+1000)
                    completeCnt++;
            }
            
            answer = Math.max(answer,completeCnt);
        }
        
        return answer;
    }
    
    public int countTime(String timeStr){
        int time = 0;
        
        String[] times = timeStr.split(":");
        time += Integer.parseInt(times[0])*60*60;
        time += Integer.parseInt(times[1])*60;
        time *= 1000;
        time += (int)(Double.parseDouble(times[2])*1000);
        
        return time;
    }
}