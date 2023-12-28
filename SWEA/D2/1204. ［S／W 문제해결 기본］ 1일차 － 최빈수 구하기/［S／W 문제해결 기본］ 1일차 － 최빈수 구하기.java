import java.util.*;
import java.io.*;
 
class Solution
{
    public static HashMap<Integer,Integer> map = new HashMap<>();
     
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
         
        int T = Integer.parseInt(br.readLine());
        for(int test_case = 1; test_case <= T; test_case++)
        {
            int test_case_num = Integer.parseInt(br.readLine());
             
            map.clear();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=0;i<1000;i++){
                int num = Integer.parseInt(st.nextToken());
                map.put(num, map.getOrDefault(num,0)+1);
            }
             
            int maxKey = 0;
            int maxCnt = Integer.MIN_VALUE;
            for(int key:map.keySet()){
                int cnt = map.get(key);
                if(cnt > maxCnt){
                    maxCnt = cnt;
                    maxKey = key;
                }
                else if(cnt == maxCnt){
                    maxKey = Math.max(maxKey, key);
                }
            }
             
            bw.write("#"+test_case_num+" "+maxKey+"\n");
        }
        bw.flush();
         
        br.close();
        bw.close();
    }
}