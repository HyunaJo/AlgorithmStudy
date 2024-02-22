import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;


public class Main {
	static int N,M;
	static ArrayList<Integer>[] links;
	static int[] tallCnt;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		links = new ArrayList[N+1];
		for(int i=1;i<=N;i++) {
			links[i] = new ArrayList<>();
		}
		
		tallCnt = new int[N+1];
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int front = Integer.parseInt(st.nextToken());
			int back = Integer.parseInt(st.nextToken());
			
			links[front].add(back);
			tallCnt[back]++;
		}

		findResult();
		
		bw.write(sb.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	public static void findResult() {
		Deque<Integer> dq = new ArrayDeque<>();
		for(int i=1;i<=N;i++) {
			if(tallCnt[i]==0) {
				dq.add(i);
			}
		}
		
		while(!dq.isEmpty()) {
			int now = dq.poll();
			sb.append(now).append(" ");
			
			for(int next:links[now]) {
				tallCnt[next]--;
				
				if(tallCnt[next] == 0) {
					dq.add(next);
				}
			}
		}
	}

}
