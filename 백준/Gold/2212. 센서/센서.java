import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static int N, K;
    public static int[] sensors;
    public static int[] gap;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine()); // 센서의 개수
        K = Integer.parseInt(br.readLine()); // 집중국의 개수

        sensors = new int[N];
        gap = new int[N-1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            sensors[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(sensors); // 센서 위치 오름차순 정렬
        for(int i=0;i<N-1;i++){
            gap[i] = sensors[i+1]-sensors[i];
        }

        Arrays.sort(gap);
        int ans = 0;
        for(int i=0;i<N-K;i++){
            ans += gap[i];
        }

        bw.write(Integer.toString(ans));
        bw.flush();

        br.close();
        bw.close();
    }
}
