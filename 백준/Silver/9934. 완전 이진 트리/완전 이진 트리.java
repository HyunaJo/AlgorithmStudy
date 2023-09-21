import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static int K; // 1 <= 빌딩번호 < 2^k
    public static int[] nodes;
    public static String[] ans;
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        K = Integer.parseInt(br.readLine());
        int maxNum = (int)Math.pow(2,K);
        nodes = new int[maxNum];
        ans = new String[K];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1;i<=maxNum-1;i++){
            nodes[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=0;i<K;i++){
            ans[i] = "";
        }

        printTree(1, maxNum-1, 0);
        for(int i=0;i<K;i++){
            bw.write(ans[i].trim()+"\n");
        }

        bw.flush();

        br.close();
        bw.close();
    }

    public static void printTree(int start, int end, int height) throws Exception{
        if(height == K) {
            return;
        }

        int mid = (start+end)/2;
        ans[height] = ans[height] + " " + Integer.toString(nodes[mid]);

        printTree(start, mid-1, height+1);
        printTree(mid+1, end, height+1);
    }
}
