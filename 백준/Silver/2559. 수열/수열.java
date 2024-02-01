import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] temperatures;
    static int max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        temperatures = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            temperatures[i] = Integer.parseInt(st.nextToken());
        }
        findMax();
        bw.write(Integer.toString(max));
        bw.flush();

        br.close();
        bw.close();
    }

    public static void findMax(){
        int sum = 0;
        for(int i=0;i<K;i++){
            sum += temperatures[i];
        }
        max = sum;

        for(int i=1;i<=N-K;i++){
            sum -= temperatures[i-1];
            sum += temperatures[i+K-1];
            max = Math.max(max, sum);
        }
    }
}
