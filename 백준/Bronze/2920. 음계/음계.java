import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int upCnt = 0;
    static int downCnt = 0;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int prior = Integer.parseInt(st.nextToken());
        for(int i=1;i<8;i++){
            int num = Integer.parseInt(st.nextToken());
            if(prior < num){
                upCnt++;
            }
            else if(prior > num){
                downCnt++;
            }
            prior = num;
        }

        if(upCnt == 7){
            bw.write("ascending");
        }
        else if(downCnt == 7){
            bw.write("descending");
        }
        else{
            bw.write("mixed");
        }

        bw.flush();
    }
}
