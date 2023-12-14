public class WeakVertices {
    public static void main(String[] args) {
        Kattio kat = new Kattio(System.in, System.out);
        int numOfVertices = kat.getInt();
        while (numOfVertices != -1) {
            int[][] adjacencyMatrix = new int[numOfVertices][numOfVertices];
            Boolean[] weakMatrix = new Boolean[numOfVertices];
            for (int i = 0; i < numOfVertices; i++) {
                weakMatrix[i] = false;
            }
            for(int i = 0; i < numOfVertices; i++) {
                for(int j = 0; j < numOfVertices; j++) {
                    adjacencyMatrix[i][j] = kat.getInt();
                }
            }
            for(int i = 0; i < numOfVertices; i ++) {
                if (weakMatrix[i] == false) {
                    for (int j = 0; j < numOfVertices; j++) {
                        if(adjacencyMatrix[i][j] == 1) {
                            for(int a = 0; a < numOfVertices; a++) {
                                if (adjacencyMatrix[j][a] == 1 && adjacencyMatrix[a][i] == 1) {
                                    weakMatrix[i] = true;
                                }
                            }
                        }
                    }
                }
            }
            for(int i = 0; i < numOfVertices; i ++) {
                if (weakMatrix[i] == false) {
                    kat.print(i);
                    kat.print(" ");
                }
            }
            kat.println();
            numOfVertices = kat.getInt();
        }
        kat.flush();
    }
}
