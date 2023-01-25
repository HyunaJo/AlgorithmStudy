import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int n = Integer.parseInt(br.readLine());
      Stack<Integer> stack = new Stack<Integer>();
      int i = 0;
      for(i=0;i<n;i++) {
         StringTokenizer st = new StringTokenizer(br.readLine(), " ");
         String cmd = st.nextToken();
         
         try {
            switch(cmd) {
            case "push":
               stack.push(Integer.parseInt(st.nextToken()));
               break;
            case "pop":
               System.out.println(stack.pop());
               break;
            case "size":
               System.out.println(stack.size());
               break;
            case "empty":
               if(stack.empty())
                  System.out.println("1");
               else
                  System.out.println("0");
               break;
            case "top":
               System.out.println(stack.peek());
               break;
            }
         }catch(Exception e) {
            System.out.println("-1");
         }
      }
      br.close();
   }
}