import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static int N,L,R; // NxN 크기 땅, 인구차이 L명 이상, R명 이하
    static int[][] population;
    static boolean[][] visited;
    static ArrayList<Node> changeNode = new ArrayList<>();

    public static class Node{
        public int x;
        public int y;

        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        population = new int[N][N];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                population[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int moveCnt = 0;
        while(true){
            boolean isMoved = false;
            visited = new boolean[N][N];

            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(!visited[i][j]){
                        changeNode.clear();
                        int sum = checkBorder(i,j);
                        if(changeNode.size()>1){
                            isMoved = true;
                            int newPopulation = sum/changeNode.size();
                            for(Node node:changeNode){
                                population[node.x][node.y] = newPopulation;
                            }
                        }
                    }
                }
            }

            if(!isMoved)
                break;
            moveCnt++;
        }

        bw.write(Integer.toString(moveCnt));
        bw.flush();
    }

    public static int checkBorder(int x, int y){
        visited[x][y] = true;
        changeNode.add(new Node(x,y));

        int sum = 0;
        sum+=population[x][y];

        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(x,y));
        while(!queue.isEmpty()){
            Node now = queue.remove();

            for(int i=0;i<4;i++){
                int newX = now.x + dx[i];
                int newY = now.y + dy[i];

                if(newX>=0 && newX<N && newY>=0 && newY<N){
                    if(!visited[newX][newY]){
                        int diff = Math.abs(population[newX][newY]-population[now.x][now.y]);
                        if(diff>=L && diff<=R){
                            visited[newX][newY] = true;
                            sum += population[newX][newY];
                            queue.add(new Node(newX,newY));
                            changeNode.add(new Node(newX,newY));
                        }
                    }
                }
            }
        }
        return sum;
    }
}
