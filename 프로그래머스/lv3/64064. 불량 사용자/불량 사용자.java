import java.util.*;

class Solution {
    public static HashMap<String, ArrayList<String>> bannedMap = new HashMap<>();
    public static boolean[] visited;
    public static HashSet<String> answers = new HashSet<>();
    
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        
        for(int i=0;i<banned_id.length;i++){
            String bannedId = banned_id[i];
            
            if(bannedMap.containsKey(bannedId))
                continue;
            
            ArrayList<String> bannedUserIds = new ArrayList<>();
            for(int j=0;j<user_id.length;j++){
                String userId = user_id[j];
                
                if(isBanned(userId, bannedId))
                    bannedUserIds.add(userId);
            }
            bannedMap.put(bannedId, bannedUserIds);
        }
        
        visited = new boolean[user_id.length];
        dfs(user_id, banned_id, 0, "");
        
        return answers.size();
    }
    
    public static void dfs(String[] userIds, String[] bannedIds, int idx, String bannedUserIds){
        if(idx == bannedIds.length){
            String[] ids = bannedUserIds.trim().split(" ");
            Arrays.sort(ids);
            
            String str = "";
            for(String id:ids){
                str += id;
            }
            answers.add(str);
            
            return;
        }
        
        String bannedId = bannedIds[idx];
        for(int i=0;i<userIds.length;i++){
            String userId = userIds[i];
            if(visited[i] || !checkBannedMap(userId, bannedId))
                continue;
            
            visited[i] = true;
            dfs(userIds, bannedIds, idx+1, bannedUserIds+" "+userId);
            visited[i] = false;
        }
    }
    
    public static boolean checkBannedMap(String userId, String bannedId){
        if(bannedMap.get(bannedId).contains(userId))
            return true;
    
        return false;
    }
    
    public static boolean isBanned(String userId, String bannedId){
        if(userId.length() != bannedId.length())
            return false;
        
        int bannedIdLen = bannedId.length();
        for(int i=0;i<bannedIdLen;i++){
            char bannedIdChar = bannedId.charAt(i);
            
            if(bannedIdChar == '*')
                continue;
            
            if(userId.charAt(i) != bannedIdChar)
                return false;
        }
        
        return true;
    }
}