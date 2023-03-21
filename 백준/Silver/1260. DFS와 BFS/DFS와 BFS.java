import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

// 깊이 우선 탐색 - q1260 (DFS와 BFS)
public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int[][] map;
    static boolean[] visited;
    static int N;
    static int M;
    static int V;
    public static void main(String[] args) throws IOException {
       StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 정점의 개수 N
        M = Integer.parseInt(st.nextToken()); // 간선의 개수 M
        V = Integer.parseInt(st.nextToken()); // 탐색을 시작할 정점의 번호 V

        map = new int[N+1][N+1]; // 간선 연결 상태
        visited = new boolean[N+1]; // 방문한 노드 표시

        // map 그리기
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = 1;
            map[y][x] = 1;
        }

        dfs(V);
        bw.newLine();

        visited = new boolean[N+1]; // visited 초기화
        bfs();

        bw.flush();
    }

    public static void dfs(int v) throws IOException { // v번 노드 방문
        visited[v] = true;
        bw.write(Integer.toString(v)+" ");

        for(int i=1;i<=N;i++){
            if(map[v][i] == 1 && !visited[i])
                dfs(i);
        }
    }

    public static void bfs() throws IOException {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.add(V);
        visited[V] = true;
        bw.write(Integer.toString(V)+" ");

        while(!deque.isEmpty()){
            int temp = deque.removeFirst();

            for(int i=1;i<=N;i++){
                if(map[temp][i]==1 && !visited[i]) {
                    deque.addLast(i);
                    visited[i] = true;
                    bw.write(Integer.toString(i) + " ");
                }
            }
        }
    }
}