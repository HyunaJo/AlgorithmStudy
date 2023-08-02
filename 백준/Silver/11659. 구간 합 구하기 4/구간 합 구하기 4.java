import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static int N,M;
    public static int[] sum;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        sum = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) {
            sum[i] = sum[i-1]+Integer.parseInt(st.nextToken()); // 0~i까지의 합
        }

        while(M-->0){
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            bw.write(Integer.toString(sum[j]-sum[i-1])+"\n");
        }

        bw.flush();

        br.close();
        bw.close();
    }
}
