import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static int N, S;
	public static int[] arr;
	public static int ans = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		findAnswer();
		
		if(ans == Integer.MAX_VALUE)
			ans = 0;
		bw.write(Integer.toString(ans));
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	public static void findAnswer() {
		int lp = 0; // 왼쪽 포인터
		int rp = 0; // 오른쪽 포인터
		long sum = arr[0];
		
		while(true) {
			if(sum < S) { // 합이  S보다 작은 경우
				rp++; // 오른쪽 포인터 오른쪽으로 이동
				if(rp==N)
					return;
				sum += arr[rp];
			}
			else { // 합이 S이상인 경우
				if((rp-lp+1)<ans)
					ans = rp-lp+1;
				
				if(lp==rp) {
					sum -= arr[lp];
					lp++;
					rp++;
					if(rp==N)
						return;
					sum += arr[rp];
				}
				else {
					sum -= arr[lp];
					lp++;
				}
			}
		}
		
	}
}
