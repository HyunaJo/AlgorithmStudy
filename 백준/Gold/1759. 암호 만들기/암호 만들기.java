import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int L,C;
	static char[] chars; 
	static char[] password;
	static StringBuilder sb = new StringBuilder();
	

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken()); // 암호는 서로 다른 L개의 알파벳
		C = Integer.parseInt(st.nextToken()); // 사용했을 법한 문자 종류 수
		
		// 사용했을 법한 문자 종류 입력 받기
		st = new StringTokenizer(br.readLine());
		chars = new char[C];
		for(int i=0;i<C;i++) {
			char ch = st.nextToken().charAt(0);
			chars[i] = ch;
		}
		
		Arrays.sort(chars);
		password = new char[L];
		findPassword(0, 0, 0);
		bw.write(sb.toString());
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	public static void findPassword(int idx, int mCnt, int jCnt) {
		if(mCnt+jCnt == L) {
			if(mCnt<1 || jCnt<2) {
				return;
			}
			
			for(char passwordChar:password) {
				sb.append(passwordChar);
			}
			sb.append("\n");
			
			return;
		}
		
		for(int i=idx;i<C;i++) {
			char ch = chars[i];
			password[mCnt+jCnt] = ch;
			
			if(ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u') { // 모음인 경우
				findPassword(i+1, mCnt+1, jCnt);
				continue;
			}
			
			findPassword(i+1, mCnt, jCnt+1);
		}
	}

}
