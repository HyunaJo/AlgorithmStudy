import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static long A;
    static int B,C;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Long.parseLong(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        bw.write(Long.toString(getAns(A,B)));
        bw.flush();
    }

    public static long getAns(long x, int n){
        if(n == 1){
            return x%C;
        }

        long half = getAns(x,n/2);
        if(n%2 == 1){
            return (half*half%C)*x%C;
        }
        return half*half%C;
    }
}
