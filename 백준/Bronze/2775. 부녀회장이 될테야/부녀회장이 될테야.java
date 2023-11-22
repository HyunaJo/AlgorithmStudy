import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static int T, k, n;
    public static int[][] people;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        T = Integer.parseInt(br.readLine()); // 테스트 케이스의 수

        while(T-->0){
            k = Integer.parseInt(br.readLine()); // k층
            n = Integer.parseInt(br.readLine()); // n호

            people = new int[k+1][n+1];
            for(int i=1;i<=n;i++){
                people[0][i] = i;
            }

            for(int i=1;i<=k;i++){
                for(int j=1;j<=n;j++){
                    people[i][j] = people[i][j-1]+people[i-1][j];
                }
            }

            bw.write(people[k][n]+"\n");
        }
        bw.flush();
        
        br.close();
        bw.close();
    }
}