import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int V,E;
    static ArrayList<Edge> edges = new ArrayList<>();
    static int[] parents;

    static class Edge implements Comparable<Edge>{
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o){
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }

        parents = new int[V+1];
        for(int i=1;i<=V;i++){
            parents[i] = i;
        }

        Collections.sort(edges);
        bw.write(Integer.toString(findMin()));
        bw.flush();

        br.close();
        bw.close();
    }

    public static int findMin(){
        int weightSum = 0;
        int cnt = 0;

        for(Edge edge:edges){
            if(!union(edge.from,edge.to)){
                continue;
            }

            weightSum += edge.weight;
            if(++cnt == V){
                break;
            }
        }

        return weightSum;
    }

    public static boolean union(int a, int b){
        a = findParent(a);
        b = findParent(b);

        if(a == b){
            return false;
        }

        if(a<b){
            parents[b] = a;
            return true;
        }
        parents[a] = b;
        return true;
    }

    public static int findParent(int a){
        if(a == parents[a]){
            return a;
        }

        return parents[a] = findParent(parents[a]);
    }
}
