import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int n = scanner.nextInt();
		int i;
		Stack<Integer> stack = new Stack<Integer>();
		
		for(i=0;i<n;i++) {
			int num = scanner.nextInt();
			if(num==0)
				stack.pop();
			else
				stack.push(num);
		}
		
		int total = 0;
		while(stack.size()!=0) {
			total += stack.pop();
		}
		System.out.println(total);
	}
}
