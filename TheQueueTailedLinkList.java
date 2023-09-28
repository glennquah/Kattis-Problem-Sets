import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

class TheQueueTailedLinkList{

    static class ListNode {
        private int num;
        private ListNode nextNode;

        ListNode(int num) {
            this.num = num;
            this.nextNode = null;
        }

        public void setNext(ListNode n) {
            this.nextNode = n;
        }

        public int getNum() {
            return this.num;
        }

    }

    static class TailedLinkedList {
        private ListNode head;
        private ListNode tail;
        private int size;

        TailedLinkedList() {
            this.head = null;
            this.tail = null;
            this.size = 0;
        }

        public void addFront(ListNode node) {
            if (size == 0) {
                this.head = node;
                this.tail = node;
            } else {
                node.setNext(this.head);
                this.head = node;
            }
            this.size++;
        }

        public void addBack(ListNode node) {
            if (size == 0) {
                this.head = node;
                this.tail = node;
            } else {
                this.tail.setNext(node);
                this.tail = node;
            }
            this.size++;
        }

        public void addMiddle(ListNode node) {
            if (size == 0) {
                this.head = node;
                this.tail = node;
            } else {
                int mid = (size + 1) / 2;
                ListNode curr = head;
                int count = 0;
                while(count != mid - 1) {
                    curr = curr.nextNode;
                    count++;
                }
                node.setNext(curr.nextNode);
                curr.setNext(node);
            }
            size++;
        }

        public int get(int num) {
            int count = 0;
            ListNode curr = head;
            while (count != num) {
                curr = curr.nextNode;
                count++;
            }
            return curr.getNum();
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int numOfCommands = Integer.parseInt(br.readLine());
        TailedLinkedList TequeList = new TailedLinkedList();

        for(int i = 0; i < numOfCommands; i++) {
            String[] line = br.readLine().split(" ");
            String command = line[0];
            int num = Integer.parseInt(line[1]);
            if (command.equals("push_back")) {
                TequeList.addBack(new ListNode(num));
            } else if (command.equals("push_front")) {
                TequeList.addFront(new ListNode(num));
            } else if (command.equals("push_middle")) {
                TequeList.addMiddle(new ListNode(num));
            } else {
                writer.println(TequeList.get(num));
            }
        }
        writer.flush();
    }    
}
