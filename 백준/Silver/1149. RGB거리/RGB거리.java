import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class Main {
	static final int R = 0;
	static final int G = 1;
	static final int B = 2;
	
	static int N;
	static int[][] prices;
	static int[][] dp;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		prices = new int[N][3];
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			prices[i][R] = Integer.parseInt(st.nextToken());
			prices[i][G] = Integer.parseInt(st.nextToken());
			prices[i][B] = Integer.parseInt(st.nextToken());
		}
		
		dp = new int[N][3];
		dp[0][R] = prices[0][R];
		dp[0][G] = prices[0][G];
		dp[0][B] = prices[0][B];
		
		for(int i=1;i<N;i++) {
			dp[i][R] = prices[i][R] + Math.min(dp[i-1][G], dp[i-1][B]);
			dp[i][G] = prices[i][G] + Math.min(dp[i-1][R], dp[i-1][B]);
			dp[i][B] = prices[i][B] + Math.min(dp[i-1][R], dp[i-1][G]);
		}
		
		for(int i=0;i<3;i++) {
			min = Math.min(min, dp[N-1][i]);
		}
		
		bw.write(Integer.toString(min));
		bw.flush();
		
		br.close();
		bw.close();
	}
}
