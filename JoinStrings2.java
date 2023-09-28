import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;

class JoinStrings{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        ArrayList<LinkedList<String>> listOfStrings = new ArrayList<>();
        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        //read string
        for (int i = 1; i <= num; i ++) {
            String name = br.readLine();
            LinkedList<String> tempLinkList = new LinkedList<String>();
            tempLinkList.add(name);
            listOfStrings.add(tempLinkList);
        }

        int largestList = 0;
        int largestListNum = 0;

        if (num == 0) {
            listOfStrings.get(0).get(0);
        } else {
            for (int j = 0; j < num - 1; j++) {
                String[] readLine = br.readLine().split(" ");
                int first = Integer.parseInt(readLine[0]);
                int second = Integer.parseInt(readLine[1]);
                LinkedList<String> firstLL = listOfStrings.get(first - 1);
                LinkedList<String> secondLL = listOfStrings.get(second - 1);
                firstLL.addAll(secondLL);
                secondLL.clear();
                if (firstLL.size() > largestList) {
                    largestList = firstLL.size();
                    largestListNum = first;
                }
            }
        }


        for (int i = 0; i < listOfStrings.get(largestListNum - 1).size(); i++) {
            writer.print(listOfStrings.get(largestListNum - 1).get(i));
        }

        writer.flush();
    }
}