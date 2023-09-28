import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

class ShatteredCake {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int width = Integer.parseInt(br.readLine());
        int num = Integer.parseInt(br.readLine());
        int area = 0;
        for (int i = 0; i < num; i ++) {
            String[] line = br.readLine().split(" ");
            int cakeLength = Integer.parseInt(line[0]);
            int cakeWidth = Integer.parseInt(line[1]);
            area += cakeLength * cakeWidth;
        }
        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int length = area / width;
        writer.print(length);
        writer.flush();
    }
}