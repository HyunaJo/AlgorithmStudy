import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static int N, M;
    public static boolean[][] seats;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        seats = new boolean[N+1][21];
        while(M-->0){
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());

            if(command == 1){
                int i = Integer.parseInt(st.nextToken()); // 기차 번호
                int x = Integer.parseInt(st.nextToken()); // 좌석 번호

                seats[i][x] = true;
            }
            else if (command == 2) {
                int i = Integer.parseInt(st.nextToken()); // 기차 번호
                int x = Integer.parseInt(st.nextToken()); // 좌석 번호

                seats[i][x] = false;
            }
            else {
                int i = Integer.parseInt(st.nextToken()); // 기차 번호

                setSeats(command, i);
            }
        }

        HashSet<String> answers = new HashSet<>();
        for(int i=1;i<=N;i++){
            StringBuilder result = new StringBuilder();
            for(int j=1;j<=20;j++){
                if(seats[i][j]){
                    result.append("1");
                }
                else{
                    result.append("0");
                }
            }
            answers.add(result.toString());
        }


        bw.write(Integer.toString(answers.size()));
        bw.flush();

        br.close();
        bw.close();
    }

    public static void setSeats(int command, int i){
        if(command == 3){
            for(int j=20;j>1;j--){
                seats[i][j] = seats[i][j-1];
            }
            seats[i][1] = false;
        }
        else if(command == 4){
            for(int j=1;j<20;j++){
                seats[i][j] = seats[i][j+1];
            }
            seats[i][20] = false;
        }
    }
}
