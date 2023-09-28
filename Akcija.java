import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


class Akcija {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        List<Integer> books = new ArrayList<Integer>();
        for (int i = 0; i < num; i ++) {
            books.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(books, new Comparator<Integer>() {
            @Override
            public int compare(Integer b1, Integer b2) {
                return b2 - b1;
            }
        });
        int total = 0;
        int count = 1;
        for (int i = 0; i < books.size(); i++) {
            if (count != 3) {
                total += books.get(i);
                count++;
            } else {
                count = 1;
            }
        }
        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        writer.println(total);
        writer.flush();
    }    
}
