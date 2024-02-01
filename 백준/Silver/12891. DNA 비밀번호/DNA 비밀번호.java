import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static HashMap<Character, Integer> dnaMap = new HashMap<>();
	static int dnaCharCnt;
	static int S,P;
	static int[] cnts; // 부분 문자열에 포함되어야 할 각 문자의 최소 개수들 -> 사용하면 --;
	static StringBuilder sb = new StringBuilder(); // 결과
	
	public static void main(String[] args) throws Exception{
		// DNA 기본 문자 설정
		dnaMap.put('A', 0);
		dnaMap.put('C', 1);
		dnaMap.put('G', 2);
		dnaMap.put('T', 3);
		dnaCharCnt = dnaMap.keySet().size(); // DNA 기본 문자 총개수
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken()); // DNA 문자열 길이
		P = Integer.parseInt(st.nextToken()); // 비밀번호 문자열 길이
		
		String input = br.readLine(); // 임의의 DNA 문자열 입력받기
		
		// 부분 문자열에 포함되어야 할 각 문자의 최소 개수 입력받기
		cnts = new int[dnaCharCnt];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<dnaCharCnt;i++) {
			cnts[i] = Integer.parseInt(st.nextToken());
		}
		
		// 결과 생성 및 출력
		bw.write(Integer.toString(countPossible(input)));
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	public static int countPossible(String input) {
		int possibleCnt = 0;
		
		for(int i=0;i<P;i++) {
			char ch = input.charAt(i);
			cnts[dnaMap.get(ch)]--;
		}
		
		if(checkValid()) {
			possibleCnt++;
		}
		
		int lastIdx = S-P; // 가능한 마지막 부분문자열의 시작 인덱스
		for(int i=1;i<=lastIdx;i++) {
			cnts[dnaMap.get(input.charAt(i-1))]++;
			cnts[dnaMap.get(input.charAt(i+P-1))]--;
			
			if(checkValid()) {
				possibleCnt++;
			}
		}
		
		return possibleCnt;
	}
	
	public static boolean checkValid(){
		for(int i=0;i<dnaCharCnt;i++) {
			if(cnts[i]>0) {
				return false;
			}
		}
		
		return true;
	}
}
