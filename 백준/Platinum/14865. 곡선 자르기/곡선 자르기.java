import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    static int N;

    static class Location{
        int x;
        int y;

        public Location(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static class Point implements Comparable<Point>{
        int x;
        int idx;

        public Point(int x, int idx){
            this.x = x;
            this.idx = idx;
        }

        @Override
        public int compareTo(Point o){
            return this.x - o.x;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        Deque<Location> locs = new ArrayDeque<>();
        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            locs.add(new Location(x,y));
        }

        while(true){
            if(locs.peek().y<0){
                break;
            }

            Location now = locs.poll();
            locs.add(now);
        }

        ArrayList<Point> points = new ArrayList<>();
        Location prior = locs.poll();
        int idx = 0;
        int cnt = 0;
        while(!locs.isEmpty()){
            Location now = locs.poll();

            if((prior.y>0 && now.y<0) || (prior.y<0 && now.y>0)){
                points.add(new Point(now.x,idx));

                if(++cnt == 2){
                    cnt = 0;
                    idx++;
                }
            }
        }

        Collections.sort(points);
        Deque<Point> dq = new ArrayDeque<>();
        dq.add(points.get(0));

        int pointCnt = points.size();
        int notOuter = 0;
        int notInner = 0;
        boolean hasInner = false;
        int lastIdx = -1;
        for(int i=1;i<pointCnt;i++){
            Point now = points.get(i);
            if(dq.isEmpty()){
                dq.add(now);
                continue;
            }

            Point left = dq.pollLast();

            if(left.idx != now.idx){
                hasInner = true;
                lastIdx = now.idx;
                dq.add(left);
                dq.add(now);
                continue;
            }

            if(dq.size() == 0){
                if(!hasInner){
                    notInner++;
                }
                hasInner = false;
                notOuter++;
                continue;
            }

            if(lastIdx == now.idx){
                notInner++;
            }
        }

        System.out.println(notOuter+" "+notInner);

    }
}
