import java.util.ArrayList;
import java.util.Stack;

class Dominos {
    public static void main(String[] args) {
        Kattio kat = new Kattio(System.in, System.out);
        int numOfTCs = kat.getInt();

        for (int i = 0; i < numOfTCs; i ++) {
            int numOfDoms = kat.getInt();
            int numOfLines = kat.getInt();
            DominoChain domChain = new DominoChain(numOfDoms);
            for (int j = 0; j < numOfLines; j++) {
                int firstDom = kat.getInt();
                int secondDom = kat.getInt();
                domChain.addDominoFalling(firstDom, secondDom);
            }
            int num = domChain.getStronglyConnectedComponent();
            kat.println(num);
        }
        kat.flush();
    }

    public static class DominoChain {
        private int numOfDom;
        @SuppressWarnings("Unchecked")
        private ArrayList<ArrayList<Integer>> adjList;

        public DominoChain(int numOfDom) {
            this.numOfDom = numOfDom;
            this.adjList = new ArrayList<ArrayList<Integer>>();
            for (int i = 0; i < numOfDom; i ++) {
                adjList.add(new ArrayList<Integer>());
            }
        }

        public void addDominoFalling(int firstDom, int secondDom) {
            adjList.get(firstDom - 1).add(secondDom - 1);
        }

        public int getStronglyConnectedComponent() {
            Stack<Integer> stack = new Stack<Integer>();
            boolean visited[] = new boolean[numOfDom];
            //all visited should be false

            //topo sort logic
            for (int i = 0; i < numOfDom; i++) {
                if (visited[i] == false) {
                    DFS(i, visited, stack);
                }
            }

            int count = 0;
            boolean[] knockedDown = new boolean[numOfDom];
            while(stack.empty() == false) {
                int node = stack.pop();
                if (!knockedDown[node]) {
                    knockDown(node, knockedDown);
                    count++;
                }
            }

            return count;
        }

        //TOPO SORT!
        public void DFS(int i, boolean visited[], Stack<Integer> stack) {
            ArrayList<Integer> edgesOut = adjList.get(i);
            for (int j = 0; j < edgesOut.size(); j ++) {
                int nextNode = edgesOut.get(j);
                if (!visited[nextNode]) {
                    //System.out.println("pop");
                    visited[nextNode] = true;
                    DFS(nextNode, visited, stack);
                }
            }
            stack.push(i);            
        }

        //TO POP BACK THE STACK OF TOPO & CHECK FOR STORNGLY CONNECTED COMPONENT
        public void knockDown(int node, boolean[] knockedDown) {
            knockedDown[node] = true;
            for (int i = 0; i < adjList.get(node).size(); i++) {
                int neighbour = adjList.get(node).get(i);
                if (!knockedDown[neighbour]) {
                    knockDown(neighbour, knockedDown);
                }
            }
        }
    }
}


