import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        int cnt = 1;
        while(true){
            if(B%10==1)
                B = (B-1)/10;
            else if((B%10)%2==0)
                B /= 2;
            else {
                bw.write("-1");
                break;
            }
            cnt++;

            if(B<A){
                bw.write("-1");
                break;
            }
            else if(B==A){
                bw.write(Integer.toString(cnt));
                break;
            }
        }

        bw.flush();
    }
}
