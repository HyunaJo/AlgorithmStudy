import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] arr = new int[n];
		
		for(int i=0;i<n;i++) {
			arr[i] = scanner.nextInt();
		}
		Arrays.sort(arr);
		
		int m = scanner.nextInt();
		for(int i=0;i<m;i++) {
			System.out.println(findNum(arr, scanner.nextInt(), 0, n-1));
		}
		
	}
	
	public static int findNum(int[] arr, int key, int low, int high) {
		if(low<=high) {
			int mid = (low+high)/2;
			if(arr[mid]>key) {
				return findNum(arr, key, low, mid-1);
			}
			else if(arr[mid]<key) {
				return findNum(arr, key, mid+1, high);
			}
			else {
				return 1;
			}
		}
		return 0;
	}
}
