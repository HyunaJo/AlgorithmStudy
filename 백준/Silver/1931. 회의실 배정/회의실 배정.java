import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt();
		int[][] meetings = new int[N][2];
		
		for(int i=0;i<N;i++) {
			meetings[i][0] = scanner.nextInt();
			meetings[i][1] = scanner.nextInt();
		}
		Arrays.sort(meetings, new Comparator<int[]>() { // 끝나는 시간 기준으로 정렬
			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1]== o2[1]) // 끝나는 시간 같을 경우
					return o1[0]-o2[0]; // 시작하는 시간 비교
				else
					return o1[1]-o2[1];
			}
		});
		
		int[] meeting = meetings[0];
		int possibleCnt = 1; // 가능한 회의 개수
		for(int i=1;i<N;i++) {
			if(meeting[1] <= meetings[i][0]) { // 끝나는 시간 <= 다음 미팅 시작 시간
				meeting = meetings[i];
				possibleCnt++;
			}
		}
		System.out.println(possibleCnt);
	}
}
