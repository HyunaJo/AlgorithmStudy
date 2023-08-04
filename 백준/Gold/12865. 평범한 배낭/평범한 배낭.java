import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main { // 평범한 배낭
    public static int N,K,W,V;
    public static int[] weights;
    public static int[] values;
    public static int[][] dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 물품의 수
        K = Integer.parseInt(st.nextToken()); // 버틸 수 있는 무게

        weights = new int[N+1];
        values = new int[N+1];
        dp = new int[N+1][K+1];
        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            weights[i] = Integer.parseInt(st.nextToken());
            values[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++)
        {
            for (int j = 0; j <= K; j++)
            {
                // 2-1. 갱신이 가능하면 둘 중 큰 값 넣어주기
                if (j - weights[i] >= 0) {
                    dp[i][j] = Math.max(
                            dp[i-1][j], dp[ i-1 ][ j - weights[i] ] + values[i] );
                }
                // 2-2. 아니면 원래 있던 값
                else{
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        bw.write(Integer.toString(dp[N][K]));
        bw.flush();

        br.close();
        bw.close();
    }
}
