import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    public static int N;
    public static PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    public static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        for(int i = 0 ; i < N; i++){
            int num = Integer.parseInt(br.readLine());

            if(minHeap.size() == maxHeap.size())
                maxHeap.offer(num);
            else
                minHeap.offer(num);

            if(!minHeap.isEmpty() && !maxHeap.isEmpty())
                if(minHeap.peek() < maxHeap.peek()){
                    int tmp = minHeap.poll();
                    minHeap.offer(maxHeap.poll());
                    maxHeap.offer(tmp);
                }

            bw.write(maxHeap.peek() + "\n");
        }

        bw.flush();

        br.close();
        bw.close();
    }
}