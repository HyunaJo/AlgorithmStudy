import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N; // 영상 크기
	static int[][] video;
	static StringBuilder sb = new StringBuilder(); // 결과
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine()); // 영상 크기 입력받기
		video = new int[N][N];
		for(int i=0;i<N;i++) {
			String input = br.readLine();
			for(int j=0;j<N;j++) {
				video[i][j] = input.charAt(j)-'0';
			}
		}

		if(isAllSame(N, 0, 0)) {
			bw.write(Integer.toString(video[0][0]));
			bw.flush();
			return;
		}
		
		compressVideo(N, 0, 0);
		bw.write(sb.toString());
		bw.flush();
	}
	
	public static void compressVideo(int size, int x, int y) {
		if(size == 1) {
			sb.append(video[x][y]);
			return;
		}
		
		sb.append("(");
		
		int halfN = size/2;
		if(!isAllSame(halfN, x, y)) {
			compressVideo(halfN, x, y);
		}
		
		if(!isAllSame(halfN, x, y+halfN)) {
			compressVideo(halfN, x, y+halfN);
		}
		
		if(!isAllSame(halfN, x+halfN, y)) {
			compressVideo(halfN, x+halfN, y);
		}
		
		if(!isAllSame(halfN, x+halfN, y+halfN)) {
			compressVideo(halfN, x+halfN, y+halfN);
		}
		
		sb.append(")");
	}
	
	public static boolean isAllSame(int size, int x, int y) {
		int value = video[x][y];
		
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				if(value != video[x+i][y+j]) {
					return false;
				}
			}
		}
		
		sb.append(value);
		return true;
	}
}
