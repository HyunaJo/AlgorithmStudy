import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static int knownCnt;
    static int[] parent;
    static boolean[] known;
    static ArrayList<Integer>[] parties;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N+1];
        for(int i=1;i<=N;i++){
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        knownCnt = Integer.parseInt(st.nextToken());
        known = new boolean[N+1];
        for(int i=0;i<knownCnt;i++){
            known[Integer.parseInt(st.nextToken())] = true;
        }

        parties = new ArrayList[M];
        for(int m=0;m<M;m++){
            parties[m] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int peopleCnt = Integer.parseInt(st.nextToken());

            int first = Integer.parseInt(st.nextToken());
            parties[m].add(first);
            for(int i=1;i<peopleCnt;i++){
                int num = Integer.parseInt(st.nextToken());
                parties[m].add(num);
                union(first, num);
            }
        }

        for(int i=1;i<=N;i++){
            if(!known[i]){
                continue;
            }

            int parent = findParent(i);
            known[parent] = true;
        }

        int impossibleCnt = 0;
        for(int i=0;i<M;i++){
            for(int num:parties[i]){
                if(known[findParent(num)]){
                    impossibleCnt++;
                    break;
                }
            }
        }

        bw.write(Integer.toString(M-impossibleCnt));
        bw.flush();
    }

    public static void union(int a, int b){
        if(a == b){
            return;
        }

        a = findParent(a);
        b = findParent(b);
        if(a > b){
            parent[a] = b;
        }
        else{
            parent[b] = a;
        }
    }

    public static int findParent(int child){
        if(child == parent[child]){
            return child;
        }

        return parent[child] = findParent(parent[child]);
    }
}
