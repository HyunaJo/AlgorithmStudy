import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static final int FOLDER = 1;
    static final int FILE = 0;
    static int N,M;
    static HashMap<String, Integer> folderIdxMap;
    static Folder[] folders;
    static HashSet<String> fileNames;
    static int fileTotalCnt, fileTypeCnt;
    static StringBuilder sb = new StringBuilder();

    static class Folder{
        ArrayList<String> subfolders;
        HashSet<String> subfiles;
        int subfileCnt;

        public Folder(){
            subfolders = new ArrayList<>();
            subfiles = new HashSet<>();
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        folderIdxMap = new HashMap<>();
        folders = new Folder[N+1];
        int folderIdx = 0;

        for(int i=0;i<N+M;i++){
            st = new StringTokenizer(br.readLine());
            String P = st.nextToken(); // 상위 폴더 이름
            String F = st.nextToken(); // 폴더 또는 파일 이름
            int C = Integer.parseInt(st.nextToken()); // 폴더인지 파일인지

            // 상위 폴더 인덱스
            int idx = folderIdxMap.getOrDefault(P,-1);
            if(idx == -1){
                idx = folderIdx;
                folderIdxMap.put(P, folderIdx);
                folders[folderIdx++] = new Folder();
            }

            if(C == FILE){ // 파일인 경우
                folders[idx].subfiles.add(F);
                folders[idx].subfileCnt++;
                continue;
            }

            // 폴더인 경우
            folders[idx].subfolders.add(F);
        }

        int Q = Integer.parseInt(br.readLine());
        while(Q-->0){
            String[] inputs = br.readLine().split("/");
            String target = inputs[inputs.length-1];

            fileNames = new HashSet<>();
            fileTotalCnt = 0;
            dfs(target);
            fileTypeCnt = fileNames.size();
            sb.append(fileTypeCnt).append(" ").append(fileTotalCnt).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    public static void dfs(String folderName){
        int idx = folderIdxMap.getOrDefault(folderName,-1);

        if(idx == -1){
            return;
        }

        Folder folder = folders[idx];
        fileTotalCnt += folder.subfileCnt;
        fileNames.addAll(folder.subfiles);

        for(String subfolder:folder.subfolders){
            dfs(subfolder);
        }
    }
}
