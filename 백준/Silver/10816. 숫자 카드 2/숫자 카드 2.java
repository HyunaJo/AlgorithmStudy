import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		HashMap<Long, Integer> hash = new HashMap<>();
		int i=0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(i=0;i<N;i++) {
			Long num = Long.parseLong(st.nextToken());
			if(hash.containsKey(num)) 
				hash.put(num,hash.get(num)+1);
			else
				hash.put(num, 1);
		}
		
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(i=0;i<M;i++) {
			Long findNum = Long.parseLong(st.nextToken());
			if(hash.containsKey(findNum))
				sb.append(hash.get(findNum)+" ");
			else
				sb.append("0 ");
		}
		System.out.println(sb.toString());
	}
}
