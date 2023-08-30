import java.util.*;

class Music implements Comparable<Music>{
    int idx;
    int playingTime;
    String title;
    String musicInfo;
    
    public Music(int idx, int playingTime, String title, String musicInfo){
        this.idx = idx;
        this.playingTime = playingTime;
        this.title = title;
        this.musicInfo = musicInfo;
    }
    
    @Override
    public int compareTo(Music o){
        if(o.playingTime == this.playingTime){
            return this.idx - o.idx;
        }
        
        return o.playingTime - this.playingTime;
    }
}

class Solution {
    public static Music[] musics;
    public String solution(String m, String[] musicinfos) {
        // m = 네오가 기억한 멜로디
        m = changeInfo(m);
        
        int musicinfosCnt = musicinfos.length; // musicinfos 개수
        musics = new Music[musicinfosCnt];
        for(int i=0;i<musicinfosCnt;i++){
            String musicInfo = musicinfos[i];
            String[] infos = musicInfo.split(",");
            
            musics[i] = new Music(i, timeToMinutes(infos[1])-timeToMinutes(infos[0]),infos[2],changeInfo(infos[3]));
        }
        
        Arrays.sort(musics);
        String answer = "";
        for(int i=0;i<musicinfosCnt;i++){
            Music now = musics[i];
            int playingTime = now.playingTime;
            String info = now.musicInfo;
            
            String totalMusic = "";
            for(int j=0;j<playingTime;j++){
                totalMusic += Character.toString(info.charAt(j%info.length()));
            }
            
            if(totalMusic.contains(m)){
                answer = now.title;
                break;
            }
        }
        
        if(answer.equals(""))
            answer = "(None)";
        
        return answer;
    }
    
    public static String changeInfo(String info){
        info = info.replaceAll("C#","c");
        info = info.replaceAll("D#","d");
        info = info.replaceAll("F#","f");
        info = info.replaceAll("G#","g");
        info = info.replaceAll("A#","a");
        
        return info;
    }
    
    public static int timeToMinutes(String time){
        String[] times = time.split(":");
        int hour = Integer.parseInt(times[0])*60;
        int minute = Integer.parseInt(times[1]);
        
        return hour+minute;
    }
}