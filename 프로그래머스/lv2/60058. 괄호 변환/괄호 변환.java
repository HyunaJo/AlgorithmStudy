import java.util.*;

class Solution {
    public String solution(String p) {
        String answer = makeCorrect(p);
        return answer;
    }
    
    public static String makeCorrect(String w){
        if(w.equals("")) // 빈 문자열인 경우
            return ""; // 빈 문자열 return
        
        // 문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리
        String u = "";
        String v = w;
        
        int leftCnt = 0; // '(' 개수
        int rightCnt = 0; // ')' 개수
        for(int charIdx = 0;charIdx<w.length();charIdx++){
            char ch = w.charAt(charIdx);
            
            if(ch=='('){
                leftCnt++;
            }
            else if(ch == ')'){
                rightCnt++;
            }
            
            u += Character.toString(ch);
            v = v.substring(1);
            
            if(leftCnt == rightCnt)
                break;
        }
        
        if(isCorrect(u)){ // 문자열 u가 "올바른 괄호 문자열" 이라면
            // 문자열 v에 대해 1단계부터 다시 수행
            // 수행한 결과 문자열을 u에 이어 붙인 후 반환
            return u + makeCorrect(v);
        }
        
        // 문자열 u가 "올바른 괄호 문자열"이 아니라면
        String tmp = "("+makeCorrect(v)+")";
        u = u.substring(1,u.length()-1);
        
        for(int charIdx=0;charIdx<u.length();charIdx++){
            char ch = u.charAt(charIdx);
            
            if(ch=='('){
                tmp += ")";
            }
            else if(ch==')'){
                tmp += "(";
            }
        }
        
        return tmp;
    }
    
    public static boolean isCorrect(String str){ 
        // 올바른 괄호 문자열인지 판단
        Deque<Character> dq = new ArrayDeque<>();
        
        for(int idx=0;idx<str.length();idx++){
            char ch = str.charAt(idx);
            
            if(ch=='('){
                dq.addLast(ch);
            }
            else if(ch == ')'){
                if(dq.isEmpty())
                    return false;
                dq.pollLast();
            }
        }
        
        if(!dq.isEmpty())
            return false;
        
        return true;
    }
}