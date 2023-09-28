import java.util.Scanner;

class DetailedDifferences {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        String[][] lines = new String[num][2];

        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[0].length; j++) {
                lines[i][j] = sc.next();
            }
        }

        String[] ans = new String[num];
        for (int i = 0; i < lines.length; i++) {
            ans[i] = "";
            for (int j = 0; j < lines[i][0].length(); j++) {
                if (lines[i][0].charAt(j) == (lines[i][1].charAt(j))) {
                    ans[i] = ans[i] + ".";
                } else {
                    ans[i] = ans[i] + "*";
                }
            }
        }

        for (int i = 0; i < lines.length; i++) {
            for (int j = 0; j < lines[0].length; j++) {
                System.out.println(lines[i][j]);
                if (j == lines[0].length - 1) {
                    System.out.println(ans[i]);
                    System.out.println();
                }
            }
        }
    }    
}
