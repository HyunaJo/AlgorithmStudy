import java.util.*;

class Solution {
    public static HashMap<Integer,String> hexMap= new HashMap<>();
    
    public String solution(int n, int t, int m, int p) {
        makeHexMap(); // 10~15 알파벳 저장
        
        int ansCnt = 0;
        int num = 0;
        int order = 0;
        String answer = "";
        
        if(m==p)
            p = 0;
        
        while(ansCnt<t){
            String str = changeNum(n, num);
            
            int strLen = str.length();
            for(int i=0;i<strLen;i++){
                order = (order+1) % m;
                if(order == p){ // 튜브 순서인 경우
                    answer += Character.toString(str.charAt(i));
                    ansCnt++;
                    
                    if(ansCnt == t) // t만큼 구한 경우
                        break;
                }
            }
            num++;
        }
        
        return answer;
    }
    
    public static void makeHexMap(){
        hexMap.put(10,"A");
        hexMap.put(11,"B");
        hexMap.put(12,"C");
        hexMap.put(13,"D");
        hexMap.put(14,"E");
        hexMap.put(15,"F");
    }
    
    public static String changeNum(int n, int num){
        if(num == 0)
            return "0";
        
        String str = ""; // num을 n진수로 변경한 값(문자열)
        
        while(num>0){
            int tmp = num%n; // 나머지
            if(tmp<10)
                str = Integer.toString(tmp) + str;
            else
                str = hexMap.get(tmp) + str;
            
            num /= n;
        }
        
        return str;
    }
    
}