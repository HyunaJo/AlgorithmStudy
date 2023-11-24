import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static int N, M;
    public static HashMap<String, HashSet<String>> folders;
    public static HashMap<String, HashSet<String>> files;
    public static ArrayList<String> answers;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 폴더의 총 개수
        M = Integer.parseInt(st.nextToken()); // 파일의 총 개수
        folders = new HashMap<>();
        files = new HashMap<>();

        for(int i=0;i<N+M;i++){
            st = new StringTokenizer(br.readLine());
            String P = st.nextToken(); // 상위 폴더 이름
            String F = st.nextToken(); // 폴더 또는 파일 이름
            int C = Integer.parseInt(st.nextToken()); // 파일(0)인지 폴더(1)인지

            if(C==0){ // 파일
                if(!files.containsKey(P)){
                    files.put(P,new HashSet<>());
                }

                files.get(P).add(F);
            }
            else{ // 폴더
                if(!folders.containsKey(P)){
                    folders.put(P,new HashSet<>());
                }

                folders.get(P).add(F);
            }
        }

        answers = new ArrayList<>();
        int Q = Integer.parseInt(br.readLine());
        while(Q-->0){
            String path = br.readLine();
            String[] pathFolders = path.split("/");


            HashSet<String> subFiles = new HashSet<>();
            int fileCnt = dfs(pathFolders[pathFolders.length-1], 0, subFiles);
            bw.write(subFiles.size()+" "+fileCnt+"\n");
        }
        bw.flush();

        br.close();
        bw.close();
    }

    public static int dfs(String folderName, int fileCnt, HashSet<String> subFiles){
        HashSet<String> subFolders = folders.get(folderName);

        if(files.containsKey(folderName)){
            for(String subFile:files.get(folderName)){
                subFiles.add(subFile);
                fileCnt++;
            }
        }

        if(folders.containsKey(folderName)){
            for(String subFolder:subFolders){
                fileCnt = dfs(subFolder, fileCnt, subFiles);
            }
        }

        return fileCnt;
    }
}
