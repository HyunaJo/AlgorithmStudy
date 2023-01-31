import java.util.Arrays;
import java.util.Scanner;

// 이분 탐색 - 랜선 자르기
public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int K = scanner.nextInt(); // 이미 가지고 있는 랜선의 개수
		int N = scanner.nextInt(); // 필요한 랜선의 개수
		
		int[] lines = new int[K];
		for(int i=0;i<K;i++)
			lines[i] = scanner.nextInt();
		Arrays.sort(lines);
		
		findMax(lines, K, N);
	}
	
	public static void findMax(int[] lines, int K, int N) {
		long start = 0;
		long end = (long)lines[K-1]+1; // 자를 수 있는 최대 길이 + 1
		long mid = (start+end)/2;
		int newLineCnt = 0; // 잘라서 만들어낸 랜선 개수
		int i=0;
		while(start+1<end) {
			newLineCnt = 0;
			for(i=0;i<K;i++) {
				newLineCnt += lines[i]/mid;
			}
			
			if(newLineCnt<N) // 얻은 랜선 개수 < 필요한 랜 개수 => 더 짧게 잘라야 
				end = mid;
			else // 얻은 랜선 개수 >= 필요한 랜선 개수 => 더 길게 잘라도 됨
				start = mid;
			mid = (start+end)/2;
		}
		System.out.println(start);
	}
}
