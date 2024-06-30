import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static int T, N;
    static long[] p;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine());

        p = new long[101];
        p[1] = 1;
        p[2] = 1;
        p[3] = 1;
        p[4] = 2;
        p[5] = 2;

        while(T-->0){
            N = Integer.parseInt(br.readLine());
            sb.append(findLength(N)).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }

    public static long findLength(int n){
        if(p[n] != 0){
            return p[n];
        }

        return p[n] = findLength(n-1) + findLength(n-5);
    }
}
