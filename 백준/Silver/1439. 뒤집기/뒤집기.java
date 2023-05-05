import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String S = br.readLine();
        Character startNum = S.charAt(0);
        int zeroBundleCnt = 0;
        int oneBundleCnt = 0;
        for(int i=1;i<S.length();i++){
            Character num = S.charAt(i);
            if(startNum != num){
                if(startNum == '0')
                    zeroBundleCnt++;
                else
                    oneBundleCnt++;
                startNum = num;
            }
        }
        if(startNum == '0')
            zeroBundleCnt++;
        else
            oneBundleCnt++;

        bw.write(Integer.toString(Math.min(zeroBundleCnt, oneBundleCnt)));
        bw.flush();
    }
}
