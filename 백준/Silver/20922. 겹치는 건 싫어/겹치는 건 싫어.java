import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static int N, K;
    public static int[] nums;
    public static Map<Integer, Integer> map = new HashMap<>(); // 숫자 포함 횟수 저장할 map
    public static int maxLength = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        if (N != 1) {
            findMaxLength();
        }
        bw.write(Integer.toString(maxLength));
        bw.flush();

        br.close();
        bw.close();
    }

    public static void findMaxLength() {
        int start = 0;
        int end = 0;

        while (end < N) {
            int endNum = nums[end];
            int endNumCnt = map.getOrDefault(endNum, 0);
            map.put(endNum, endNumCnt + 1);

            if (endNumCnt < K) {
                maxLength = Math.max(maxLength, end-start+1);

                end++;
                continue;
            }

            while(true){
                int startNum = nums[start];
                int startNumCnt = map.get(startNum);
                map.put(startNum, startNumCnt - 1);

                start++;

                if(startNum == endNum){
                    end++;
                    break;
                }
            }
        }
    }
}
