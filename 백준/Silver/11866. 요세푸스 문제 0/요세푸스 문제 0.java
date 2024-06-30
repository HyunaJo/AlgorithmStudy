import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Deque<Integer> dq = new ArrayDeque<>();
        for(int i=1;i<=N;i++){
            dq.add(i);
        }

        sb.append("<");
        int idx = 0;
        while(dq.size()>1){
            idx = (idx+1)%K;
            int num = dq.poll();

            if(idx == 0){
                sb.append(num).append(", ");
                continue;
            }

            dq.add(num);
        }
        sb.append(dq.poll()).append(">");

        bw.write(sb.toString());
        bw.flush();
    }
}

