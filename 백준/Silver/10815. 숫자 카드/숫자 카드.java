import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int i;
		
		int n = Integer.parseInt(br.readLine()); // 숫자 카드 개수
		int[] cards = new int[n]; // 가지고 있는 숫자 카드
		st = new StringTokenizer(br.readLine());
		for(i=0;i<n;i++)
			cards[i] = Integer.parseInt(st.nextToken());
		
		int m = Integer.parseInt(br.readLine()); // m개의 수
		int[] findNums = new int[m];
		st = new StringTokenizer(br.readLine());
		for(i=0;i<m;i++)
			findNums[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(cards);
		for(i=0;i<m;i++) {
			binarySearch(cards, n, findNums[i]);
		}
	}
	
	public static void binarySearch(int[] arr, int size, int findNum) {
		int start = 0;
		int end = size-1;
		int mid;
		boolean isExist = false; // 찾는 수 arr에 존재하는지
		
		while(start<=end) {
			mid = (start+end)/2;
			
			if(arr[mid]<findNum) // arr[mid] < 찾는 수 => mid값 커져야 함
				start = mid+1;
			else if(arr[mid]>findNum) // arr[mid] > 찾는 수 => mid값 작아져야 함
				end = mid-1;
			else {
				isExist = true;	
				break;
			}
		}
		
		if(isExist)
			System.out.println("1");
		else
			System.out.println("0");
	}
}
