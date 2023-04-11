import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int N; // 정점의 개수
    static int M; // 간선의 개수
    static int[][] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N+1][N+1];
        visited = new boolean[N+1];
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            graph[x][y] = 1;
            graph[y][x] = 1;
        }

        int cnt = 0; // 연결 요소 개수
        for(int i=1;i<=N;i++){ // 1번 노드부터 시작
            if(!visited[i]) { // 확인하지 않은 노드인 경우
                cnt++;
                bfs(i);
            }
        }
        bw.write(Integer.toString(cnt));
        bw.flush();
    }

    public static void bfs(int start) {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(start);
        visited[start] = true;

        while(!deque.isEmpty()){
            int temp = deque.removeFirst();

            for(int i=1;i<=N;i++){
                if(graph[temp][i]==1 && !visited[i]){
                    deque.addLast(i);
                    visited[i] = true;
                }
            }
        }

    }
}
