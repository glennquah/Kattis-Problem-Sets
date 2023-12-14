class Island {

    static class Node {
        private int x;
        private int y;
        private char type;
        
        public Node(int x, int y, char type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }
    public static void main(String[] args) {
        Kattio kat = new Kattio(System.in, System.out);
        int row = kat.getInt();
        int col = kat.getInt();
        char[][] grid = new char[row][col];
        boolean[][] visited = new boolean[row][col];

        for(int i = 0; i < row; i ++) {
            String s = kat.getWord();
            for(int j = 0; j < s.length(); j++) {
                grid[i][j] = s.charAt(j);
            }
        }
        
        int numOfLands = 0;

         for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (!visited[i][j] && grid[i][j] == 'L') {
                    numOfLands++;
                    bfs(grid, visited, new Node(i, j, grid[i][j]));
                }
            }
        }
        kat.print(numOfLands);
        kat.flush();
    }

    public static void bfs(char[][] grid, boolean[][] visited, Node node) {
        if (node.type == 'L' || (node.type == 'C')) {
            visited[node.x][node.y] = true;
            if (node.y < grid[0].length - 1 && !visited[node.x][node.y + 1]) {
                bfs(grid, visited, new Node(node.x, node.y + 1, grid[node.x][node.y + 1]));
            }
            if (node.y > 0 && !visited[node.x][node.y - 1]) {
                bfs(grid, visited, new Node(node.x, node.y - 1, grid[node.x][node.y - 1]));
            }
            if (node.x > 0 && !visited[node.x - 1][node.y]) {
                bfs(grid, visited, new Node(node.x - 1, node.y, grid[node.x - 1][node.y]));
            }
            if (node.x < grid.length - 1 && !visited[node.x + 1][node.y]) {
                bfs(grid, visited, new Node(node.x + 1, node.y, grid[node.x + 1][node.y]));
            }
        }                    
    }
}