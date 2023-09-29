import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
class IntegerLists {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int num = Integer.parseInt(br.readLine());
        for (int i = 0; i < num; i ++) {
            String line1 = br.readLine();
            int sizeOfArray = Integer.parseInt(br.readLine());
            String input = br.readLine();
            input = input.substring(1, input.length() - 1);
            String[] listOfArrays = input.split(",");
            Deque<String> queue = new ArrayDeque<>(listOfArrays.length);
            for (String s : listOfArrays) {
                if (!s.isEmpty()) {
                    queue.add(s);
                }
            }


            boolean flag = false;
            boolean reverseFlag = true;

            for (int j = 0; j < line1.length(); j++) {
                if (line1.charAt(j) == 'R') {
                    reverseFlag = !reverseFlag;
                } else if (queue.size() > 0 && reverseFlag) {
                    queue.removeFirst();
                } else if (queue.size() > 0 && !reverseFlag) {
                    queue.removeLast();
                } else {
                    writer.println("error");
                    flag = true;
                    break;
                }
            }
            if (!reverseFlag) {
                List<String> list = new ArrayList<>(queue);
                Collections.sort(list, new Comparator<String>() {
                    @Override
                    public int compare(String s1, String s2) {
                        return -1;
                    }
                } );
                queue.clear();
                queue.addAll(list);
            }
            if (flag == false) {
                StringBuilder result = new StringBuilder("[");
                while (!queue.isEmpty()) {
                    result.append(queue.poll());
                    if (!queue.isEmpty()) {
                        result.append(",");
                    }
                }
                result.append("]");
                writer.println(result.toString());
            }
        }
        writer.flush();
    }
}
