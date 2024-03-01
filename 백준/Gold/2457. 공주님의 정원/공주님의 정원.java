import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static Date START = new Date(3,1);
    static Date END = new Date(11,30);
    static int N;
    static PriorityQueue<Flower> flowers;

    static class Date implements Comparable<Date>{
        int month;
        int day;

        public Date(int month, int day){
            this.month = month;
            this.day = day;
        }

        @Override
        public int compareTo(Date o){
            if(this.month == o.month){
                return this.day - o.day;
            }

            return this.month - o.month;
        }
    }

    static class Flower implements Comparable<Flower>{
        Date start;
        Date end;

        public Flower(Date start, Date end){
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Flower o){
            if(o.end.compareTo(this.end) == 0){
                this.start.compareTo(o.start); // 시작이 더 빠르고
            }

            return o.end.compareTo(this.end); // 종료가 더 느린
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        flowers = new PriorityQueue<>();
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            Date start = new Date(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
            Date end = new Date(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
            flowers.add(new Flower(start,end));
        }

        bw.write(Integer.toString(findMin()));
        bw.flush();
    }

    public static int findMin(){
        int selectedCnt = 1;
        Flower prior = findLastFlower();

        if(prior == null){
            return 0;
        }

        Date end = prior.end;
        Date start = prior.start;

        if(start.compareTo(START)<=0){
            return selectedCnt;
        }

        while(!flowers.isEmpty()){
            Flower flower = flowers.poll();

            if(flower.end.compareTo(prior.end) == 0){
                // 이전 꽃과 지는 날이 같은 경우 -> 피는 날이 이전 꽃보다 느림
                continue;
            }

            if(flower.start.compareTo(prior.start) == 0){
                continue;
            }

            if(flower.end.compareTo(prior.start)<0){
                // 지는 날이 이전 꽃 피는 날보다 이전이거나 같으면
                selectedCnt = 0;
                continue;
            }

            while(!flowers.isEmpty()){ // 가능한 날들 중 가장 먼저 피는 꽃 찾기
                Flower tmp = flowers.poll();

                if(tmp.end.compareTo(prior.start)<0){
                    flowers.add(tmp);
                    break;
                }

                if(tmp.start.compareTo(flower.start)==0){
                    continue;
                }

                if(tmp.start.compareTo(flower.start)<0 ){
                    flower = tmp;
                }
            }

            selectedCnt++;
            start = flower.start;
            if(flower.start.compareTo(START)<=0){
                break;
            }

            prior = flower;
        }

        if(start.compareTo(START)>0){
            return 0;
        }

        return selectedCnt;
    }

    public static Flower findLastFlower(){
        Flower flower = null;
        while(!flowers.isEmpty()){
            Flower tmp = flowers.poll();

            if(tmp.end.compareTo(END)<=0){
                // 지는 날이 11월 30일과 같거나 전날인 경우
                flowers.add(tmp);
                break;
            }

            if(flower == null){
                flower = tmp;
                continue;
            }

            if(tmp.start.compareTo(flower.start)<0){
                flower = tmp;
            }
        }

        return flower;
    }
}

