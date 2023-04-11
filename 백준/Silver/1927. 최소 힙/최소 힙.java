import java.io.*;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i=0;i<N;i++){
            int num = Integer.parseInt(br.readLine());
            if(num==0){ // 0 입력한 경우
                if (pq.isEmpty()) // 비어있으면
                    bw.write("0\n"); // 0 출력
                else // 안 비어있는 경우
                    bw.write(Integer.toString(pq.remove())+"\n"); // 가장 작은 값 출력
            }
            else // 자연수 입력 시
                pq.add(num); // pq에 값 추가
        }

        bw.flush();
    }
}
