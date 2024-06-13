import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static int T;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());
        while(T-->0){
            int n = Integer.parseInt(br.readLine());

            int[][] scores = new int[2][n+1];
            for(int i=0;i<2;i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=1;j<=n;j++){
                    scores[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[][] dp = new int[2][n+1];
            for(int i=1;i<=n;i++){
                dp[0][i] = Math.max(dp[1][i-1]+scores[0][i],dp[0][i-1]);
                dp[1][i] = Math.max(dp[0][i-1]+scores[1][i],dp[1][i-1]);
            }

            sb.append(Math.max(dp[0][n],dp[1][n])+"\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }
}
