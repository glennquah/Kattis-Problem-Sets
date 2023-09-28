import java.util.Scanner;

class Pot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int[] nums = new int[num];
        for (int i = 0; i < num; i ++) {
            nums[i] = sc.nextInt();
        }

        int ans = 0;
        int ansTemp = 0;
        int pow = 0;
        for (int i = 0; i < num; i ++) {
            pow = 0;
            pow = nums[i] % 10;
            if (pow == 0) {
                ansTemp = 1;
            } else {
                nums[i] = nums[i] / 10;
                ansTemp = nums[i];
                for (int j = 1; j < pow; j++) {
                    ansTemp = ansTemp * nums[i];
            }
        }
            ans += ansTemp;
        }

        System.out.print(ans);
    }    
}
