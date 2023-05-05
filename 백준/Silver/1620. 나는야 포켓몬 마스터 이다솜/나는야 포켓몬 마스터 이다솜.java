import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 도감에 수록된 포켓몬 개수
        int M = Integer.parseInt(st.nextToken()); // 맞춰야하는 문제의 개수

        HashMap<Integer, String> IntStringmap = new HashMap<>();
        HashMap<String,Integer> StringIntmap = new HashMap<>();
        for(int i=0;i<N;i++) {
            String name = br.readLine();
            IntStringmap.put(i + 1, name);
            StringIntmap.put(name, i + 1);
        }

        for(int i=0;i<M;i++){
            String question = br.readLine();
            try{
                bw.write(IntStringmap.get(Integer.parseInt(question))+"\n");
            }catch (Exception e){
                bw.write(Integer.toString(StringIntmap.get(question))+"\n");
            }
        }

        bw.flush();
    }
}
