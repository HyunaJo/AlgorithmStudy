import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N,M;
	static int[] sums;
	static StringBuilder sb = new StringBuilder(); // 결과
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 수의 개수
		M = Integer.parseInt(st.nextToken()); // 합을 구해야 하는 횟수
		
		sums = new int[N+1]; // 1번부터의 해당 인덱스까지의 합을 담을 배열
		// 합 담을 배열 채우기
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			sums[i] = sums[i-1] + Integer.parseInt(st.nextToken()); // 1~i까지의 합
		}
		
		while(M-->0) { // M만큼 반복
			// 합을 구해야하는 구간 입력받기
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken()); // 구간 시작점
			int j = Integer.parseInt(st.nextToken()); // 구간 끝점
			
			sb.append(sums[j]-sums[i-1]).append("\n"); // 구간합 작성
		}
		// 결과 출력
		bw.write(sb.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}

}
