import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static int N; // 수열의 크기
    public static int[] nums; // 수열
    public static int[] sum; // 증가하는 부분 수열의 합

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        nums = new int[N];
        sum = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            nums[i] = Integer.parseInt(st.nextToken());
            sum[i] = nums[i];
        }

        for(int i=0;i<N-1;i++){
            int num = nums[i];

            for(int j=i;j<N;j++){
                if(num<nums[j]){
                    sum[j] = Math.max(sum[j], sum[i]+nums[j]);
                }
            }
        }

        int max = 0;
        for(int i=0;i<N;i++){
            max = Math.max(max, sum[i]);
        }

        bw.write(Integer.toString(max));
        bw.flush();

        br.close();
        bw.close();
    }
}