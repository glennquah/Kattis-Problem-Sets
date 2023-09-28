import java.util.Scanner;

class Autori {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        String ans = "" + input.charAt(0);

        for (int i = 1; i < input.length(); i++) {
            if (Character.isUpperCase(input.charAt(i)) && input.charAt(i-1) == '-') {
                ans += input.charAt(i);
            }
        }

        System.out.print(ans);
    }

}