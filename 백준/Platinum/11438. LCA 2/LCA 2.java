import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static int N,M,K;
    public static int[] depth;
    public static int[][] parent; // parent[K][V] = 정점V의 2^K번째 조상 정점 번호
    public static ArrayList<Integer>[] tree;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        tree = new ArrayList[N+1];
        for(int i=1;i<=N;i++)
            tree[i] = new ArrayList<>();

        for(int i=0;i<N-1;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }

//        K = -1;
//        for(int i=1;i<=N;i*=2)
//            K++;
        K = (int) Math.ceil( Math.log(N) / Math.log(2) );

        depth = new int[N+1];
        parent = new int[K+1][N+1];
        dfs(1,1);
        fillParent();

        M = Integer.parseInt(br.readLine());
        while(M-->0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            bw.write(Integer.toString(lca(a,b))+"\n");
        }

        bw.flush();

        br.close();
        bw.close();
    }

    static void dfs(int id, int cnt) {
        // 1. depth를 기록
        depth[id] = cnt;

        // 2. 자식들의 depth를 기록
        int len = tree[id].size();
        for (int i = 0; i < len; i++) {
            int next = tree[id].get(i);

            // 아직 깊이를 모르면 → 미방문 노드
            if (depth[next] == 0) {
                dfs(next, cnt+1);
                parent[0][next] = id;       // 첫번째 부모를 기록  (2^0)
            }
        }
    }

    public static void fillParent(){
        for(int i=1;i<=K;i++){
            for(int j=1;j<=N;j++)
                parent[i][j] = parent[i-1][parent[i-1][j]];
        }
    }

    // 4. 최소공통조상 찾기
    static int lca(int a, int b) {
        // 1. depth[a] >= depth[b] 이도록 조정하기
        if (depth[a] < depth[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        // 2. 더 깊은 a를 2^K승 점프하여 depth를 맞추기
        //    깊이의 차를 2제곱수의 합을 이용해 좁히기 - 큰 수부터 Jump
        for (int i = K; i>=0; i--) {
            if (Math.pow(2, i) <= depth[a] - depth[b]) {
                a = parent[i][a];
            }
        }

        // 3. depth를 맞췄는데 같다면 바로 종료
        if (a == b) return a;

        // 4. a-b는 같은 depth이므로 2^K승 점프하며 공통부모 바로 아래까지 올리기
        for (int i = K; i >= 0; i--) {
            if (parent[i][a] != parent[i][b]) {
                a = parent[i][a];
                b = parent[i][b];
            }
        }

        // 공통부모 바로 아래에서 반복문이 끝났으므로 첫번째 부모 (2^0) 리턴
        return parent[0][a];
    }
}
