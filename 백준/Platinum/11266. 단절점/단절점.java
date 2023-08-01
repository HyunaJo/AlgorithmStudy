import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Array;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static int V,E;
    public static ArrayList<Integer>[] adjList;
    public static int[] visited; // 방문 순서
    public static boolean[] isDjj; // 단절점인지
    public static int order;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 1. 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[V+1];
        for(int i=1;i<=V;i++)
            adjList[i] = new ArrayList<Integer>();

        for(int i=1;i<=E;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList[a].add(b);
            adjList[b].add(a);
        }

        // 2. visit 배열,
        visited = new int[V+1];
        isDjj = new boolean[V+1];

        // order는 전역변수 - 방문순서 기록용 변수
        order = 1;
        for(int i=1;i<=V;i++){
            // 그래프가 분리되어 있을 수 있음
            if(visited[i] == 0){
                // id, parentId, isRoot
                // 내가 방금 왔던 곳은 가지 않을게 -> parentId 이용
                dfs(i,0,true);
            }
        }

        // 3. 단절점 개수 출력
        int ans = 0;
        String djj = "";
        for(int i=1;i<=V;i++){
            if(isDjj[i]){
                ans++;
                djj += Integer.toString(i)+" ";
            }
        }
        bw.write(ans+"\n");
        bw.write(djj);

        bw.flush();

        br.close();
        bw.close();

    }

    public static int dfs(int id, int parent, boolean isRoot){
        // 1. 방문 순서 기록 후 자식(child)과 비교 준비
        visited[id] = order;
        order++;

        // ret : 함수가 return하면서 방문했던 최소 order를 리턴
        //       역전 현상이 발생하는지 확인하는 용도
        int ret = visited[id];
        int childCnt = 0;

        // 2. 자식 dfs - 인접 정점 모두 확인
        int len = adjList[id].size();
        for(int i=0;i<len;i++)  {
            int next = adjList[id].get(i);

            // 왔던길 돌아가기 방지
            if(next==parent)
                continue;

            // 2-1. 자식 최초 방문했을 경우
            if(visited[next] == 0){
                childCnt++;

                // 자식 정점 중 방문순서가 빠른 값
                // DP, 분할정복 divided&conquer -> 밑바닥까지 최후의 노드까지 1개
                int lowOrder = dfs(next, id, false);

                // Root가 아니고 order 역전이 불가능하면 단절점
                if(!isRoot && lowOrder>=visited[id])
                    isDjj[id] = true;

                ret = Math.min(ret, lowOrder);
            }
            // 2-2. 자식을 이미 방문한 경우
            else{
                ret = Math.min(ret, visited[next]); // 너가 가진 애 중에 작은 애 가져와
            }
        }

        // 3. 루트 정점인 경우 자식 개수가 2개 이상이면 단절점
        if(isRoot && childCnt>=2)
            isDjj[id] = true;

        return ret;
    }
}
