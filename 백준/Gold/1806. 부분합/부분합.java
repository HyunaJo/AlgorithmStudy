import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N,S;
    static int[] nums;
    static int min;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        min = Integer.MAX_VALUE;
        findMin();

        bw.write((min==Integer.MAX_VALUE)?"0":Integer.toString(min));
        bw.flush();

        br.close();
        bw.close();
    }

    public static void findMin(){
        int lp = 0;
        int rp = 0;
        int sum = nums[lp];

        while(lp<N){
            if(sum < S){
                if(++rp == N){
                    break;
                }
                sum += nums[rp];
                continue;
            }

            min = Math.min(min, rp-lp+1);
            if(lp == rp){
                sum -= nums[lp++];
                if(++rp == N){
                    break;
                }
                sum += nums[rp];
                continue;
            }

            sum -= nums[lp++];
        }

    }
}
