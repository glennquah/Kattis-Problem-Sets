import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

class JoinStrings{
    static class ListNode {
        private String name;
        private ListNode nextNode;

        ListNode(String name) {
            this.name = name;
            this.nextNode = null;
        }

        ListNode(String name, ListNode nextNode) {
            this.name = name;
            this.nextNode = nextNode;
        }

        public void setNext(ListNode n) {
            this.nextNode = n;
        }
    }

    static class TailedLinkedList {
        private ListNode head;
        private ListNode tail;
        private ListNode curr;
        private int size;

        TailedLinkedList() {
            this.head = null;
            this.tail = null;
            this.size = 0;
        }

        public void addListNode(ListNode node) {
            if (size == 0) {
                this.head = node;
                this.tail = node;
                this.curr = node;
            } else {
                this.tail.setNext(node);
                this.tail = node;
            }
            size++;
        }

        public void addAnotherTailedList(TailedLinkedList secondLL) {
            size += secondLL.size;
            this.tail.setNext(secondLL.head);
            this.tail = secondLL.tail;
        }

        public void printAll(PrintWriter writer) {
            for (int i = 0; i < size; i++) {
                writer.print(this.curr.name);
                this.curr = this.curr.nextNode;
            }
            writer.flush();
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        ArrayList<TailedLinkedList> listOfStrings = new ArrayList<>();

        //read string
        for (int i = 0; i < num; i ++) {
            ListNode tempNode = new ListNode(br.readLine());
            TailedLinkedList tempLinkedList = new TailedLinkedList();
            tempLinkedList.addListNode(tempNode);
            listOfStrings.add(tempLinkedList);
        }
        
        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int listNum = 0;
        if (num == 1) {
            listOfStrings.get(0).printAll(writer);
        } else {
            for (int j = 0; j < num - 1; j++) {
                String[] readLine = br.readLine().split(" ");
                int first = Integer.parseInt(readLine[0]);
                int second = Integer.parseInt(readLine[1]);
                TailedLinkedList firstLL = listOfStrings.get(first - 1);
                TailedLinkedList secondLL = listOfStrings.get(second - 1);
                firstLL.addAnotherTailedList(secondLL);
                listNum = first;
            }
            listOfStrings.get(listNum - 1).printAll(writer);
        }
    }
}