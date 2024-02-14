import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N,r,c;
	static int ans = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		int size = (int)Math.pow(2, N);
		searchNum2(size, 0, 0);
		bw.write(Integer.toString(ans));
		bw.flush();
		
		br.close();
		bw.close();
	}
	
	public static void searchNum2(int size, int x, int y) {
		if(x==r&&y==c || size == 1) {
			return;
		}
			
		int halfN = size/2;
		
		if(r<x+halfN && c<y+halfN) { // 왼쪽 위
			searchNum2(halfN, x, y);
			return;
		}
		
		if(r<x+halfN && c>=y+halfN) { // 오른쪽 위
			ans += halfN*halfN;
			searchNum2(halfN, x, y+halfN);
			return;
		}
		
		if(r>=x+halfN && c<y+halfN) { // 왼쪽 아래
			ans += halfN*halfN*2;
			searchNum2(halfN, x+halfN, y);
			return;
		}
		
		
		if(r>=x+halfN && c>=y+halfN) { // 오른쪽 아래
			ans += halfN*halfN*3;
			searchNum2(halfN, x+halfN, y+halfN);
			return;
		}
	}
}
