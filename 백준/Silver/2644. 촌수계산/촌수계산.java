import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Integer>[] relations;
    static boolean[] visited;
    static int ans = -1;
    public static int n;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        relations = new ArrayList[n+1];
        visited = new boolean[n+1];
        for(int i=1; i<n+1; i++)
            relations[i] = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(br.readLine());

        for(int i=0; i<l; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            relations[p].add(c);
            relations[c].add(p);
        }

        dfs(x,y, 0);
        bw.write(Integer.toString(ans));
        bw.flush();

        br.close();
        bw.close();
    }

    static void dfs(int start, int end, int cnt) {
        if(start == end) {
            ans = cnt;
            return;
        }

        visited[start] = true;
        for(int i=0; i<relations[start].size(); i++) {
            int next = relations[start].get(i);
            if(!visited[next]) {
                dfs(next, end, cnt+1);
            }
        }
    }
}
