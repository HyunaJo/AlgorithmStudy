import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int N;
	static int[] possibleNums = {1,2,3,5,7,9};
	static StringBuilder sb = new StringBuilder(); // 결과
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine()); // N자리수
		findPrime(0,0); // 신기한 소수 찾기
		// 결과 출력
		bw.write(sb.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	public static void findPrime(int digit, int num) {
		if(digit == N) { // N자리수인 경우
			sb.append(num+"\n"); // 담기
			return;
		}
		
		for(int i:possibleNums) { // 1~9 뒤에 붙이기
			int newNum = num*10+i; // 새로운 수
			
			if(isNotPrime(newNum)) { // 소수가 아니면 true
				continue;
			}
			
			findPrime(digit+1, newNum); // 다음 자리 붙이러 가기
		}
	}
	
	public static boolean isNotPrime(int num) {
		if(num==1) {
			return true; // 소수가 아니면 true
		}
		
		int maxMultiplyNum = (int)Math.sqrt(num);
		for(int i=2;i<=maxMultiplyNum;i++) {
			if(num%i==0) {
				return true; // 소수가 아니면 true
			}
		}
		
		return false; // 소수이면 false
	}

}
