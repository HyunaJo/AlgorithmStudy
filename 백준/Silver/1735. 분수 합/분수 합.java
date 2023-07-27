import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static int firstA, firstB, secondA, secondB;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        firstA = Integer.parseInt(st.nextToken());
        firstB = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        secondA = Integer.parseInt(st.nextToken());
        secondB = Integer.parseInt(st.nextToken());

        int sumA = firstA*secondB + firstB*secondA;
        int sumB = firstB*secondB;

        if(sumA ==0){
            bw.write("0 1");
        }
        else {
            int divider = find(sumB,sumA);
            sumA /= divider;
            sumB /= divider;
            bw.write(Integer.toString(sumA)+" "+Integer.toString(sumB));
        }

        bw.flush();

        br.close();
        bw.close();
    }

    public static int find(int a, int b){
        if (b>a)
            return find(b,a);

        if(a%b==0)
            return b;
        else
            return find(b, a%b);
    }
}
