import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static int N,d,k,c;
	static int[] sushi;
	static int maxCnt;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 접시 수
		d = Integer.parseInt(st.nextToken()); // 초밥 가짓수
		k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
		c = Integer.parseInt(st.nextToken()); // 쿠폰 번호
		
		sushi = new int[N];
		for(int i=0;i<N;i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}
		
		findMax();
		bw.write(Integer.toString(maxCnt));
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	public static void findMax() {
		int left = 0;
		int right = k-1;
		
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(c, 1);
		for(int i=0;i<k;i++) {
			map.put(sushi[i], map.getOrDefault(sushi[i], 0)+1);
		}
		
		maxCnt = map.keySet().size();
		while(left<N) {
			int leftSushi = sushi[left];
			map.put(leftSushi, map.get(leftSushi)-1);
			if(map.get(leftSushi)==0) {
				map.remove(leftSushi);
			}
			
			left++;
			right = (right+1)%N;
			
			map.put(sushi[right], map.getOrDefault(sushi[right], 0)+1);
			maxCnt = Math.max(maxCnt, map.keySet().size());
		}
	}

}
