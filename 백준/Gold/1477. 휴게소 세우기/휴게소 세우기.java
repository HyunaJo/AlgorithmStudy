import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static int N,M,L;
    public static int[] locations;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 현재 휴게소 개수
        M = Integer.parseInt(st.nextToken()); // 더 지으려고 하는 휴게소 개수
        L = Integer.parseInt(st.nextToken()); // 고속도로 길이

        locations = new int[N+2];
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++){
            locations[i] = Integer.parseInt(st.nextToken());
        }
        locations[N+1] = L;
        Arrays.sort(locations);

        int left = 1;
        int right = L;
        while(left<=right){
            int mid = (right+left)/2;
            int cnt = 0;

            for(int i=0;i<=N;i++){
                cnt += (locations[i+1]-locations[i]-1)/mid;
            }

            if(cnt > M){
                left = mid+1;
            }
            else{
                right = mid-1;
            }
        }
        bw.write(Integer.toString(left));
        bw.flush();

        br.close();
        bw.close();
    }
}
