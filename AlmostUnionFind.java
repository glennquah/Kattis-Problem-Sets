class AlmostUnionFind {

    static class UnionFind {
        public int[] parentMove;
        public int[] parentUnion;
        public int[] count;
        public long[] sum;   

        UnionFind(int N) {
            parentMove = new int[N + 1];
            parentUnion = new int[N + 1];
            count = new int[N + 1];
            sum = new long[N + 1];

            for (int i = 1; i <= N; i ++) {
                parentMove[i] = i;
                parentUnion[i] = i;
                count[i] = 1;
                sum[i] = i;
            }
        }

        public int findSet(int i) { 
            int numMoveParent = parentMove[i];
            int numUnion = parentUnion[numMoveParent];
            while (numMoveParent != numUnion) {
                parentUnion[numMoveParent] = parentUnion[numUnion];
                numMoveParent = parentUnion[numMoveParent];
                numUnion = parentUnion[numMoveParent];
            }
            return numMoveParent;
        }

        public boolean isSameSet(int i, int j) {
            return findSet(i) == findSet(j);
        }

        public void unionSet(int i, int j) {
            if (!isSameSet(i, j)) {
                int x = findSet(i);
                int y = findSet(j);
                parentUnion[y] = x;
                sum[x] += sum[y];
                count[x] += count[y];
            } 
        } 

        public void moveToSet(int i, int j) {
            if (!isSameSet(i, j)) {
                int y = findSet(j);
                int x = findSet(i);
                sum[x] = sum[x] - i;
                count[x] = count[x] - 1;
                parentMove[i] = y;
                sum[y] += i;
                count[y]++;
            }
        }


        public void printElementsAndSum(int i, Kattio kat) {
            int parentSet = findSet(i);
            kat.println(count[parentSet] + " " + sum[parentSet]);
        }
    }
    
    public static void main(String[] args) {
        Kattio kat = new Kattio(System.in, System.out);
        while(kat.hasMoreTokens()) {
            int numOfInt = kat.getInt();
            int numOfCommands = kat.getInt();
            UnionFind disJointSets = new UnionFind(numOfInt);

            for (int i = 0; i < numOfCommands; i ++) {
                int command = kat.getInt();
                int p = kat.getInt();
                if (command == 1) {
                    int q = kat.getInt();
                    disJointSets.unionSet(p, q );
                } else if (command == 2) {
                    int q = kat.getInt();
                    disJointSets.moveToSet(p, q);
                } else {
                    //System.out.println(p);
                    disJointSets.printElementsAndSum(p, kat);
                }
            }
        }
        kat.flush();
    }    
}
