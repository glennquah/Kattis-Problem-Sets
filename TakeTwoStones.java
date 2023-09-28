import java.util.Scanner;

class TakeTwoStones {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numStones = sc.nextInt();

        if (numStones % 2 != 0) {
            System.out.print("Alice");
        } else {
            System.out.print("Bob");
        }
    }
}
