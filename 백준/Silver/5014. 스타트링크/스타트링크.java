import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int F,S,G,U,D;
    static boolean[] visited;
    static int[] movings;

    static class Floor implements Comparable<Floor>{
        int floor;
        int cnt;

        public Floor(int floor, int cnt){
            this.floor = floor;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Floor o) {
            return this.cnt - o.cnt;
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        F = Integer.parseInt(st.nextToken()); // 스타트링크 총 층수
        S = Integer.parseInt(st.nextToken()); // 강호 현재 위치
        G = Integer.parseInt(st.nextToken()); // 스타트링크가 있는 곳의 위치
        U = Integer.parseInt(st.nextToken()); // 위로 U층 가는 버튼
        D = Integer.parseInt(st.nextToken()); // 아래로 D층 가는 버튼

        movings = new int[]{U,-1*D};
        visited = new boolean[F+1];
        int min = findMin();
        bw.write((min==-1)?"use the stairs":Integer.toString(min));
        bw.flush();
    }

    public static int findMin(){
        PriorityQueue<Floor> pq = new PriorityQueue<>();
        pq.add(new Floor(S,0));

        visited[S] = true;
        while(!pq.isEmpty()){
            Floor now = pq.poll();
            if(now.floor == G){
                return now.cnt;
            }

            for(int i=0;i<2;i++){
                int moving = movings[i];
                int nextFloor = now.floor + moving;

                if(!isValid(nextFloor)){
                    continue;
                }

                if(visited[nextFloor]){
                    continue;
                }

                visited[nextFloor] = true;
                pq.add(new Floor(nextFloor,now.cnt+1));
            }
        }
        return -1;
    }

    public static boolean isValid(int floor){
        return floor>0 && floor<=F;
    }
}
