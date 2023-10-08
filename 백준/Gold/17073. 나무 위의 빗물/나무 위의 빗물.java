import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static int N, W;
    public static ArrayList[] links;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 노드의 수
        W = Integer.parseInt(st.nextToken()); // 고인 물의 양

        links = new ArrayList[N+1];
        for(int i=1;i<=N;i++){
            links[i] = new ArrayList<Integer>();
        }

        for(int i=1;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            links[a].add(b);
            links[b].add(a);
        }

        int lastNodeCnt = 0;
        for(int i=2;i<=N;i++){
            if(links[i].size() == 1){
                lastNodeCnt++;
            }
        }

        bw.write(String.format("%.10f",(double)W/lastNodeCnt));
        bw.flush();

        br.close();
        bw.close();
    }
}
