import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static int N;
    public static long[][] dp;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        dp = new long[N+1][11];
        dp[0][0] = 1;
        dp[0][10] = 1;

        for(int i=1;i<=N;i++){
            dp[i][1] = dp[i-1][10];
            dp[i][10] += dp[i][1];

            for(int j=2;j<=9;j++){
                dp[i][j] = (dp[i][j-1]-dp[i-1][j-1])%10_007;
                if(dp[i][j]<0){
                    dp[i][j] += 10_007;
                }
                dp[i][10] += dp[i][j];
            }
        }

        long sum = 0;
        for(int i=0;i<=N;i++){
            sum += dp[i][10];
        }
        bw.write(Long.toString(sum%10_007));
        bw.flush();

        br.close();
        bw.close();
    }
}
