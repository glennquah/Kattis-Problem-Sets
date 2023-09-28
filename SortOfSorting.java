// Quah Han Rong, Glenn
// A0254560H / E0959230

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

class SortOfSorting {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        List<String> sortedNames = new ArrayList<String>();
        while (num != 0) {
            List<String> namesToSort = new ArrayList<String>();
            for (int i = 0; i < num; i++) {
                namesToSort.add(br.readLine());
            }
            
            Collections.sort(namesToSort, new Comparator<String>() {
                @Override
                public int compare(String s1, String s2) {
                    if (s1.charAt(0) > s2.charAt(0)) {
                        return 1;
                    } else if (s2.charAt(0) > s1.charAt(0)) {
                        return -1;
                    } else {
                        if (s1.charAt(1) > s2.charAt(1)) {
                            return 1;
                        } else if (s2.charAt(1) > s1.charAt(1)) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }
                }
            });

            for (int i = 0; i < num; i++) {
                sortedNames.add(namesToSort.get(i));
            }
            sortedNames.add("");
            num = Integer.parseInt(br.readLine());
        }
        sortedNames.remove(sortedNames.size() - 1);
        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        for (int i = 0; i < sortedNames.size(); i++) {
            writer.println(sortedNames.get(i));
        }
        writer.flush();
    }
}