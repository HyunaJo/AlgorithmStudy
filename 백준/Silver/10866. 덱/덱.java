import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

// 자료구조 - q10866(덱)
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine()); // 명령의 수 N

        Deque<Integer> deque = new ArrayDeque<>();
        ArrayList<String> outputs = new ArrayList<>();
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
                case "push_front":
                    deque.addFirst(Integer.parseInt(st.nextToken()));
                    break;
                case "push_back":
                    deque.addLast(Integer.parseInt(st.nextToken()));
                    break;
                case "pop_front":
                    if (deque.isEmpty())
                        outputs.add("-1");
                    else
                        outputs.add(Integer.toString(deque.removeFirst()));
                    break;
                case "pop_back":
                    if (deque.isEmpty())
                        outputs.add("-1");
                    else
                        outputs.add(Integer.toString(deque.removeLast()));
                    break;
                case "size":
                    outputs.add(Integer.toString(deque.size()));
                    break;
                case "empty":
                    if (deque.isEmpty())
                        outputs.add("1");
                    else
                        outputs.add("0");
                    break;
                case "front":
                    if (deque.isEmpty())
                        outputs.add("-1");
                    else
                        outputs.add(Integer.toString(deque.getFirst()));
                    break;
                case "back":
                    if (deque.isEmpty())
                        outputs.add("-1");
                    else
                        outputs.add(Integer.toString(deque.getLast()));
                    break;
            }
        }

        int i=0;
        for(i=0;i<outputs.size()-1;i++)
            bw.write(outputs.get(i)+"\n");
        bw.write(outputs.get(i));
        bw.flush();
    }
}