import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static int n;
    public static int[] dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        dp = new int[n+1];
        dp[1] = 1;
        for(int i=2;i<=n;i++){
            int minCnt = Integer.MAX_VALUE;

            for(int j=1;j*j<=i;j++){
                minCnt = Math.min(minCnt, dp[i-j*j]+1);
            }
            dp[i] = minCnt;
        }

        bw.write(Integer.toString(dp[n]));
        bw.flush();
    }
}
