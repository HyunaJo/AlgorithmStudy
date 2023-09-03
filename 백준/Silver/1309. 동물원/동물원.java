import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static int N; // 우리의 크기
    public static int[][] dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        dp = new int[N+1][3];

        // dp[n][0] -> n번째 줄에 사자 안 둔 경우의 수
        // dp[n][1],dp[n][2] -> n번째 줄의 1/2번째 칸에 사자 둔 경우의 수
        dp[1][0] = dp[1][1] = dp[1][2] = 1;
        for(int i=2;i<=N;i++){
            dp[i][0] = (dp[i-1][0]+dp[i-1][1]+dp[i-1][2])%9901;
            dp[i][1] = (dp[i-1][0]+dp[i-1][2])%9901;
            dp[i][2] = (dp[i-1][0]+dp[i-1][1])%9901;
        }

        bw.write(Integer.toString((dp[N][0]+dp[N][1]+dp[N][2])%9901));
        bw.flush();

        br.close();
        bw.close();
    }
}
