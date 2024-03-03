import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static final int BLANK = 0;
    static final int COLOR_PAPER = 1;
    static final int PAPER = 10;
    static int[][] map;
    static int[] counts = {0,5,5,5,5,5};
    static boolean[][] visited;
    static int min;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        map = new int[PAPER][PAPER];
        for(int i=0;i<PAPER;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<PAPER;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        min = Integer.MAX_VALUE;
        visited = new boolean[PAPER][PAPER];
        findMin(0,0, 0);

        bw.write((min==Integer.MAX_VALUE)?"-1":Integer.toString(min));
        bw.flush();

        br.close();
        bw.close();
    }

    public static void findMin(int x, int y, int cnt){
        if(x>=PAPER-1 && y>PAPER-1){
            min = Math.min(min, cnt);
            return;
        }

        if(cnt >= min){
            return;
        }

        if(y>=PAPER){
            x += 1;
            y = 0;

        }

        if(map[x][y] == BLANK){
            findMin(x,y+1,cnt);
            return;
        }

        for(int size=5;size>0;size--){
            if(counts[size] == 0){
                continue;
            }

            if(isAvailable(x,y,size)){
                counts[size]--;
                setStatus(x,y,size,BLANK);
                findMin(x,y+1,cnt+1);
                setStatus(x,y,size,COLOR_PAPER);
                counts[size]++;
            }
        }
    }

    public static void setStatus(int x, int y, int size, int status){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                visited[x+i][y+j] = !visited[x+i][y+j];
                map[x+i][y+j] = status;
            }
        }
    }

    public static boolean isAvailable(int x, int y, int size){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(x+i>=PAPER || y+j>=PAPER){
                    return false;
                }

                if(map[x+i][y+j] == BLANK || visited[x+i][y+i]){
                    return false;
                }
            }
        }

        return true;
    }
}
