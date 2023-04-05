import java.io.*;
import java.util.*;

// 정렬 - q18870 (좌표 압축)
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> points = new ArrayList<>();
        for(int i=0;i<N;i++)
            points.add(Integer.parseInt(st.nextToken()));

        HashSet<Integer> uniquePoints = new HashSet<>(points);
        ArrayList<Integer> orders = new ArrayList<>(uniquePoints);
        Collections.sort(orders);

        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0;i<orders.size();i++)
            map.put(orders.get(i),i);

        for(int i=0;i<N;i++)
            bw.write(map.get(points.get(i))+" ");


        bw.flush();
    }
}
