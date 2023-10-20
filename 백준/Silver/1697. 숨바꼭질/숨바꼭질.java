import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static final int MAX_NUM = 100_000;
    public static int N, K;
    public static int[] time = new int[MAX_NUM+2];
    public static int ans = 0;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 수빈이의 위치
        K = Integer.parseInt(st.nextToken()); // 동생의 위치

        if(N != K){
            bfs(N);
        }

        bw.write(Integer.toString(ans));
        bw.flush();

        br.close();
        bw.close();
    }

    public static void bfs(int loc){
        Deque<Integer> dq = new ArrayDeque<>();
        dq.addLast(loc);

        while(!dq.isEmpty()){
            int nowLoc = dq.pollFirst();

            // 순간이동
            for(int i=0;i<3;i++){
                int nextLoc = nowLoc;

                if(i == 0){
                    nextLoc += 1;
                } else if(i == 1){
                    nextLoc -= 1;
                } else if(i == 2){
                    nextLoc *= 2;
                }

                if(nextLoc == K){
                    ans = time[nowLoc]+1;
                    return;
                }

                if(nextLoc<0 || nextLoc>MAX_NUM || time[nextLoc]!=0){
                    continue;
                }

                time[nextLoc] = time[nowLoc]+1;
                dq.addLast(nextLoc);
            }
        }
    }
}