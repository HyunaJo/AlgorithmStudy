import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] nums;
	static boolean[] visited;
	static ArrayList<String> ansArr = new ArrayList<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		nums = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(nums);
		visited = new boolean[N];
		for(int i=0;i<N;i++) {
			visited[i] = true;
			makeArr(i, 1, Integer.toString(nums[i]));
			visited[i] = false;
		}
		
		for(int i=0;i<ansArr.size();i++) {
			bw.write(ansArr.get(i)+"\n");
		}
		bw.flush();
		
		br.close();
		bw.close();
		
	}
	
	public static void makeArr(int idx, int cnt, String str) {
		if(cnt == M) {
			ansArr.add(str);
			return;
		}
		
		for(int i=0;i<N;i++) {
			if(visited[i]) {
				continue;
			}
			
			visited[i] = true;
			makeArr(i, cnt+1, str+" "+nums[i]);
			visited[i] = false;
		}
		
	}

}
