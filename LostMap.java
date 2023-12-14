import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LostMap {

    public static class Edge {
        private int from;
        private int to;
        private int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
    public static void main(String[] args) {
        Kattio kat = new Kattio(System.in, System.out);
        int numOfVillages = kat.getInt();
        List<Edge> listOfEdges = new ArrayList<>();



        for (int i = 0 ; i < numOfVillages; i ++) {
            for (int j = 0; j < numOfVillages; j ++) {
                int weight = kat.getInt();
                listOfEdges.add(new Edge(i, j, weight));
            }
        }

        //sort edge in ascending order
        listOfEdges.sort(new Comparator<Edge>() {
            @Override public int compare(Edge edge1, Edge edge2) {
                return edge1.weight - edge2.weight;
            }
        });

        //KRUSKAL MST
        int subsets[] = new int[numOfVillages];
        for (int i = 0; i < numOfVillages; i++) {
            subsets[i] = i;
        }
        int numOfEdges = 0;

        for (int i = 0; numOfEdges < numOfVillages - 1; i++) {
            //start with the smallest edge
            Edge thisEdge = listOfEdges.get(i);
            //System.out.println(numOfEdges);
            // for cycle detection
            int a = findMainParent(subsets, thisEdge.from);
            int b = findMainParent(subsets, thisEdge.to);

            if (a != b) {
                kat.print(thisEdge.from + 1);
                kat.print(" ");
                kat.print(thisEdge.to + 1);
                kat.println();
                subsets[a] = b;
                numOfEdges++;
            }
        }
        kat.flush();
    }

    public static int findMainParent(int[] subsets, int vill) {
        if (subsets[vill] == vill) {
            return subsets[vill];
        } else {
            //recursion to find the main parent
            subsets[vill] = findMainParent(subsets, subsets[vill]);
            return subsets[vill];
        }
    } 
}