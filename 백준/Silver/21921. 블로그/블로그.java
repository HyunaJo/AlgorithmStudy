import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 블로그를 시작하고 지난 일수 N
        int X = Integer.parseInt(st.nextToken()); // X일 동안 들어온 방문자 수

        st = new StringTokenizer(br.readLine());
        int[] visits = new int[N];
        for(int i=0;i<N;i++)
            visits[i] = Integer.parseInt(st.nextToken());

        int max = 0; // 최대 누적 방문자 수
        int sum = 0; // 누적 방문자 수
        int cnt = 1; // 최대 누적 방문자인 기간 수
        
        for(int i=0;i<X;i++) // 첫날부터 X일 동안 방문자 수
            sum += visits[i];
        max = sum;
        
        for(int i=X;i<N;i++){
            sum -= visits[i-X];
            sum += visits[i];
            
            if(sum>max){ // max보다 누적 방문자 수 많은 경우
                max = sum;
                cnt = 1;
            }
            else if(sum==max) // max와 같은 누적 방문자 수인 경우
                cnt++; // 기간++;
        }
        if(max==0)
            bw.write("SAD");
        else
            bw.write(max +"\n"+ cnt);
        bw.flush();
    }
}
