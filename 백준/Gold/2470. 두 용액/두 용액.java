import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine()); // 전체 용액의 수 N

        int[] liquids = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++)
            liquids[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(liquids);

        int start = 0;
        int end = N-1;
        int min = Integer.MAX_VALUE;
        int[] mixLiquids = new int[2];
        while(start<end){
            int x = liquids[start];
            int y = liquids[end];
            int sum = x+y;
            int absSum = Math.abs(sum);
            if(absSum<min) {// min보다 절댓값이 작을 경우
                min = absSum; // min 변경
                mixLiquids[0] = x;
                mixLiquids[1] = y;
            }
            if(sum>0) // 두 용액 합이 0보다 큰 경우
                end--;
            else
                start++; // 두 용액 합이 0보다 작거나 같은 경우
        }

        bw.write(Integer.toString(mixLiquids[0])+" "+Integer.toString(mixLiquids[1]));
        bw.flush();
    }
}
