class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        int totalSeconds = timeToSeconds(play_time);
        long[] dp = new long[totalSeconds+1];
        
        for(String log:logs){
            String[] logTime = log.split("-");
            int startSeconds = timeToSeconds(logTime[0]);
            int endSeconds = timeToSeconds(logTime[1]);
            
            dp[startSeconds] += 1;
            dp[endSeconds] -= 1;
        }
        
        for(int i=1;i<=totalSeconds;i++){
            dp[i] += dp[i-1];
        }
        
        for(int i=1;i<=totalSeconds;i++){
            dp[i] += dp[i-1];
        }
        
        int advSeconds = timeToSeconds(adv_time);
        int answerSeconds = 0;
        long maxSum = dp[advSeconds-1];
        for(int i=0;i+advSeconds<=totalSeconds;i++){
            long sum = dp[i+advSeconds]-dp[i];
            if(maxSum < sum){
                maxSum = sum;
                answerSeconds = i+1;
            }
        }
        
        return secondToTime(answerSeconds);
    }
    
    public int timeToSeconds(String time){
        String[] times = time.split(":");
        int hour = Integer.parseInt(times[0]);
        int minute = Integer.parseInt(times[1]);
        int second = Integer.parseInt(times[2]);
        return hour*3600+minute*60+second;
    }
    
    public String secondToTime(int timeSeconds){
        int hour = timeSeconds/3600;
        timeSeconds %= 3600;
        int minute = timeSeconds/60;
        timeSeconds %= 60;
        int second = timeSeconds;
        
        return String.format("%02d:%02d:%02d",hour,minute,second);
    }
}