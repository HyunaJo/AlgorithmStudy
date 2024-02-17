import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int R,C;
    static char[][] board;
    static boolean[] used;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static int max = 1;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 세로, 가로 칸 수 입력받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken()); // 세로 칸 수
        C = Integer.parseInt(st.nextToken()); // 가로 칸 수

        // 보드 채우기
        board = new char[R][C];
        for(int i=0;i<R;i++){
            board[i] = br.readLine().toCharArray();
        }

        used = new boolean[26];
        used[board[0][0]-'A'] = true;
        findMax(1,0,0);

        bw.write(Integer.toString(max));
        bw.flush();

        br.close();
        bw.close();
    }

    public static void findMax(int cnt, int x, int y){
        if(max == 26){
            return;
        }

        for(int i=0;i<4;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx<0 || nx>=R || ny<0 || ny>=C){
                continue;
            }

            int nextIdx = board[nx][ny]-'A';
            if(used[nextIdx]){
                continue;
            }

            used[nextIdx] = true;
            max = Math.max(max, cnt+1);
            findMax(cnt+1, nx, ny);
            used[nextIdx] = false;
        }
    }
}
