import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    public static class Word implements Comparable<Word>{
        int cnt = 0; // 등장 횟수
        String word; // 영단어

        public Word(String word,int cnt) {
            this.word = word;
            this.cnt = cnt;
        }

        public void increaseNum(){
            cnt++;
        }

        @Override
        public int compareTo(Word o) {
            if(this.cnt!=o.cnt)
                return o.cnt-this.cnt; // 등장횟수 내림차순
            else if(this.word.length()!=o.word.length())
                return o.word.length()-this.word.length(); // 단어 길이 내림차순
            else
                return this.word.compareTo(o.word); // 알파벳 사전 순
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 단어의 개수
        int M = Integer.parseInt(st.nextToken()); // 단어의 최소 길이

        ArrayList<Word> words = new ArrayList<>();
        HashMap<String, Integer> wordMap = new HashMap<>();
        for(int i=0;i<N;i++){
            String word = br.readLine();
            if(word.length()>=M){
                if(wordMap.containsKey(word))
                    wordMap.put(word,wordMap.get(word)+1);
                else
                    wordMap.put(word,1);
            }
        }

        for (String key : wordMap.keySet()) {
            words.add(new Word(key, wordMap.get(key)));
        }
        Collections.sort(words);

        for (Word word : words)
            bw.write(word.word+"\n");
        bw.flush();
    }
}
