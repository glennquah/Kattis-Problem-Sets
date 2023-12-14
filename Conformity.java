import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

class Confirmity {
    public static void main(String[] args) throws IOException {
        Kattio kat = new Kattio(System.in, System.out);
        int num = kat.getInt();
        HashMap<String, Integer> hash = new HashMap<>();
        int countOfPopularCourses = 0;
        int count = 0;

        for (int i = 0; i < num; i++) {
            StringBuilder sb = new StringBuilder();
            int[] line = new int[5];
            for (int j = 0; j < 5; j++) {
                line[j] = kat.getInt();
            }
            Arrays.sort(line);
            for (int j = 0; j < 5; j++) {
                sb.append(line[j]);
            }
            //System.out.println(sb.toString());
            String lineString = sb.toString();
            if (!hash.containsKey(lineString)) {
                hash.put(lineString, 1);
                if (1 > countOfPopularCourses) {
                    countOfPopularCourses = 1;
                }
            } else {
                int value = hash.get(lineString);
                value++;
                hash.replace(lineString, value);
                if (value > countOfPopularCourses) {
                    countOfPopularCourses = value;
                    count = 1;
                } else if (value == countOfPopularCourses) {
                    count++;
                }
            }
        }
        if (countOfPopularCourses == 1) {
            kat.println(num);
        } else {
            kat.println(countOfPopularCourses * count);
        }
        kat.close();
    }
}