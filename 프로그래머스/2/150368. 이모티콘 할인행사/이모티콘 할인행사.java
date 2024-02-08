class Solution {
    static int[] sales = {10,20,30,40};
    static int userCnt, emoticonCnt;
    static int maxSubscriber, maxProfit;
    static int[] appliedSales;
    
    public int[] solution(int[][] users, int[] emoticons) {
        userCnt = users.length;
        emoticonCnt = emoticons.length;
        appliedSales = new int[emoticonCnt];
        
        maxSubscriber = 0;
        maxProfit = 0;
        applySales(0, users, emoticons);
        
        int[] answer = {maxSubscriber, maxProfit};
        return answer;
    }
    
    public static void applySales(int cnt, int[][] users, int[] emoticons){
        if(cnt == emoticonCnt){
            getResult(users, emoticons);
            // System.out.println(maxSubscriber+" "+maxProfit);
            return;
        }
        
        for(int i=0;i<4;i++){
            appliedSales[cnt] = sales[i];
            applySales(cnt+1, users, emoticons);
        }
    }
    
    public static void getResult(int[][] users, int[] emoticons){
        int subscriber = 0;
        int profit = 0;
        
        for(int[] user:users){
            int totalPrice = 0;
            
            for(int i=0;i<emoticonCnt;i++){
                if(user[0] > appliedSales[i]){
                    continue;
                }
                totalPrice += emoticons[i]/100*(100-appliedSales[i]);
            }
            
            if(totalPrice >= user[1]){
                subscriber++;
                continue;
            }
            profit += totalPrice;
        }
        if(subscriber>maxSubscriber){
            maxSubscriber = subscriber;
            maxProfit = profit;
            return;
        }
        
        if(subscriber == maxSubscriber){
            maxProfit = Math.max(maxProfit,profit);
        }
    }
}