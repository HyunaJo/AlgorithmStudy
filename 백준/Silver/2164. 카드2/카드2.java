import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
	static int N; // 1~N 카드
	static Deque<Integer> dq;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine()); // 카드 마지막 번호 입력받기
		dq = new ArrayDeque<>(); // 카드를 담을 덱
		for(int i=1;i<=N;i++) {
			dq.add(i);
		}
		
		while(dq.size()>1) {
			dq.poll(); // 제일 위에 있는 카드 버리기
			dq.add(dq.poll());
		}
		bw.write(Integer.toString(dq.poll()));
		bw.flush();
		
		br.close();
		bw.close();
	}

}
