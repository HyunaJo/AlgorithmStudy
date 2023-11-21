import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main{
    public static int n,m;
    public static PriorityQueue<Long> nums;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 카드의 개수
        m = Integer.parseInt(st.nextToken()); // 합체 횟수

        nums = new PriorityQueue<Long>();
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            nums.add(Long.parseLong(st.nextToken()));
        }

        while(m-->0){
            long x = nums.poll();
            long y = nums.poll();
            long newNum = x+y;

            nums.add(newNum);
            nums.add(newNum);
        }

        long sum = 0;
        for(int i=0;i<n;i++){
            sum += nums.poll();
        }

        bw.write(Long.toString(sum));
        bw.flush();

        br.close();
        bw.close();
    }
}
