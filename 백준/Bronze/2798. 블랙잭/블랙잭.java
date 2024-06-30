import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] cards;
    static int max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cards = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            cards[i] = Integer.parseInt(st.nextToken());
        }

        makeCombination(0,0,0);
        bw.write(Integer.toString(max));
        bw.flush();
    }

    public static void makeCombination(int idx, int sum, int cnt){
        if(cnt == 3){
            max = Math.max(max, sum);
            return;
        }

        for(int i=idx;i<N;i++){
            if(sum+cards[i] <= M){
                makeCombination(i+1, sum+cards[i], cnt+1);
            }
        }
    }
}
