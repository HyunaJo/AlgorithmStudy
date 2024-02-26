import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static PriorityQueue<Work> pq;
	static class Work implements Comparable<Work>{
		int receivedTime;
		int score;
		int remainTime;
		
		public Work(int receivedTime, int score, int remainTime) {
			this.receivedTime = receivedTime;
			this.score = score;
			this.remainTime = remainTime;
		}
		
		@Override
		public int compareTo(Work o) {
			return o.receivedTime-this.receivedTime;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		pq = new PriorityQueue<>();
		Work nowWork = null; // 진행 중인 일
		int score = 0;
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			if(Integer.parseInt(st.nextToken()) == 0) {
				if(nowWork == null) {
					continue;
				}

				// 업무 주어지지 않은 경우 현재 업무 진행
				if(--nowWork.remainTime != 0) { // 아직 안 끝난 경우
					continue;
				}

				// 업무 끝난 경우
				score += nowWork.score; // 점수 더해주기
				if(!pq.isEmpty()) { // 밀린 일 있는 경우
					nowWork = pq.poll(); // 현재 일 변경
					continue;
				}

				nowWork = null; // 밀린 일 없는 경우
				continue;
			}

			int A = Integer.parseInt(st.nextToken()); // 점수
			int time = Integer.parseInt(st.nextToken()); // 걸리는 시간
			if(nowWork == null) { // 현재 일 없는 경우
				nowWork = new Work(i,A,time); // 현재 일로 설정
			}
			else { // 현재 일 있는 경우
				pq.add(nowWork); // 현재 일 큐에 넣고
				nowWork = new Work(i,A,time); // 지금 받은 일 하기
			}

			if(--nowWork.remainTime != 0) { // 아직 안 끝난 경우
				continue;
			}

			// 끝난 경우
			score += nowWork.score;
			if(!pq.isEmpty()) {
				nowWork = pq.poll();
			}
		}
		
		bw.write(Integer.toString(score));
		bw.flush();
		
		br.close();
		bw.close();
	}
}
