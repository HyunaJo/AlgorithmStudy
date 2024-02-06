import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N,M,R;
	static int[][] arr;
	static int[][] next = {{0,1},{1,0},{0,-1},{-1,0}};
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int borderCnt = Math.min(N, M)/2;
		while(R-->0) {
			for(int i=0;i<borderCnt;i++) {
				rotateBorder(i);
			}
		}
		
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
	}
	
	public static void rotateBorder(int start) {
		int tmp = arr[start][start];
		int endCol = M-start-1;
		int endRow = N-start-1;
		
		int x = start;
		int y = start;
		for(int i=0;i<4;i++) {
			while(true) {
				int nx = x + next[i][0];
				int ny = y + next[i][1];
				
				if(nx<start || nx>endRow || ny<start || ny>endCol) {
					break;
				}
				
				if(nx==start && ny==start) {
					break;
				}
				
				arr[x][y] = arr[nx][ny];
				x = nx;
				y = ny;
			}
		}
		arr[x][y] = tmp;
	}
}
