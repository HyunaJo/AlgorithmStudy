import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int M;
    static boolean[] nums;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        nums = new boolean[21];
        M = Integer.parseInt(br.readLine());
        while(M-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();

            switch (cmd){
                case "add":
                    int x = Integer.parseInt(st.nextToken());
                    nums[x] = true;
                    break;
                case "remove":
                    x = Integer.parseInt(st.nextToken());
                    nums[x] = false;
                    break;
                case "check":
                    x = Integer.parseInt(st.nextToken());
                    sb.append((nums[x])?"1":"0").append("\n");
                    break;
                case "toggle":
                    x = Integer.parseInt(st.nextToken());
                    nums[x] = !nums[x];
                    break;
                case "all":
                    Arrays.fill(nums, true);
                    break;
                case "empty":
                    Arrays.fill(nums, false);
                    break;
            }
        }

        bw.write(sb.toString());
        bw.flush();
    }
}
