import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int N; // 탑 개수
	static Tower[] towers; // 탑 정보 저장할 배열
	static int[] touchedTowers; // 수신하는 탑의 번호 저장할 배열
	static StringBuilder sb = new StringBuilder(); // 결과
	
	static class Tower{ // 탑 정보를 담을 객체
		int num; // 탑의 번호
		int height; // 탑의 높이
		public Tower(int num, int height) {
			this.num = num;
			this.height = height;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine()); // 탑 개수 입력받기
		
		// 탑 정보 입력받기
		StringTokenizer st = new StringTokenizer(br.readLine());
		towers = new Tower[N+1];
		for(int i=1;i<=N;i++) {
			towers[i] = new Tower(i, Integer.parseInt(st.nextToken()));
		}
		
		// 수신하는 탑 찾아내기
		Deque<Tower> dq = new ArrayDeque<>();
		touchedTowers = new int[N+1];
		for(int i=N;i>0;i--) {
			Tower left = towers[i];
			while(!dq.isEmpty()) {
				if(dq.peekLast().height > left.height) {
					break;
				}
				touchedTowers[dq.pollLast().num] = left.num;
			}
			dq.addLast(left);
		}
		
		// 결과 출력
		for(int i=1;i<=N;i++) {
			sb.append(touchedTowers[i]).append(" ");
		}
		bw.write(sb.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}

}
