import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static final int PAPER_WIDTH = 100; // 도화지 크기
	static final int COLOR_PAPER_WIDTH = 10; // 색종이 크기
	static int N;
	static int[][] paper = new int[PAPER_WIDTH+1][PAPER_WIDTH+1]; // 도화지
	static int ans = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine()); // 색종이의 수
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken()); // 색종이의 맨왼쪽 y좌표
			int bottom = PAPER_WIDTH-Integer.parseInt(st.nextToken()); // 색종이의 맨아래 x좌표
			
			paper[bottom][left] += 1;
			paper[bottom-COLOR_PAPER_WIDTH][left] -= 1;
			paper[bottom][left+COLOR_PAPER_WIDTH] -= 1;
			paper[bottom-COLOR_PAPER_WIDTH][left+COLOR_PAPER_WIDTH] += 1;
		}
		
		// 구간합
		// 왼쪽칸을 현재 칸에 더해주기
		for(int i=0;i<PAPER_WIDTH;i++) {
			for(int j=1;j<PAPER_WIDTH;j++) {
				paper[i][j]+= paper[i][j-1]; 
			}
		}
		// 아래칸을 현재 칸에 더해주기
		for(int j=0;j<PAPER_WIDTH;j++) {
			for(int i=PAPER_WIDTH-1;i>=0;i--) {
				paper[i][j] += paper[i+1][j];
			}
		}
		
		// 색종이 있는 구간 개수 구하기
		for(int i=1;i<PAPER_WIDTH;i++) {
			for(int j=0;j<PAPER_WIDTH;j++) {
				if(paper[i][j]>0) {
					ans++;
				}
				
			}
		}
		
		bw.write(Integer.toString(ans));
		bw.flush();
		
		br.close();
		bw.close();
	}

}
