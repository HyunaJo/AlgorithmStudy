import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static final int LEFT = 0;
    static final int RIGHT = 1;
    static int M,t,N;
    static PriorityQueue<Person> leftQueue = new PriorityQueue<>();
    static PriorityQueue<Person> rightQueue = new PriorityQueue<>();
    static int boatLoc = LEFT;
    static long[] reachedTime;
    static long time;
    static Deque<Person> ridingDeque = new ArrayDeque<>();
    static StringBuilder sb = new StringBuilder();

    static class Person implements Comparable<Person>{
        int idx;
        long waitingTime;

        public Person(int idx, long waitingTime){
            this.idx = idx;
            this.waitingTime = waitingTime;
        }

        @Override
        public int compareTo(Person o) {
            if(this.waitingTime == o.waitingTime){
                return this.idx - o.idx;
            }
            return Long.compare(this.waitingTime, o.waitingTime);
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken()); // 한번에 최대 M명
        t = Integer.parseInt(st.nextToken()); // 다른 정박장으로 t시간
        N = Integer.parseInt(st.nextToken()); // 손님 수
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            int waitingTime = Integer.parseInt(st.nextToken());
            if(st.nextToken().equals("left")){
                leftQueue.add(new Person(i,waitingTime));
                continue;
            }

            rightQueue.add(new Person(i,waitingTime));
        }

        reachedTime = new long[N];
        time = 0;
        move();
        for(long answer:reachedTime){
            sb.append(answer).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
    }

    public static void move(){
        while(!leftQueue.isEmpty() || !rightQueue.isEmpty()){
            if(boatLoc == LEFT){
                process(leftQueue, rightQueue);
                continue;
            }
            process(rightQueue, leftQueue);
        }
    }

    public static void process(PriorityQueue<Person> pq, PriorityQueue<Person> oppositePq){
        // 현재 정박장에서 더이상 태울 사람 없는 경우
        if(pq.isEmpty()){ // 반대 정박장으로 이동
            boatLoc = (boatLoc+1)%2;
            time = Math.max(oppositePq.peek().waitingTime, time);
            time += t;
            return;
        }

        // 현재 위치에서 탈 수 있는 경우
        if(pq.peek().waitingTime <= time){
            ride(pq);
            time += t;
            saveTime();
            boatLoc = (boatLoc+1)%2;
            return;
        }

        // 반대쪽 사람이 먼저 오는 경우
        if(!oppositePq.isEmpty() && pq.peek().waitingTime > oppositePq.peek().waitingTime){
            if(time < oppositePq.peek().waitingTime){
                // 반대쪽 사람 도착 시간이 아직 안된 경우
                time = oppositePq.peek().waitingTime;
            }
            time += t; // 반대로 이동하는 시간
            ride(oppositePq); // 탑승 시작
            time += t; // 현재 위치로 다시 이동하는 시간
            saveTime(); // 도착 시간 저장
            return;
        }

        // 현재 위치 사람이 먼저 오는데 탑승시간이 아닌 경우
        if(pq.peek().waitingTime > time){
            time = pq.peek().waitingTime;
        }
        ride(pq);
        time += t;
        saveTime();
        boatLoc = (boatLoc+1)%2;
    }

    public static void ride(PriorityQueue<Person> pq){
        while(!pq.isEmpty()){
            if(pq.peek().waitingTime > time){
                break;
            }

            ridingDeque.add(pq.poll());
            if(ridingDeque.size() == M){
                break;
            }
        }
    }

    public static void saveTime(){
        while(!ridingDeque.isEmpty()){
            Person person = ridingDeque.poll();
            reachedTime[person.idx] = time;
        }
    }
}
