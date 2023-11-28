import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static int N;
    public static int[] dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        dp = new int[N+3];
        dp[2] = 1;
        dp[3] = 1;

        for(int i=4;i<=N;i++){
            int min = Integer.MAX_VALUE;
            if(i%3 == 0){
                min = Math.min(min, dp[i/3] + 1);
            }
            
            if(i%2 == 0){
                min = Math.min(min, dp[i/2] + 1);
            }

            min = Math.min(min, dp[i-1] + 1);
            dp[i] = min;
        }

        bw.write(Integer.toString(dp[N]));
        bw.flush();

        br.close();
        bw.close();
    }
}
