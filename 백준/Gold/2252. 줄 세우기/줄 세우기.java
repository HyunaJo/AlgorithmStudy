import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static Deque<Integer> deque = new ArrayDeque<>();
    public static int[] indegree; // 들어오는 간선의 수
    public static ArrayList<Integer>[] edges;
    public static int N,M;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 학생 수
        M = Integer.parseInt(st.nextToken()); // 키를 비교한 횟수

        edges = new ArrayList[N+1];
        for(int i=1;i<=N;i++)
            edges[i] = new ArrayList<>();

        indegree = new int[N+1];
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            indegree[b]++;
            edges[a].add(b);
        }

        for(int i=1;i<=N;i++){
            if(indegree[i]==0)
                deque.add(i);
        }
        while(!deque.isEmpty()){
            int cur = deque.pollFirst();

            bw.write(Integer.toString(cur)+" ");

            int size = edges[cur].size();
            for(int i=0;i<size;i++){
                int targetId = edges[cur].get(i);
                indegree[targetId]--;
                if(indegree[targetId]==0)
                    deque.add(targetId);
            }
        }

        bw.flush();

        br.close();
        bw.close();
    }
}
