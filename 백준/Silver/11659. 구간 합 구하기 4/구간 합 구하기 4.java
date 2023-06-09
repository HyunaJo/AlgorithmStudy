import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 수의 개수
        int M = Integer.parseInt(st.nextToken()); // 합을 구해야 하는 횟수
        int[] sums = new int[N+1]; // 합 배열
        int sum = 0;

        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++){
            sum += Integer.parseInt(st.nextToken());
            sums[i] = sum;
        }

        while(M>0){
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            bw.write(Integer.toString(sums[j]-sums[i-1])+"\n");
            M--;
        }

        bw.flush();

        br.close();
        bw.close();
    }
}
