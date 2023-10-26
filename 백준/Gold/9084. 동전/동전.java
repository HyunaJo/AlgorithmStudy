import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static int T, N, M;
    public static int[] coins; // 동전 금액
    public static int[] dp; // dp[i] = 동전으로 dp[i] 만들 수 있는 경우의 수

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine()); // 테스트케이스 수
        while(T-->0){
            N = Integer.parseInt(br.readLine()); // 동전의 가지 수

            coins = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++){
                coins[i] = Integer.parseInt(st.nextToken());
            }

            M = Integer.parseInt(br.readLine()); // 만들어야 할 금액
            dp = new int[M+1]; // dp[i] = 동전 합이 i인 경우의 수
            dp[0] = 1;
            for(int i=0;i<N;i++){
                int coin = coins[i];
                for(int j=coin;j<=M;j++){
                    dp[j] += dp[j-coin];
                }
            }

            bw.write(Integer.toString(dp[M])+"\n");
        }
        bw.flush();

        br.close();
        bw.close();
    }
}