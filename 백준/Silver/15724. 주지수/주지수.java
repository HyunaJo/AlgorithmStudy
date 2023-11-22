import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static int N, M, K, x1, x2, y1, y2;
    public static int[][] population;
    public static int[][] dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 세로
        M = Integer.parseInt(st.nextToken()); // 가로

        population = new int[N+1][M+1];
        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1;j<=M;j++){
                population[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[N+1][M+1]; // dp[i][j] = (1 1 i j)구역 내 살고 있는 사람 수
        for(int i=1;i<=N;i++){
            for(int j=1;j<=M;j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1] + population[i][j] - dp[i-1][j-1];
            }
        }

        K = Integer.parseInt(br.readLine());
        while(K-->0){
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());

            bw.write(Integer.toString(dp[x2][y2]-dp[x1-1][y2]-dp[x2][y1-1]+dp[x1-1][y1-1])+"\n");
        }
        bw.flush();

        br.close();
        bw.close();
    }
}