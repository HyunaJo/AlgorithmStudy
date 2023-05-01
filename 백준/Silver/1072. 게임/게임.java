import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        long X = Long.parseLong(st.nextToken());
        long Y = Long.parseLong(st.nextToken());
        long Z = (Y*100)/X;

        if(Z>=99){
            bw.write("-1");
        }
        else{
            long start = 1;
            long end = X;

            while(start<=end){
                long mid = (start+end)/2;
                long newZ = (mid+Y)*100/(mid+X);
                if(newZ>Z){
                    end = mid - 1;
                }
                else
                    start = mid + 1;
            }
            bw.write(Long.toString(start));
        }
        bw.flush();
    }
}
