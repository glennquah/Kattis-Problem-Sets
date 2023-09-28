import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

class FizzBuzz {

    public static void main(String[] args) throws NumberFormatException, IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int x = Integer.parseInt(br.readLine());
        int y = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());

        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        for (int i = 1; i <= n; i++) {
            if (i % x == 0) {
                writer.printf("Fizz");
            } 
            if (i % y == 0) {
                writer.printf("Buzz");
            } 
            if (i % x != 0 && i % y != 0 ) {
                writer.print(i);
            }
            if (i != n) {
                writer.println();
            }
        }
        writer.flush();
    }
}

