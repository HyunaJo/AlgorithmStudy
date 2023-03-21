import java.io.*;
import java.util.StringTokenizer;

// 그리디 알고리즘 - q13305 (주유소)
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine()); // 도시의 개수 N

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] distances = new int[N-1]; // 두 도시를 연결하는 도로의 길이
        long totalDistance = 0; // 총거리
        for(int i=0;i<N-1;i++) {
            int distance = Integer.parseInt(st.nextToken());
            totalDistance += distance;
            distances[i] = distance;
        }

        st = new StringTokenizer(br.readLine());
        int[] prices = new int[N]; // 주유소의 리터당 가격
        for(int i=0;i<N;i++)
            prices[i] = Integer.parseInt(st.nextToken());

        int idx = 0;
        int price = prices[idx];
        long totalPrice = 0;
        long checkDistance = 0;
        while(checkDistance<totalDistance){
            totalPrice += (long) price * distances[idx]; // 다음 도시 갈만큼만 주유
            checkDistance += distances[idx];

            for(int i=idx+1;i<N-1;i++){ // 마지막 주유소의 리터당 가격은 비교할 필요 없음
                if(price>prices[i]) // 현재 주유소 리터당 가격 > 다음 주유소 리터당 가격
                    break;
                else // 계속해서 그 다음 주유소와 비교
                    idx++;
                totalPrice += (long) price * distances[idx]; // 다음 도시 갈만큼만 주유
                checkDistance += distances[idx];
            }
            idx++;
            price = prices[idx];
        }
        bw.write(Long.toString(totalPrice));
        bw.flush();
    }
}
