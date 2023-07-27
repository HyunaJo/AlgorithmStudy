import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static boolean[] nums;
    public static int N, K;
    public static int ans;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 삭제 유무 => true:삭제
        nums = new boolean[N+1];

        int removePrime = 2;
        while(K>0){
            if(isPrime(removePrime)){
                for(int i=1;i<=N/removePrime;i++){
                    if(!nums[i*removePrime]){
                        nums[i*removePrime] = true;
                        ans = i*removePrime;
                        K--;
                        if(K==0)
                            break;
                    }
                }
            }
            removePrime++;
        }
        bw.write(Integer.toString(ans));

        bw.flush();

        br.close();
        bw.close();

    }

    public static boolean isPrime(int num){
        if(num == 2)
            return true;

        for(int i=3;i<=Math.sqrt(num);i+=2){
            if(num%i==0)
                return false;
        }
        return true;
    }
}
