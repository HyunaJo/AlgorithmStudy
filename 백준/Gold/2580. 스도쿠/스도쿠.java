import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static int[][] map = new int[9][9];
    public static ArrayList<Location> blanks = new ArrayList<>();
    public static boolean isFinish = false;
    public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static class Location{
        int x;
        int y;

        public Location(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i=0;i<9;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<9;j++){
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
                if(num == 0){ // 빈칸 위치 담아두기
                    blanks.add(new Location(i,j));
                }
            }
        }

        sudoku(0);
        bw.flush();

        br.close();
        bw.close();
    }

    public static void sudoku(int idx) throws Exception{
        if(isFinish) {
            return;
        }

        if(idx == blanks.size()){
            isFinish = true;
            printAns();
            return;
        }

        Location loc = blanks.get(idx);
        for(int i=1;i<=9;i++){
            if(checkPossible(loc.x, loc.y, i) && !isFinish){
                map[loc.x][loc.y] = i;
                sudoku(idx+1);
            }
        }
        map[loc.x][loc.y] = 0;
    }

    public static boolean checkPossible(int x, int y, int num){
        for(int i=0;i<9;i++){
            if(i!=y && map[x][i]==num) // 가로 확인
                return false;
            if(i!=x && map[i][y]==num) // 세로 확인
                return false;
        }

        // 정사각형 8칸 확인
        int top = (x/3)*3; // 정사각형의 첫 행 번호
        int right = (y/3)*3; // 정사각형의 첫 열 번호
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                int nx = top+i;
                int ny = right+j;

                if(nx==x && ny==y)
                    continue;

                if(map[nx][ny]==num)
                    return false;
            }
        }

        return true;
    }

    public static void printAns() throws Exception{
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                bw.write(Integer.toString(map[i][j])+" ");
            }
            bw.write("\n");
        }
    }
}
