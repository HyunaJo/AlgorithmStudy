import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int N = scanner.nextInt(); // 동전 총 N종류
		int K = scanner.nextInt(); // 가치 함 K
		
		int[] prices = new int[N];
		int i=0;
		for(i=0;i<prices.length;i++) {
			prices[i] = scanner.nextInt();
		}
		
		int cnt = 0;
		for(i=prices.length-1;i>=0;i--) { // 가치가 높은 동전부터
			int coin = prices[i];
			cnt += K/coin;
			K %= coin;
			
			if(K==0)
				break;
		}
		System.out.println(cnt);
	}
}
