import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static ArrayList<Integer>[] relations;
    static boolean[] visited;
    static boolean isPossible = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 사람의 수
        M = Integer.parseInt(st.nextToken()); // 친구 관계의 수

        relations = new ArrayList[N];
        visited = new boolean[N];
        for(int i=0;i<N;i++)
            relations[i] = new ArrayList<>();

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            relations[a].add(b);
            relations[b].add(a);
        }

        for(int i=0;i<N;i++){
            findRelations(i,1);
            if(isPossible) {
                break;
            }
        }

        if(isPossible)
            bw.write("1\n");
        else
            bw.write("0\n");

        bw.flush();
    }

    public static void findRelations(int start, int connectCnt){
        if(connectCnt==5) {
            isPossible = true;
            return;
        }

        visited[start] = true;
        for(int friend:relations[start]){
            if(!visited[friend]){
                findRelations(friend, connectCnt+1);
            }
        }
        visited[start] = false;
    }
}
