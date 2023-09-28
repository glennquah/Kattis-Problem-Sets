import java.util.Scanner;

class StuckInATimeLoop {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        for (int i = 1; i <= num; i ++) {
            System.out.println(i + " Abracadabra");
        }
        sc.close();
    }
}
