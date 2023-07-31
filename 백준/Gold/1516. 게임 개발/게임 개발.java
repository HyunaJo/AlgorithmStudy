import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static int N; // 건물의 종류 수
    public static Deque<Integer> deque = new ArrayDeque<>();
    public static int[] indegree;
    public static int[] times;
    public static ArrayList<Integer>[] nextIds;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        nextIds = new ArrayList[N+1];
        for(int i=1;i<=N;i++)
            nextIds[i] = new ArrayList<>();

        times = new int[N+1];
        indegree = new int[N+1];
        for(int i=1;i<=N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            times[i] = Integer.parseInt(st.nextToken()); // 건물 짓는데 걸리는 시간

            while(true){
                int priorBuildingId = Integer.parseInt(st.nextToken()); // 먼저 지어져야하는 건물 번호

                // -1 입력된 경우 break
                if(priorBuildingId==-1)
                    break;

                indegree[i]++;
                nextIds[priorBuildingId].add(i);
            }
        }

        for(int i=1;i<=N;i++){
            if(indegree[i]==0)
                deque.add(i);
        }

        int[] priorTimes = new int[N+1]; // 이전 건물 짓는데 걸리는 시간
        while(!deque.isEmpty()){
            int cur = deque.pollFirst();

            int size = nextIds[cur].size();
            for(int i=0;i<size;i++){
                int nextId = nextIds[cur].get(i);
                priorTimes[nextId] = Math.max(priorTimes[nextId], times[cur]+priorTimes[cur]);
                indegree[nextId]--;

                if(indegree[nextId]==0)
                    deque.add(nextId);
            }
        }

        for(int i=1;i<=N;i++){
            bw.write(Integer.toString(priorTimes[i]+times[i])+"\n");
        }
        bw.flush();

        br.close();
        bw.close();
    }
}
