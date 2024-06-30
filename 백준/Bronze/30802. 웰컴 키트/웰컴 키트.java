import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static final int TSHIRT_TYPE = 6;
    static int N, T, P;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] counts = new int[TSHIRT_TYPE];
        for(int i=0;i<TSHIRT_TYPE;i++){
            counts[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        int tshirtCnt = 0;
        for(int i=0;i<TSHIRT_TYPE;i++){
            tshirtCnt += counts[i]/T;
            if(counts[i]%T != 0){
                tshirtCnt++;
            }
        }

        sb.append(tshirtCnt).append("\n");
        sb.append(N/P).append(" ").append(N%P);
        bw.write(sb.toString());
        bw.flush();
    }
}
