import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    static class CirclePoint implements Comparable<CirclePoint>{
        int x;
        int circleIdx;
        int type;

        public CirclePoint(int x, int circleIdx, int type){
            this.x = x;
            this.circleIdx = circleIdx;
            this.type = type;
        }

        @Override
        public int compareTo(CirclePoint o){
            return this.x-o.x;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        CirclePoint[] circlePoints = new CirclePoint[2*N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int center = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            circlePoints[i*2] = new CirclePoint(center-r, i, 0);
            circlePoints[i*2+1] = new CirclePoint(center+r, i, 1);
        }
        Arrays.sort(circlePoints);

        Deque<CirclePoint> dq = new ArrayDeque<>();
        for (CirclePoint point : circlePoints) {
            if (dq.isEmpty()) {
                dq.addFirst(point);
                continue;
            }

            CirclePoint prior = dq.peek();
            if (prior.circleIdx == point.circleIdx) {
                dq.pop();
                continue;
            }
            if (prior.type==0 && point.type==1) {
                break;
            }

            dq.push(point);
        }

        sb.append(dq.isEmpty() ? "YES" : "NO");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
