import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static int n; // 계단 개수
    public static int[] scores; // 계단 점수
    public static int[] sum;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        scores = new int[n+3]; // 0,1,2,3은 기본적으로 갖도록 함
        sum = new int[n+3];
        for(int i=1;i<=n;i++){
            scores[i] = Integer.parseInt(br.readLine());
        }

        sum[1] = scores[1];
        sum[2] = scores[1]+scores[2];
        sum[3] = Math.max(scores[1],scores[2])+scores[3];

        for(int i=4;i<=n;i++){
            sum[i] = Math.max(sum[i-3]+scores[i-1],sum[i-2])+scores[i];
        }

        bw.write(Integer.toString(sum[n]));
        bw.flush();

        br.close();
        bw.close();
    }
}
