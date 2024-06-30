import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static String A,B,C;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        A = br.readLine();
        B = br.readLine();
        C = br.readLine();
        String AB = A+B;
        
        sb.append(Integer.parseInt(A)+Integer.parseInt(B)-Integer.parseInt(C)).append("\n");
        sb.append(Integer.parseInt((AB))-Integer.parseInt(C));

        bw.write(sb.toString());
        bw.flush();

    }
}
