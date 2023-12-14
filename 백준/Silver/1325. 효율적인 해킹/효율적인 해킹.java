import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int N, M;
    public static ArrayList<Integer>[] relations;
    public static int[] hackingCnt;
    public static boolean[] visited;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        relations = new ArrayList[N+1];
        for(int i=1;i<=N;i++){
            relations[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            relations[A].add(B);
        }

        hackingCnt = new int[N+1];
        for(int i=1;i<=N;i++){
            visited = new boolean[N+1];
            countHacking(i);
        }

        ArrayList<Integer> maxIndex = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        for(int i=1;i<=N;i++){
            if(hackingCnt[i] > max){
                maxIndex.clear();

                max = hackingCnt[i];
                maxIndex.add(i);
            }
            else if(hackingCnt[i] == max){
                maxIndex.add(i);
            }
        }

        StringBuilder ans = new StringBuilder();
        for(int idx:maxIndex){
            ans.append(idx).append(" ");
        }

        bw.write(ans.toString());
        bw.flush();

        br.close();
        bw.close();
    }

    public static void countHacking(int num){
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(num);
        visited[num] = true;

        while(!queue.isEmpty()){
            int now = queue.poll();

            for(int next:relations[now]){
                if(visited[next]){
                    continue;
                }

                visited[next] = true;
                hackingCnt[next]++;
                queue.add(next);
            }
        }
    }
}
