import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 그리디 알고리즘 - 1789 (수들의 합)
public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		long s = Long.parseLong(br.readLine()); // 서로 다른 n개의 자연수 합 = s 
		long num = 1; // 제일 작은 수부터 
		while(s>=num) {
			s -= num; 
			num++;
		}
		
		bw.write(Long.toString(num-1));
		bw.flush();
	}
}
