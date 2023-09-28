import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;

class Teque {
    static class Queue {
        private int queueNumInFront;
        private int queueNumFromBack;

        Queue(int queueNumInFront, int queueNumFromBack) {
            this.queueNumInFront = queueNumInFront;
            this.queueNumFromBack = queueNumFromBack;
        }

        public void queueFromFront() {
            this.queueNumInFront++;
        }

        public void queueFromBack() {
            this.queueNumFromBack--;
        }

        public void deQueueFromFront() {
            this.queueNumInFront--;
        }

        public void deQueueFromBack() {
            this.queueNumFromBack++;
        }

        public int getQueueInFrontNum() {
            return this.queueNumInFront;
        }

        public int getQueueFromBackNum() {
            return this.queueNumFromBack;
        }

        public int getTotalQueueSize() {
            return this.queueNumInFront + Math.abs(queueNumFromBack + 1);
        }

    }

    static class FullQueue {
        private final HashMap<Integer, Integer> frontQueue;
        private final HashMap<Integer, Integer> backQueue;
        private final Queue frontQueueNum;
        private final Queue backQueueNum;

        FullQueue(HashMap<Integer, Integer> frontQueue,
                  HashMap<Integer, Integer> backQueue,
                  Queue frontQueueNum,
                  Queue backQueueNum) {
            this.frontQueue = frontQueue;
            this.backQueue = backQueue;
            this.frontQueueNum = frontQueueNum;
            this.backQueueNum = backQueueNum;
        }

        public void addFront(int n) {
            frontQueue.put(frontQueueNum.queueNumInFront, n);
            if (frontQueueNum.getTotalQueueSize() > backQueueNum.getTotalQueueSize()) {
                //System.out.println("hi");
                backQueue.put(backQueueNum.queueNumFromBack, frontQueue.get(frontQueueNum.queueNumFromBack + 1));
                frontQueueNum.deQueueFromBack();
                backQueueNum.queueFromBack();
                frontQueue.remove(frontQueueNum.queueNumFromBack);
            }
            frontQueueNum.queueFromFront();
        }

        public void addBack(int n) {
            backQueue.put(backQueueNum.queueNumInFront, n);
            //System.out.println("why nvr print this");
            if (frontQueueNum.getTotalQueueSize() + 1 < backQueueNum.getTotalQueueSize()) {
                frontQueue.put(frontQueueNum.queueNumFromBack, backQueue.get(backQueueNum.queueNumFromBack + 1));
                frontQueueNum.deQueueFromBack();
                backQueueNum.queueFromBack();
                backQueue.remove(backQueueNum.queueNumFromBack);
            }
            backQueueNum.queueFromFront();
        }

        public void addMiddle(int n) {
            if (frontQueueNum.getTotalQueueSize() + backQueueNum.getTotalQueueSize() == 0) {
                //System.out.println("1");
                frontQueue.put(frontQueueNum.queueNumFromBack, n);
                frontQueueNum.queueFromBack();
            } else if (frontQueueNum.getTotalQueueSize() == backQueueNum.getTotalQueueSize()) {
                //System.out.println("POPOOP");
                //System.out.println("the hash no is 2nd else " + frontQueueNum.queueNumFromBack);
                backQueue.put(backQueueNum.queueNumFromBack, n);
                backQueueNum.queueFromBack();
            } else {
                //System.out.println("ASD");
                //System.out.println("the hash no is 1st else " + backQueueNum.queueNumFromBack);
                frontQueue.put(frontQueueNum.queueNumFromBack, backQueue.get(backQueueNum.queueNumFromBack + 1));
                backQueueNum.deQueueFromBack();
                frontQueueNum.queueFromBack();
                backQueue.remove(backQueueNum.queueNumFromBack);
                backQueue.put(backQueueNum.queueNumFromBack, n);
                backQueueNum.queueFromBack();
            }
        }

        public int getNum(int n) {
            int count = frontQueueNum.getTotalQueueSize();
            //System.out.println("count is " + count);
            //System.out.println("front queue size is " + frontQueueNum.getTotalQueueSize());
            //System.out.println("back queue size is " + backQueueNum.getTotalQueueSize());
            if (n < frontQueueNum.getTotalQueueSize()) {
                //System.out.print("hash for this is");
                //System.out.println(frontQueueNum.queueNumInFront - n - 1);
                return frontQueue.get(frontQueueNum.queueNumInFront - n - 1);
            } else {
                n = n - count;
                //System.out.println("counttttt is" + n);
                //System.out.print("hash for this is");
                //System.out.println(backQueueNum.queueNumFromBack + 1 + n);
                return backQueue.get(backQueueNum.queueNumFromBack + 1 + n);
            }
        }
        
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int numOfCommands = Integer.parseInt(br.readLine());
        HashMap<Integer, Integer> frontQueue = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> backQueue = new HashMap<Integer, Integer>();
        FullQueue FQueue = new FullQueue(frontQueue, backQueue, new Queue(0, -1), new Queue(0, -1));

        for(int i = 0; i < numOfCommands; i++) {
            String[] line = br.readLine().split(" ");
            String command = line[0];
            int num = Integer.parseInt(line[1]);

            if (command.equals("push_back")) {
                FQueue.addBack(num);
            } else if (command.equals("push_front")) {
                FQueue.addFront(num);
            } else if (command.equals("push_middle")) {
                FQueue.addMiddle(num);
            } else {
                writer.println(FQueue.getNum(num));
            }
        }
        writer.flush();
    }    
}