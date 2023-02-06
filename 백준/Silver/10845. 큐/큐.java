import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<Integer> queue = new LinkedList();
		
		int n = Integer.parseInt(br.readLine()); // 명령의 수
		int last = -1; // 큐의 마지막 정수
		for(int i=0;i<n;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
		
			try {
				switch(command) {
				case "push":
					last = Integer.parseInt(st.nextToken());
					queue.add(last);
					break;
				case "pop":
					if(queue.isEmpty())
						System.out.println("-1");
					else
						System.out.println(queue.poll());
					break;
				case "size":
					System.out.println(queue.size());
					break;
				case "empty":
					if(queue.isEmpty())
						System.out.println("1");
					else
						System.out.println("0");
					break;
				case "front":
					if(queue.isEmpty())
						System.out.println("-1");
					else
						System.out.println(queue.peek());
					break;
				case "back":
					if(queue.isEmpty())
						System.out.println("-1");
					else
						System.out.println(last);
					break;
				}
			}
			catch(Exception e) {
				System.out.println("-1");
			}
		}
	}
}
