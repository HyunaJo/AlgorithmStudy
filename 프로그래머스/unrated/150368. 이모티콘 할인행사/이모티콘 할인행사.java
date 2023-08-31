class Solution {
    public static int emoticonCnt = 0; // 이모티콘 총 개수
    public static int userCnt = 0; // 사용자 수
    public static int[] discount = {10,20,30,40}; // 할인율
    public static int[] appliedDiscount;
    
    public static int answerRegisterCnt = 0;
    public static int answerPaySum = 0;
    
    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = new int[2];
        
        emoticonCnt = emoticons.length;
        userCnt = users.length;
        
        appliedDiscount = new int[emoticonCnt];
        findMax(users, emoticons, 0);
        
        answer[0] = answerRegisterCnt;
        answer[1] = answerPaySum;
        return answer;
    }
    
    public static void findMax(int[][] users, int[] emoticons, int emoticonIdx){
        if(emoticonIdx == emoticonCnt){
            int registerCnt = 0; // 서비스 가입자 수
            int paySum = 0; // 이모티콘 판매액
            
            for(int[] user: users){
                int userPay = 0;
                // System.out.println(user[0]+","+user[1]);
                
                for(int i=0;i<emoticonCnt;i++){
                    if(user[0]<=appliedDiscount[i]){ // 사용자가 구매하는 경우
                        userPay += (emoticons[i]/100)*(100-appliedDiscount[i]);
                    }
                }
                
                if(userPay>=user[1]){ // 서비스 가입하는 경우
                    registerCnt++;
                }
                else{ // 이모티콘 구매하는 경우
                    paySum += userPay;
                }
            }
            
            if(answerRegisterCnt<registerCnt){
                answerRegisterCnt = registerCnt;
                answerPaySum = paySum;
            }
            else if(answerRegisterCnt==registerCnt && answerPaySum < paySum){
                answerPaySum = paySum;
            }
            
            return;
        }
        
        for(int i=0;i<4;i++){
            appliedDiscount[emoticonIdx] = discount[i];
            findMax(users, emoticons, emoticonIdx+1);
        }
        
    }
    
    public static void getResult(){
        
    }
}