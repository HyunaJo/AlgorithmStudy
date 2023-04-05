import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

// 자료구조 - q4949 (균형잡힌 세상)
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        ArrayList<String> results = new ArrayList<>();

        while(true){
            String sentence = br.readLine();
            if(sentence.equals(".")) // 온점 하나만 입력한 경우 while문 종료
                break;

            if(isBalanced(sentence))
                bw.write("yes\n");
            else
                bw.write("no\n");
        }

        bw.flush();
    }

    public static boolean isBalanced(String sentence){ // 균형잡혔는지 판단
        Stack<Character> stack = new Stack<Character>();

        for(int i=0;i<sentence.length()-1;i++){ // 문장의 마지막은 무조건 .이므로 그 전까지만 판단
            char letter = sentence.charAt(i);
            switch (letter){ // [,],(,)인 경우 push,pop 수행
                case '[':
                case '(':
                    stack.push(letter);
                    break;
                case ']':
                    if(stack.isEmpty())
                        return false;
                    else {
                        if (!stack.pop().equals('['))
                            return false;
                    }
                    break;
                case ')':
                    if(stack.isEmpty())
                        return false;
                    else {
                        if (!stack.pop().equals('('))
                            return false;
                    }
                    break;
            }
        }
        return stack.isEmpty();
    }
}
