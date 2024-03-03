import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    static class Vertex implements Comparable<Vertex> {
        int index;
        long weight;

        public Vertex(int index, long weight) {
            this.index = index;
            this.weight = weight;
        }

        @Override
        public int compareTo(Vertex v) {
            return Long.compare(this.weight, v.weight);
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer stn;
    static int N, M, from, to;
    static long weight;
    static boolean[] sight, isVisited;
    static List<Vertex>[] adjList;
    static long[] minWeight;
    static PriorityQueue<Vertex> pqueue = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        stn = new StringTokenizer(br.readLine());

        N = Integer.parseInt(stn.nextToken());
        M = Integer.parseInt(stn.nextToken());

        minWeight = new long[N];
        isVisited = new boolean[N];
        sight = new boolean[N];
        stn = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            if (Integer.parseInt(stn.nextToken()) == 0) {
                continue;
            }
            sight[i] = true;
        }

        adjList = new List[N];
        for (int i = 0; i < N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            stn = new StringTokenizer(br.readLine());

            from = Integer.parseInt(stn.nextToken());
            to = Integer.parseInt(stn.nextToken());
            weight = Long.parseLong(stn.nextToken());

            adjList[from].add(new Vertex(to, weight));
            adjList[to].add(new Vertex(from, weight));
        }

        Arrays.fill(minWeight, Long.MAX_VALUE);
        minWeight[0] = (long)0;
        isVisited[0] = true;
        for (Vertex nextVertex : adjList[0]) {
            if (sight[nextVertex.index] && !(nextVertex.index == N - 1)) {
                continue;
            }
            if (isVisited[nextVertex.index]) {
                continue;
            }
            minWeight[nextVertex.index] = nextVertex.weight;
            pqueue.add(nextVertex);
        }

        while (!pqueue.isEmpty()) {
            Vertex nearVertex = pqueue.poll();

            if (sight[nearVertex.index] && !(nearVertex.index == N - 1)) {
                continue;
            }
            if (isVisited[nearVertex.index]) {
                continue;
            }
//            if (minWeight[nearVertex.index] < nearVertex.weight) {
//                continue;
//            }
            isVisited[nearVertex.index] = true;
//            minWeight[nearVertex.index] = nearVertex.weight;
//            if (nearVertex.index == N - 1) {
//                break;
//            }
            for (Vertex nextVertex : adjList[nearVertex.index]) {
                if (isVisited[nextVertex.index]) {
                    continue;
                }
                if (minWeight[nextVertex.index] < minWeight[nearVertex.index] + nextVertex.weight) {
                    continue;
                }
                if (sight[nextVertex.index] && !(nextVertex.index == N - 1)) {
                    continue;
                }
                pqueue.add(new Vertex(nextVertex.index,minWeight[nearVertex.index] + nextVertex.weight));
                minWeight[nextVertex.index] = minWeight[nearVertex.index] + nextVertex.weight;
            }
        }

        if (minWeight[N - 1] == Long.MAX_VALUE) {
            bw.write(Integer.toString(-1));
            bw.flush();
            bw.close();
            br.close();
            return;
        }
        bw.write(Long.toString(minWeight[N - 1]));
        bw.flush();
        bw.close();
        br.close();
    }
}

