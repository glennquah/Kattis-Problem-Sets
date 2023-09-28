import java.util.Scanner;

class Skener {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int R = sc.nextInt();
        int C = sc.nextInt();
        int Zr = sc.nextInt();
        int Zc = sc.nextInt();

        sc.nextLine();

        char[][] small = new char[R][C];
        for (int i = 0 ; i < R; i ++) {
            String line = sc.nextLine();
            for (int j = 0; j < C; j++) {
                small[i][j] = line.charAt(j);
            }
        }

        sc.close();
        for (int i = 0; i < R * Zr; i++) {
            for (int j = 0; j < C * Zc; j++) {
                System.out.print(small[(i/Zr)][j/Zc]);
            }
            System.out.println();
        }
    }    
}
