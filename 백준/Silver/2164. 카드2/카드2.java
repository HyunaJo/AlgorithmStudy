import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

// 자료 구조 - 카드2
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine()); // 1-n번 카드 존재
		
		if(n==1) // 카드가 1개인 경우에는 바로 1 출력
			bw.write("1");
		else { // 카드가 1개보다 많은 경우
			Deque<Integer> cards = new ArrayDeque<>(); // 1-n번 카드 담을 덱
			for(int i=1;i<=n;i++)
				cards.add(i); // 카드 번호순으로 담기
			
			while(true) {
				cards.removeFirst(); // 제일 위에 있는 카드 버리기
				
				if(cards.size()==1)
					break; // 카드 1장 남은 경우 break
				
				int top = cards.removeFirst(); // 1장 버린 후 제일 위에 있는 카드
				cards.add(top); // 제일 아래로 옮기기
			}
			
			bw.write(Integer.toString(cards.peek()));
		}
		
		bw.flush();
	}
}
