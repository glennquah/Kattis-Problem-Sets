import java.util.Scanner;

class LastFactorialDigit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numTries = sc.nextInt();
        int ans = 0;
        int[] arrDigits = new int[numTries];

        for (int i = 0; i < numTries; i ++) {
            arrDigits[i] = sc.nextInt();
        }

        for (int i = 0; i < numTries; i ++) {
            ans = 1;
            for (int j = 1; j <= arrDigits[i]; j++) {
                ans = ans * j;
            }
            
            System.out.println(ans % 10);
        }
    }
}
