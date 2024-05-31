import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] order;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        order = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++){
            int leftCnt = Integer.parseInt(st.nextToken());

            for(int j=1;j<=N;j++){
                if(leftCnt == 0 && order[j] == 0){
                    order[j] = i;
                    break;
                }

                if(order[j] == 0){
                    leftCnt--;
                }
            }
        }

        for(int i=1;i<=N;i++){
            bw.write(order[i]+" ");
        }
        bw.flush();

    }
}
