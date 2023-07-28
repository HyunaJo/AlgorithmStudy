import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static final int MAX = 1_000_000_001;
    public static int N,M,K;
    public static int[][] dp;
    public static char[] ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // a 개수
        M = Integer.parseInt(st.nextToken()); // z 개수
        K = Integer.parseInt(st.nextToken()); // 사전에서 K번째 문자열

        ans = new char[N+M];
        dp = new int[N+1][M+1]; // a N개와 z M개로 만들 수 있는 문자열 개수
        for(int i=0;i<=N;i++){
            dp[i][0] = 1;
        }
        for(int i=0;i<=M;i++){
            dp[0][i] = 1;
        }
        for(int i=1;i<=N;i++){
            for(int j=1;j<=M;j++){
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                if (dp[i][j] > MAX) {
                    dp[i][j] = MAX;
                }
            }
        }

        if(dp[N][M] < K)
            bw.write("-1");
        else
            bw.write(findWord());

        bw.flush();

        br.close();
        bw.close();
    }

    public static String findWord(){
        String ans = "";
        int totalCnt = N+M;

        for(int i=0;i<totalCnt;i++){
            if(N==0){ // 더이상 a 안 넣는 경우
                ans += "z";
                M--;
                continue;
            }
            else if(M==0){ // 더이상 z 안 넣는 경우
                ans += "a";
                N--;
                continue;
            }

            if(dp[N-1][M] >= K){ // a로 시작하는 경우
                ans += "a";
                N--;
            }
            else{ // z로 시작하는 경우
                K -= dp[N-1][M];
                ans += "z";
                M--;
            }
        }

        return ans;
    }

}
