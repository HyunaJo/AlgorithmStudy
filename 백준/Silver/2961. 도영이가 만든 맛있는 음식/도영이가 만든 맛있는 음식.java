import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N; // 재료 개수
	static Ingredient[] ingredients; // 재료 정보
	static Ingredient[] selecteds; // 선택된 재료 정보
	static StringBuilder sb = new StringBuilder(); // 결과
	static Long minDiff = Long.MAX_VALUE; // 쓴맛과 신맛의 최소 차이
	
	static class Ingredient{ // 재료 클래스
		int s; // 신맛
		int b; // 쓴맛
		public Ingredient(int s, int b) {
			this.s = s;
			this.b = b;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine()); // 재료 개수 입력받기
		
		// 재료 정보 입력받기
		ingredients = new Ingredient[N];
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()); // 쓴맛, 신맛 입력받기
			ingredients[i] = new Ingredient(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
	
		// 쓴맛과 신맛의 최소 차이 구하기
		selecteds = new Ingredient[N];
		for(int i=0;i<N;i++) {
			selecteds[0] = ingredients[i];
			findMinDiff(1, i+1);
			
			if(minDiff == 0) {
				break;
			}
		}
		bw.write(Long.toString(minDiff));
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	public static void findMinDiff(int selectedCnt, int idx) {
		if(idx == N) {
			long sourSum = 1; // 선택된 재료들의 신맛의 최종값
			long bitterSum = 0; // 선택된 재료들의 쓴맛의 최종값
			for(int i=0;i<selectedCnt;i++) {
				Ingredient selected = selecteds[i];
				sourSum *= selected.s;
				bitterSum += selected.b;
			}
			
			minDiff = Math.min(minDiff, Math.abs(sourSum-bitterSum));
			return;
		}
		
		findMinDiff(selectedCnt, idx+1);
		selecteds[selectedCnt] = ingredients[idx];
		findMinDiff(selectedCnt+1, idx+1);
	}
}
