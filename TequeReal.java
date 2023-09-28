import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;

class TequeReal {
    static class Queue {
        private final HashMap<Integer, Integer> frontQueue;
        private final HashMap<Integer, Integer> backQueue;
        private  int frontQueueHead;
        private  int frontQueueTail;
        private  int backQueueHead;
        private  int backQueueTail;

        //order is
        /*
         * 1. frontQueueHead -> 0
         * 2. frontQueueTail -> -1
         * 3. backQueueHead -> 0
         * 4. backQueueTail -> -1
         * frontQueueHead -> frontQueueTail -> backQueueHead -> backQueueTail
         */

        Queue(HashMap<Integer, Integer> frontQueue, HashMap<Integer, Integer> backQueue,
                  int frontQueueHead, int frontQueueTail,
                  int backQueueHead, int backQueueTail) {
            this.frontQueue = frontQueue;
            this.backQueue = backQueue;
            this.frontQueueHead = frontQueueHead;
            this.frontQueueTail = frontQueueTail;
            this.backQueueHead = backQueueHead;
            this.backQueueTail = backQueueTail;
        }

        public int getFrontQueueSize() {
            return this.frontQueue.size();
        }

        public int getBackQueueSize() {
            return this.backQueue.size();
        }

        public void addFront(int num) {
            this.frontQueue.put(this.frontQueueHead, num);
            this.frontQueueHead++;
            if (getFrontQueueSize() > getBackQueueSize() + 1) {
                this.backQueue.put(this.backQueueHead, this.frontQueue.get(this.frontQueueTail + 1));
                this.frontQueueTail++;
                this.backQueueHead++;
                this.frontQueue.remove(this.frontQueueTail);
            }
        }

        public void addBack(int num) {
            this.backQueue.put(this.backQueueTail, num);
            this.backQueueTail--;
            if (getBackQueueSize() > getFrontQueueSize()) {
                this.frontQueue.put(this.frontQueueTail, this.backQueue.get(this.backQueueHead - 1));
                this.frontQueueTail--;
                this.backQueueHead--;
                this.backQueue.remove(this.backQueueHead);
            }
        }

        public void addMiddle(int num) {
            if (getBackQueueSize() == getFrontQueueSize()) {
                this.frontQueue.put(this.frontQueueTail, num);
                this.frontQueueTail--;
            } else {
                this.backQueue.put(this.backQueueHead, num);
                this.backQueueHead++;
            }
        }

        public int getNum(int num) {
            int newNum = num + 1;
            if (newNum <= getFrontQueueSize()) {
                return this.frontQueue.get(this.frontQueueHead - newNum);
            } else {
                newNum = newNum - getFrontQueueSize();
                return this.backQueue.get(this.backQueueHead - newNum);
            }
        }
        
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int numOfCommands = Integer.parseInt(br.readLine());
        HashMap<Integer, Integer> frontQueue = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> backQueue = new HashMap<Integer, Integer>();
        Queue FQueue = new Queue(frontQueue, backQueue, 0, -1, 0, -1);

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