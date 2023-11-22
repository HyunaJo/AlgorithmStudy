import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static int N;
    public static int[][] seat;
    public static ArrayList<Integer> seatedStudents;
    public static ArrayList<Integer>[] favorites;
    public static int[] dx = {1,-1,0,0};
    public static int[] dy = {0,0,1,-1};
    public static int score = 0;

    public static class Seat implements Comparable<Seat> {
        int x;
        int y;
        int blankCnt;
        int favoriteCnt;

        public Seat(int x, int y, int blankCnt, int favoriteCnt){
            this.x = x;
            this.y = y;
            this.blankCnt = blankCnt;
            this.favoriteCnt = favoriteCnt;
        }

        @Override
        public int compareTo(Seat o) {
            if(o.favoriteCnt != this.favoriteCnt){
                return o.favoriteCnt - this.favoriteCnt;
            }

            if(o.blankCnt != this.blankCnt){
                return o.blankCnt - this.blankCnt;
            }

            if(o.x != this.x){
                return this.x - o.x;
            }

            return this.y - o.y;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        seat = new int[N+1][N+1];
        seatedStudents = new ArrayList<>();
        favorites = new ArrayList[N*N+1];

        for(int i=0;i<N*N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int studentNum = Integer.parseInt(st.nextToken());
            favorites[studentNum] = new ArrayList<>();
            for(int j=0;j<4;j++){
                int favoriteStudent = Integer.parseInt(st.nextToken());
                favorites[studentNum].add(favoriteStudent);
            }

            findSeat(studentNum);

            seatedStudents.add(studentNum);
        }

        getScore();
        bw.write(Integer.toString(score));

        bw.flush();
        br.close();
        bw.close();
    }

    public static void findSeat(int studentNum){
        ArrayList<Seat> possibleSeats = new ArrayList<>();

        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                if(seat[i][j] != 0){
                    continue;
                }

                int blankCnt = 0;
                int favoriteCnt = 0;

                for(int k=0;k<4;k++){
                    int newX = i + dx[k];
                    int newY = j + dy[k];

                    if(newX < 1 || newX > N || newY < 1 || newY > N){
                        continue;
                    }

                    if(seat[newX][newY] == 0){
                        blankCnt++;
                    }
                    else if(favorites[studentNum].contains(seat[newX][newY])){
                        favoriteCnt++;
                    }
                }
                possibleSeats.add(new Seat(i, j, blankCnt, favoriteCnt));
            }
        }

        Collections.sort(possibleSeats);
        Seat finalSeat = possibleSeats.get(0);
        seat[finalSeat.x][finalSeat.y] = studentNum;
    }

    public static void getScore(){
        for(int i=1;i<=N;i++){
            for(int j=1;j<=N;j++){
                int favoriteCnt = 0;

                for(int k=0;k<4;k++){
                    int newX = i + dx[k];
                    int newY = j + dy[k];

                    if(newX < 1 || newX > N || newY < 1 || newY > N){
                        continue;
                    }

                    if(favorites[seat[i][j]].contains(seat[newX][newY])){
                        favoriteCnt++;
                    }
                }

                if(favoriteCnt == 1){
                    score += 1;
                }
                else if(favoriteCnt == 2){
                    score += 10;
                }
                else if(favoriteCnt == 3){
                    score += 100;
                }
                else if(favoriteCnt == 4){
                    score += 1000;
                }
            }
        }
    }
}
