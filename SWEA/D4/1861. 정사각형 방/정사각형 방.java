import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int T,N;
	static int[][] rooms; // 방 번호
	static int[][] movingCnt; // 이동할 수 있는 방의 개수
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine()); // 테스트케이스 캐수
		for(int t=1;t<=T;t++) {
			N = Integer.parseInt(br.readLine()); // 방 크기
			
			// 방 번호 입력 받기
			rooms = new int[N][N];
			for(int i=0;i<N;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					rooms[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			movingCnt = new int[N][N]; // 이동할 수 있는 방의 개수
			
			// 이동할 수 있는 방 최대 개수와 해당 방 번호 구하기
			int max = 1; // 이동할 수 있는 방 최대 개수
			int roomNum = Integer.MAX_VALUE; // 이동할 수 있는 개수가 최대인 방 번호
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					movingCnt[i][j] = moveRoom(i,j);
					if(max < movingCnt[i][j]) { // 최대보다 큰 경우
						max = movingCnt[i][j]; // 최대 변경
						roomNum = rooms[i][j]; // 방 번호 변경
						continue;
					}
					
					if(max==movingCnt[i][j] && roomNum>rooms[i][j]) { // 최대와 이동횟수 같고 방 번호 작은 경우
						roomNum = rooms[i][j]; // 방 번호 변경
					}
				}
			}
			// 결과 작성
			sb.append("#").append(t).append(" ").append(roomNum).append(" ").append(max-1).append("\n");
		}
		// 결과 출력
		bw.write(sb.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	public static int moveRoom(int x, int y) {
		if(movingCnt[x][y] != 0) { // 이미 이동횟수 구한 방인 경우
			return movingCnt[x][y];
		}
		
		int max = 1; // 최대 이동 횟수
		for(int i=0;i<4;i++) {
			// 다음 위치
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx<0 || nx>=N || ny<0 || ny>=N) { // 좌표 밖인 경우
				continue;
			}
			
			if(rooms[nx][ny] != rooms[x][y]+1) { // (현재 방 번호 + 1)이 아닌 경우
				continue;
			}
			
			
			max = Math.max(max, moveRoom(nx, ny)); // 최대 이동 횟수 구하기
		}
		movingCnt[x][y] = max; // 최대 이동 횟수 저장
		return max+1; // (최대 이동 횟수 + 1) 리턴
	}

}
