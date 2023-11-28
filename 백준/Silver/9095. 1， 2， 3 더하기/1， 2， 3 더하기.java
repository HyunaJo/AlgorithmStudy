import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static int T, n;
    public static int[] dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());
        while(T-->0){
            n = Integer.parseInt(br.readLine());

            dp = new int[n+3];
            dp[1] = 1;
            dp[2] = 2;
            dp[3] = 4;
            for(int i=4;i<=n;i++){
                dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
            }

            bw.write(dp[n]+"\n");
        }
        bw.flush();

        br.close();
        bw.close();
    }
}
