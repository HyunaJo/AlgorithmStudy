import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};

    static int N,M; // 성의 크기
    static int T; // 제한 시간
    static int[][] map; // 맵
    static boolean[][][] visited; // 방문 유무 -> [][][0]-검 없는 경우, [][][1]-검 있는 경우

    public static class Node{
        int x;
        int y;
        int dist;
        boolean hasSword;

        public Node(int x, int y, int dist, boolean hasSword){
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.hasSword = hasSword;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M][2];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        int dist = savePrincess();
        if(dist == -1)
            bw.write("Fail");
        else
            bw.write(Integer.toString(dist));

        bw.flush();
    }

    public static int savePrincess(){
        visited[0][0][0] = true;

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0,0, 0, false));

        while(!queue.isEmpty()){
            Node now = queue.remove();

            if(now.dist > T) break;
            if(now.x==N-1 && now.y==M-1)
                return now.dist;

            for(int i=0;i<4;i++){
                int newX = now.x + dx[i];
                int newY = now.y + dy[i];

                if(newX>=0 && newX<N && newY>=0 && newY<M){ // 좌표
                    if(!now.hasSword){ // 검 가지고 있지 않은 경우
                        if(map[newX][newY]!=1){ // 마법벽 아닌 경우
                            if(!visited[newX][newY][0]) { // 처음 간 위치
                                visited[newX][newY][0] = true;
                                if(map[newX][newY] == 2) // 검 있는 경우
                                    queue.add(new Node(newX, newY, now.dist + 1, true));
                                else // 검 없는 경우
                                    queue.add(new Node(newX, newY, now.dist + 1, false));
                            }
                        }
                    }
                    else{ // 검 가지고 있는 경우
                        if (!visited[newX][newY][1]) {
                            visited[newX][newY][1] = true;
                            queue.add(new Node(newX, newY, now.dist + 1, true));
                        }
                    }
                }
            }
        }

        return -1;
    }
}
