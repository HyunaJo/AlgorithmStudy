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
        if(n > 1){
            dp[2] = 2;
            for(int i=3;i<=n;i++){
                dp[i] = dp[i-2]+dp[i-1];
                dp[i] %= 10_007;
            }
        }

        bw.write(Integer.toString(dp[n]));
        bw.flush();

        br.close();
        bw.close();
    }
}
