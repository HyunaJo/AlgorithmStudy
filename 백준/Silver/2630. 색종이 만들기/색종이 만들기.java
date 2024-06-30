import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] paper;
    static int whiteCnt, blueCnt;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        paper = new int[N][N];
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        whiteCnt = 0;
        blueCnt = 0;
        count(0,0,N);
        
        sb.append(whiteCnt).append("\n").append(blueCnt);
        bw.write(sb.toString());
        bw.flush();
    }

    public static void count(int x, int y, int n){
        if(isSame(x, y, n)){
            if(paper[x][y] == 1){
                blueCnt++;
            }
            else{
                whiteCnt++;
            }
            return;
        }

        int halfN = n/2;
        count(x, y, halfN);
        count(x, y+halfN, halfN);
        count(x+halfN, y, halfN);
        count(x+halfN, y+halfN, halfN);
    }

    public static boolean isSame(int x, int y, int size){
        int color = paper[x][y];

        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(paper[x+i][y+j] != color){
                    return false;
                }
            }
        }

        return true;
    }
}
