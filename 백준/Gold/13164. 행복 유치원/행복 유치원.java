import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static int N, K;
    public static int[] heights;
    public static int[] gap;
    public static int ans = 0;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 원생의 수
        K = Integer.parseInt(st.nextToken()); // 조의 개수

        heights = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            heights[i] = Integer.parseInt(st.nextToken());
        }

        gap = new int[N-1];
        for(int i=1;i<N;i++){
            gap[i-1] = heights[i] - heights[i-1];
        }

        Arrays.sort(gap); // 오름차순 정렬
        for(int i=0;i<N-K;i++){
            ans += gap[i];
        }
        bw.write(Integer.toString(ans));
        bw.flush();
        
        br.close();
        bw.close();
    }
}
