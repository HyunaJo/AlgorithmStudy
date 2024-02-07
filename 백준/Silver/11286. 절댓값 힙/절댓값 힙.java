import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class Main {
	static int N; // 연산 개수
	static PriorityQueue<Num> pq = new PriorityQueue<>();
	static StringBuilder sb = new StringBuilder();
	
	static class Num implements Comparable<Num>{
		int value;
		int absValue;
		
		public Num(int value) {
			this.value = value;
			this.absValue = Math.abs(value);
		}
		
		@Override
		public int compareTo(Num o) {
			if(this.absValue == o.absValue) {
				return this.value - o.value;
			}
			
			return this.absValue - o.absValue;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine()); // 연산 개수 입력받기
		while(N-->0) {
			int input = Integer.parseInt(br.readLine());
			
			if(input != 0) {
				pq.add(new Num(input));
				continue;
			}
			
			if(pq.isEmpty()) {
				sb.append("0\n");
				continue;
			}

			sb.append(pq.poll().value).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}

}
