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

class PasswordHacking {

    static class Password {
        private final String pass;
        private final double prob;

        Password(String pass, double prob) {
            this.pass = pass;
            this.prob = prob;
        }

        public String getPass() {
            return this.pass;
        }

        public double getProb() {
            return this.prob;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        List<Password> listOfPass = new ArrayList<Password>();
        for (int i = 0 ; i < num; i ++) {
            String[] line = br.readLine().split(" ");
            listOfPass.add(new Password(line[0], Double.parseDouble(line[1])));
        }
        Collections.sort(listOfPass, new Comparator<Password>() {
            @Override
            public int compare(Password p1, Password p2) {
                if (Double.compare(p2.prob, p1.prob) > 0) {
                    return 1;
                } else if (Double.compare(p2.prob, p1.prob) < 0) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        int count = 1;
        double attempts = 0;
        for (int i = 0; i < num; i ++) {
            attempts += count * listOfPass.get(i).getProb();
            count++;
        }
        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        writer.printf("%.4f", attempts);
        writer.flush();
    }    
}
