import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int N,K;
	static int[] results;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		results = new int[N];
		Deque<Integer> dq = new ArrayDeque<>();
		for(int i=1;i<=N;i++) {
			dq.add(i);
		}
		
		int tmp = 1;
		int idx = 0;
		while(!dq.isEmpty()) {
			int num = dq.poll();
			if(tmp++%K==0) {
				results[idx++] = num;
				continue;
			}
			dq.add(num);
		}
		
		bw.write(Arrays.toString(results).replace('[', '<').replace(']', '>'));
		bw.flush();
		
		br.close();
		bw.close();
	}

}
