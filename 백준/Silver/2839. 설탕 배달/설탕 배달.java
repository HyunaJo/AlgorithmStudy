import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        int answer = 0;
        while (N > 0) {
            if (N % 5 == 0) {
                answer += N / 5;
                break;
            }

            N -= 3;
            answer++;
        }

        if (N < 0) {
            bw.write("-1");
        } else {
            bw.write(Integer.toString(answer));
        }
        bw.flush();

        br.close();
        bw.close();
    }
}
