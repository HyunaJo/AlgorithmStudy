import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static int N;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        int ans = 0;
        for(int i=0;i<=N;i++){
            int num = i;
            int sum = num;
            while(num > 0){
                sum += num%10;
                num /= 10;
            }

            if(sum == N){
                ans = i;
                break;
            }
        }

        bw.write(Integer.toString(ans));
        bw.flush();
    }
}
