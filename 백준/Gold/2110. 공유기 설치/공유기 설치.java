import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// 이분 탐색 - 공유기 설치
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 집의 개수
		int c = Integer.parseInt(st.nextToken()); // 공유기 개수
		
		int[] homeLocs = new int[n]; // 집의 좌표
		for(int i=0;i<n;i++)
			homeLocs[i] = Integer.parseInt(br.readLine());
		Arrays.sort(homeLocs); // 오름차순 정렬
		
		bw.write(Integer.toString(findMax(homeLocs, n, c)));
		bw.flush();
	}
	
	public static int findMax(int[] homeLocs, int n, int c) {
		int max = 0;
		int start = 1;
		int end = homeLocs[n-1] - homeLocs[0];
		int mid = 0;
		
		while(start<=end) {
			int routerCnt = 1;
			int checkHomeLoc = homeLocs[0];
			mid = (start+end)/2;
			for(int i=1;i<n;i++) { // 가능한 집에 공유기 설치해보기
				if(homeLocs[i]-checkHomeLoc>=mid) {
					routerCnt++;
					checkHomeLoc = homeLocs[i];
				}
			}
			
			if(routerCnt>=c) {
				max = mid;
				start = mid+1;
			}
			else
				end = mid-1;
		}
		
		return max;
	}
}
