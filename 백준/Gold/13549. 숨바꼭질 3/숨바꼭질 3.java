import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] time = new int[100001];
    static boolean[] visited = new boolean[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        countTime();
        bw.write(Integer.toString(time[K]));
        bw.flush();
    }

    public static void countTime(){
        visited[N] = true;
        time[N] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);

        while(!queue.isEmpty()){
            int now = queue.remove();

            if(now == K){
                return;
            }

            if(now*2<=100000){
                if(!visited[now*2] || time[now*2]>time[now]){
                    time[now*2] = time[now];
                    visited[now*2] = true;
                    queue.add(now*2);
                }
            }
            if(now+1<=100000){
                if(!visited[now+1] || time[now+1]>time[now]+1){
                    time[now+1] = time[now]+1;
                    visited[now+1] = true;
                    queue.add(now+1);
                }
            }
            if(now-1>=0){
                if(!visited[now-1] || time[now-1]>time[now]+1){
                    time[now-1] = time[now]+1;
                    visited[now-1] = true;
                    queue.add(now-1);
                }
            }
        }
    }
}
