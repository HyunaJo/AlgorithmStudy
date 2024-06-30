import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        int ans = 0;
        for(int i=5;i<=N;i++){
            int num = i;
            while(num%5 == 0){
                ans++;
                num /= 5;
            }
        }
        bw.write(Integer.toString(ans));
        bw.flush();
    }
}
