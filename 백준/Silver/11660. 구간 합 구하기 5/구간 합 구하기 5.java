import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N,M;
	static int[][] sums;
	static StringBuilder sb = new StringBuilder(); // 결과
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // NxN 크기의 표
		M = Integer.parseInt(st.nextToken()); // 구해야 하는 횟수
		
		sums = new int[N+1][N+1]; // (1,1)부터 해당 칸까지의 합을 담을 배열
		// 합 담을 배열 채우기
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				int num = Integer.parseInt(st.nextToken()); // 표에서의 (i,j) 값
				// (1,1) ~ (i,j) 구간 합 구하기
				sums[i][j] = num + sums[i-1][j] + sums[i][j-1] - sums[i-1][j-1];
			}
		}
		
		while(M-->0) { // M만큼 반복
			// (x1, y1),(x2, y2) 입력 받기
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int answer = sums[x2][y2] - sums[x1-1][y2] - sums[x2][y1-1] + sums[x1-1][y1-1];
			sb.append(answer).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}

}
