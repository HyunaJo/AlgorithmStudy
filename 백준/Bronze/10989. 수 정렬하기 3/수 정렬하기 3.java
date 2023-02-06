import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine()); // 수의 개수
		int[] nums = new int[n];
		int i = 0;
		
		for(i=0;i<n;i++)
			nums[i] = Integer.parseInt(br.readLine());
		
		Arrays.sort(nums);
		for(i=0;i<n;i++)
			bw.write(nums[i]+"\n");
		
		bw.flush();
	}
}
