import java.util.Scanner;

class NumberFun {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        Double[][] diffNumbers = new Double[num][3];
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < 3; j++) {
                diffNumbers[i][j] = sc.nextDouble();
            }
        }

        boolean poss = false;

        for (int i = 0; i < diffNumbers.length; i++) {
            poss = false;
            if (diffNumbers[i][0] + diffNumbers[i][1] == diffNumbers[i][2] ) {
                poss = true;
            } else if (diffNumbers[i][0] - diffNumbers[i][1] == diffNumbers[i][2] || diffNumbers[i][1] - diffNumbers[i][0] == diffNumbers[i][2]) {
                poss = true;
            } else if (diffNumbers[i][0] * diffNumbers[i][1] == diffNumbers[i][2] ) {
                poss = true;
            } else if (diffNumbers[i][0] / diffNumbers[i][1] == diffNumbers[i][2] || diffNumbers[i][1] / diffNumbers[i][0] == diffNumbers[i][2] ) {
                poss = true;
            }

            if (poss) {
                System.out.println("Possible");
            } else {
                System.out.println("Impossible");
            }
        }
    }
}    

