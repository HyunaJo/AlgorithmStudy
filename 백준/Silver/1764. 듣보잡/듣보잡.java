import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

// 정렬 - q1764 (듣보잡)
public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N =  Integer.parseInt(st.nextToken()); // 듣도 못한 사람의 수
        int M = Integer.parseInt(st.nextToken()); // 보도 못한 사람의 수

        HashMap<String,Integer> people = new HashMap<>(); // 듣도 못한 사람
        ArrayList<String> duplicatedPeople = new ArrayList<>(); // 보도 듣도 못한 사람
        for(int i=0;i<N;i++)
            people.put(br.readLine(),0);

        for (int i=0;i<M;i++) {
            String name = br.readLine();
            if (people.containsKey(name)) // 듣도 못한 사람에도 있는 경우
                duplicatedPeople.add(name);
        }

        Collections.sort(duplicatedPeople);
        int size = duplicatedPeople.size();
        if(size>0){ // 보고 듣도 못한 사람이 있는 경우
            bw.write(Integer.toString(size)+"\n");
            
            int i=0;
            for(i=0;i<size-1;i++){
                bw.write(duplicatedPeople.get(i)+"\n");
            }
            bw.write(duplicatedPeople.get(i));
        }
        else
            bw.write(Integer.toString(size));
        bw.flush();
    }
}
