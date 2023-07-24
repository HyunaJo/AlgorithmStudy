import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {
    public static final int MAX_STACK_SIZE = 1000;
    public static final int MAX_CMD_SIZE = 100000;
    public static final int MAX_VALUE = 1000000000;

    public static long[] stack = new long[MAX_STACK_SIZE];
    public static int stackPt;

    // 1 num 2 pop 3 inv 4 dup 5 swp 6 add 7 sub 8 mul 9 div 10 mod
    public static int[] cmdArr = new int[MAX_CMD_SIZE];
    public static int cmdPt = 0;

    public static ArrayList<Integer> numArr = new ArrayList<>();
    public static int numPt = 0;

    public static boolean isQuit = false;
    public static boolean isError = false;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        while(true){
            stackPt = 0;
            cmdPt = 0;
            numPt = 0;
            numArr.clear();

            while(true){
                String input = br.readLine();

                if(input.equals("END"))
                    break;
                else if(input.equals("QUIT")){
                    isQuit = true;
                    break;
                }

                switch (input.split(" ")[0]){
                    case "NUM":
                        cmdArr[cmdPt++] = 1;
                        numArr.add(Integer.parseInt(input.split(" ")[1]));
                        break;
                    case "POP":
                        cmdArr[cmdPt++] = 2;
                        break;
                    case "INV":
                        cmdArr[cmdPt++] = 3;
                        break;
                    case "DUP":
                        cmdArr[cmdPt++] = 4;
                        break;
                    case "SWP":
                        cmdArr[cmdPt++] = 5;
                        break;
                    case "ADD":
                        cmdArr[cmdPt++] = 6;
                        break;
                    case "SUB":
                        cmdArr[cmdPt++] = 7;
                        break;
                    case "MUL":
                        cmdArr[cmdPt++] = 8;
                        break;
                    case "DIV":
                        cmdArr[cmdPt++] = 9;
                        break;
                    case "MOD":
                        cmdArr[cmdPt++] = 10;
                        break;
                }
            }

            if(isQuit){ // 종료
                bw.flush();

                br.close();
                bw.close();

                return;
            }

            int caseCnt = Integer.parseInt(br.readLine());
            for(int i=0;i<caseCnt;i++){
                stackPt = 0;
                numPt = 0;
                isError = false;

                long inputNum = Long.parseLong(br.readLine());
                stack[stackPt++] = inputNum;

                goStack();

                if(stackPt != 1){
                    isError = true;
                }

                if(isError)
                    bw.write("ERROR\n");
                else
                    bw.write(Long.toString(stack[stackPt-1])+"\n");
            }
            bw.write("\n");
        }
    }

    public static void goStack(){
        for(int i=0;i<cmdPt;i++){
            int cmd = cmdArr[i];

            if(cmd == 1){// num
                stack[stackPt++] = numArr.get(numPt++);
            }
            else if(cmd == 2){ // pop
                if(stackPt == 0){
                    isError = true;
                    break;
                }
                stackPt--;
            }
            else if(cmd == 3){ // inv
                if(stackPt == 0){
                    isError = true;
                    break;
                }
                stack[stackPt-1] *= -1;
            }
            else if(cmd == 4){ // dup
                if(stackPt == 0){
                    isError = true;
                    break;
                }
                stack[stackPt] = stack[stackPt-1];
                stackPt++;
            }
            else if(cmd == 5){ // swp
                if(stackPt<=1){
                    isError = true;
                    break;
                }
                long temp = stack[stackPt-1];
                stack[stackPt-1] = stack[stackPt-2];
                stack[stackPt-2] = temp;
            }
            else if(cmd == 6){ // add
                if(stackPt<=1){
                    isError = true;
                    break;
                }

                long addNum = stack[stackPt-1]+stack[stackPt-2];
                if(addNum > MAX_VALUE){
                    isError = true;
                    break;
                }
                stackPt -= 2;
                stack[stackPt++] = addNum;
            }
            else if(cmd == 7){ // sub
                if(stackPt<=1){
                    isError = true;
                    break;
                }

                long subNum = stack[stackPt-2] - stack[stackPt-1];
                if(Math.abs(subNum) > MAX_VALUE){
                    isError = true;
                    break;
                }
                stackPt -= 2;
                stack[stackPt++] = subNum;
            }
            else if(cmd == 8){ // mul
                if(stackPt<=1){
                    isError = true;
                    break;
                }

                long mulNum = stack[stackPt-1]*stack[stackPt-2];
                if(Math.abs(mulNum)>MAX_VALUE){
                    isError = true;
                    break;
                }
                stackPt -= 2;
                stack[stackPt++] = mulNum;
            }
            else if(cmd == 9){ // div
                if(stackPt<=1){
                    isError = true;
                    break;
                }

                if(stack[stackPt-1] == 0){ // 0으로 나눴을 때 에러
                    isError = true;
                    break;
                }

                long first = stack[stackPt-1];
                long second = stack[stackPt-2];

                long divNum = Math.abs(second/first);
                stackPt -= 2;
                if(first*second<0)
                    stack[stackPt++] = -divNum;
                else
                    stack[stackPt++] = divNum;
            }
            else if(cmd == 10){ // mod
                if(stackPt<=1){
                    isError = true;
                    break;
                }

                if(stack[stackPt-1] == 0){ // 0으로 나눴을 때 에러
                    isError = true;
                    break;
                }

                long first = stack[stackPt-1];
                long second = stack[stackPt-2];

                long modNum = Math.abs(second)%Math.abs(first);
                stackPt -= 2;
                if(second<0)
                    stack[stackPt++] = -modNum;
                else
                    stack[stackPt++] = modNum;
            }
        }
    }
}
