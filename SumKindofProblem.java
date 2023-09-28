import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;


class SumKindofProblem {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        for (int i = 1; i <= num; i++) {
            String[] answer = br.readLine().split(" ");
            writer.print(Integer.parseInt(answer[0]));
            int ans = Integer.parseInt(answer[1]);
            int a = 0;
            int b = 0;
            int c = 0;
            int count = 1;
            int count2 = 2;
            for(int j = 1; j <= ans; j++) {
                a += j;
                b += count;
                count = count + 2;
                c += count2;
                count2 = count2 + 2;
            }
            writer.printf(" %s ", a);
            writer.printf("%s ", b);
            writer.printf("%s ", c);
            writer.println("");
        }
        writer.flush();
    }
}
