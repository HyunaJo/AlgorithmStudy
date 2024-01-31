import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Solution {
	static int T, N;
	static int[][] snail;
	static StringBuilder sb = new StringBuilder();
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수
		for(int t=1;t<=T;t++) {
			N = Integer.parseInt(br.readLine()); // 달팽이 크기
			snail = new int[N][N];
			fillSnail();
			
			sb.append("#").append(t).append("\n");
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					sb.append(snail[i][j]).append(" ");
				}
				sb.append("\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	public static void fillSnail() {
		int x = 0;
		int y = -1;
		int movingType = 0;
		int num = 1; // snail에 넣어줄 숫자
		int remainCnt = N*N; // snail에서 채워줘야할 칸 수
		
		
		while(remainCnt>0) {
			int nx = x+dx[movingType];
			int ny = y+dy[movingType];
			
			if(nx<0 || nx>=N || ny<0 || ny>=N) {
				movingType = (movingType+1)%4;
				continue;
			}
			
			if(snail[nx][ny]!=0) {
				movingType = (movingType+1)%4;
				continue;
			}
			
			snail[nx][ny] = num++;
			remainCnt--;
			x = nx;
			y = ny;
		}
	}
}
