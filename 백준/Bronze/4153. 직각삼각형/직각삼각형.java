import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static ArrayList<Integer> length;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        length = new ArrayList<>();
        while(true){
            length.clear();
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if(a==0 && b==0 && c==0){
                break;
            }

            length.add(a);
            length.add(b);
            length.add(c);
            Collections.sort(length);
            if(Math.pow(length.get(2),2) == Math.pow(length.get(0),2)+Math.pow(length.get(1),2)){
                bw.write("right\n");
            }
            else{
                bw.write("wrong\n");
            }
        }
        bw.flush();

        br.close();
        bw.close();
    }
}
