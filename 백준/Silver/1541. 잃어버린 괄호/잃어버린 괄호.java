import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

// 그리디 알고리즘 - 잃어버린 괄호
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String expression = br.readLine(); // 식
		String[] arr = expression.split(""); // 각각의 문자로 나눈 배열
		
		Deque<String> deq = new ArrayDeque<>();
		for(int i=0;i<arr.length;i++) {
			deq.add(arr[i]);
		}
		
		int num = 0;
		int sum = 0;
		int result = 0;
		for(int i=0;i<arr.length;i++) {
			try { // 맨 위 문자가 숫자인 경우
				num = num*10+Integer.parseInt(deq.peek());
				deq.removeFirst();
			}
			catch(Exception e){ // 에러난 경우 => +,-인 경우
				String operator = deq.removeFirst(); 
				sum += num;
				if(operator.equals("+")) {
					num = 0;
				}
				else if(operator.equals("-")) {
					deq.addLast(Integer.toString(sum));
					deq.addLast("-");
					num = 0;
					sum = 0;
				}
			}
		}
		
		sum+=num;
		
		if(deq.size()>0) { // -가 포함된 경우
			deq.add(Integer.toString(sum));
			// 최소값 구하기 시작
			result = Integer.parseInt(deq.removeFirst());
			num = 0;
			int i=1;
			while(deq.size()>0) {
				if(i%2==0)
					result-=Integer.parseInt(deq.removeFirst());
				else
					deq.removeFirst();
				i++;
			}
		}
		else {
			result = sum;
		}
		
		bw.write(Integer.toString(result));
		bw.flush();
	}
}
