import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int i=0;i<N;i++)
            queue.add(Integer.parseInt(br.readLine()));

        int result = 0;
        while(queue.size()>1){
            int x = queue.poll();
            int y = queue.poll();

            int sum = (x+y);
            result += sum;
            queue.add(sum);
        }
        bw.write(Integer.toString(result));
        bw.flush();
    }
}
