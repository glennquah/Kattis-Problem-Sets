import java.util.PriorityQueue;

class MillionaireMadness {
    public static void main(String[] args) {
        Kattio kat = new Kattio(System.in, System.out);
        int rowSize = kat.getInt();
        int colSize = kat.getInt();
        int[][] vault = new int[rowSize][colSize];
        

        //put it in a 2day array
        for (int i = 0; i < rowSize; i ++) {
            for (int j = 0; j < colSize; j++) {
                vault[i][j] = kat.getInt();
            }
        }

        boolean[][] visitedNode = new boolean[rowSize][colSize];
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                visitedNode[i][j] = false;
            }
        }
        PriorityQueue<NodeEdge> minHeap = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        visitedNode[0][0] = true;
        // This code is adding the initial edges to the priority queue (minHeap) for the algorithm to start.
        // //add right to PQ
        // if (colSize > 1) {
        //     minHeap.add(new NodeEdge(0, 0, 0, 1, vault[0][1] - vault[0][0]));
        // }
        // //add bottom to PQ
        // if (rowSize > 1) {
        //     minHeap.add(new NodeEdge(0, 0, 1, 0, vault[0][1] - vault[0][0]));
        // }
        minHeap.add(new NodeEdge(0, 0, 0, 0, 0));

        int ladder = 0;
        //add all of the possible edges
        while(!visitedNode[rowSize - 1][colSize - 1] ) {
            NodeEdge nodeEdge = minHeap.poll();
            visitedNode[nodeEdge.toX][nodeEdge.toY] = true;
            int heightDiff = nodeEdge.weight;
            ladder = Math.max(ladder, heightDiff);
                if (nodeEdge.toY > 0 && !visitedNode[nodeEdge.toX][nodeEdge.toY - 1]) {
                    //if it is not at the left hand side, add NodeEdge to the left
                    //System.out.println("go left");
                    minHeap.add(new NodeEdge(nodeEdge.toX, nodeEdge.toY, nodeEdge.toX, nodeEdge.toY - 1, vault[nodeEdge.toX][nodeEdge.toY - 1] - vault[nodeEdge.toX][nodeEdge.toY]));
                }
                if (nodeEdge.toY < colSize - 1 && !visitedNode[nodeEdge.toX][nodeEdge.toY + 1]) {
                    //if it is not in the right hand side, add Node to the right
                    //System.out.println("go right");
                    minHeap.add(new NodeEdge(nodeEdge.toX, nodeEdge.toY, nodeEdge.toX, nodeEdge.toY + 1, vault[nodeEdge.toX][nodeEdge.toY + 1] - vault[nodeEdge.toX][nodeEdge.toY]));
                }
                if (nodeEdge.toX > 0 && !visitedNode[nodeEdge.toX - 1][nodeEdge.toY]) {
                    //if it is not at the top, add top
                    //System.out.println("go top");
                    minHeap.add(new NodeEdge(nodeEdge.toX, nodeEdge.toY, nodeEdge.toX - 1, nodeEdge.toY, vault[nodeEdge.toX - 1][nodeEdge.toY] - vault[nodeEdge.toX][nodeEdge.toY]));
                }
                if (nodeEdge.toX < rowSize - 1 && !visitedNode[nodeEdge.toX + 1][nodeEdge.toY]) {
                    //System.out.println("go bottom");
                    //if is not at the bottom, add bottom
                    minHeap.add(new NodeEdge(nodeEdge.toX, nodeEdge.toY, nodeEdge.toX + 1, nodeEdge.toY, vault[nodeEdge.toX + 1][nodeEdge.toY] - vault[nodeEdge.toX][nodeEdge.toY]));
                }
        }

        kat.print(ladder);
        kat.flush();
    }

    static class NodeEdge {
        int fromX;
        int fromY;
        int toX;
        int toY;
        int weight;

        NodeEdge(int fromX, int fromY, int toX, int toY, int weight) {
            this.fromX = fromX;
            this.fromY = fromY;
            this.toX = toX;
            this.toY = toY;
            this.weight = weight;
        }
    }
}