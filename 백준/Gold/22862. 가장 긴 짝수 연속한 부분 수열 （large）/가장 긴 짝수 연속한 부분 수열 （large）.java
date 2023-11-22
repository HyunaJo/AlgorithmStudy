import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static int N, K;
    public static int[] nums;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        nums = new int[N];
        for(int i=0;i<N;i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        int removedCnt = 0;
        int maxLength = 0;
        while(end<N){
            if(removedCnt<=K){
                if(nums[end]%2 == 1){
                    removedCnt++;
                }
                if(removedCnt > K){
                    end++;
                    continue;
                }
                maxLength = Math.max(maxLength, end-start+1-removedCnt);
                end++;
            }
            else{
                if(nums[start]%2 == 1){
                    removedCnt--;
                }
                start++;

            }

        }

        bw.write(Integer.toString(maxLength));
        bw.flush();

        br.close();
        bw.close();
    }
}
