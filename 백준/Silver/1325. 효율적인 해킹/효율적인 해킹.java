import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, max = 0;
    static ArrayList<ArrayList<Integer>> links = new ArrayList<>();
    static boolean[] visited;
    static int[] linkCnts;
    static ArrayList<Integer> answers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 컴퓨터 수
        int M = Integer.parseInt(st.nextToken()); // 신뢰 관계 수

        linkCnts = new int[N+1];
        visited = new boolean[N+1];

        for(int i=0;i<=N;i++){
            links.add(new ArrayList<Integer>());
        }

        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            links.get(a).add(b);
        }


        for(int i=1;i<=N;i++){
            visited = new boolean[N+1];
            dfs(i);
        }

        for(int i=1;i<=N;i++){
            int hackingCnt = linkCnts[i];

            if(hackingCnt > max){
                answers.clear();;
                answers.add(i);
                max = hackingCnt;
            }
            else if(hackingCnt == max){
                answers.add(i);
            }
        }

        for(int answer:answers){
            bw.write(Integer.toString(answer)+" ");
        }

        bw.flush();
    }

    public static void dfs(int computerNum){
        visited[computerNum] = true;

        for (int i: links.get(computerNum)){
            if(!visited[i]){
                linkCnts[i]++;
                dfs(i);
            }
        }
    }
}
