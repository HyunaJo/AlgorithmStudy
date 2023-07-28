import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static int T,N,M;
    public static long[][] combination; // combination[i][j] = iCj

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        combination = new long[31][31];
        combination[0][0] = combination[1][0] = combination[1][1] = 1;
        for(int i=2;i<=30;i++){
            for(int j=0;j<=i;j++){
                if(j==0||j==i)
                    combination[i][j] = 1;
                else
                    combination[i][j] = (combination[i-1][j-1]+combination[i-1][j]);
            }
        }

        T = Integer.parseInt(br.readLine()); // 테스트케이스 수
        while(T>0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            bw.write(Long.toString(combination[M][N])+"\n");
            T--;
        }

        bw.flush();

        br.close();
        bw.close();
    }
}
