import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static final int DIVIDER = 10_007;
    static int n;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        dp = new int[n+2];
        dp[1] = 1;
        dp[2] = 3;
        for(int i=3;i<=n;i++){
            dp[i] = (dp[i-1] + dp[i-2]*2) % DIVIDER;
        }

        bw.write(Integer.toString(dp[n]));
        bw.flush();
    }
}
