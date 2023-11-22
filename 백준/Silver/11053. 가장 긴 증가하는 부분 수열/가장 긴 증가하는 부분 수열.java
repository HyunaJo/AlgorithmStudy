import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static int N; // 수열의 크기
    public static int[] nums; // 수열
    public static int[] arrCnt; // 부분 수열 길이

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        nums = new int[N];
        arrCnt = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            nums[i] = Integer.parseInt(st.nextToken());
            arrCnt[i] = 1;
        }

        for(int i=0;i<N-1;i++){
            int leastNum = nums[i];

            for(int j=i;j<N;j++){
                if(leastNum<nums[j]){ // 증가하는 수인 경우
                    arrCnt[j] = Math.max(arrCnt[i]+1, arrCnt[j]);
                }
            }
        }

        int ans = 0;
        for(int i=0;i<N;i++){
            ans = Math.max(ans, arrCnt[i]);
        }

        bw.write(Integer.toString(ans));
        bw.flush();

        br.close();
        bw.close();
    }
}