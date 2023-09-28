import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

class Spelling {
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numOfCases = Integer.parseInt( br.readLine());
        String[] input = new String[numOfCases];
        for (int i = 0; i < numOfCases; i++) {
            input[i] = br.readLine();
        }

        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        //Match ASCII value to numpad
        String[] numPad = new String[256];
        String[] clickableNumpad = new String[] {
            "2", "22", "222", "3", "33", "333", "4", "44", "444", "5", "55", "555", "6", "66", "666", "7", "77", "777", "7777", "8", "88", "888", "9", "99", "999", "9999"
        }; 
        int count = 0;

        for (int i = 97; i <= 122; i++) {
            numPad[i] = clickableNumpad[count];
            count++;
        }


        for (int i = 0; i < numOfCases; i++) {
            writer.printf("Case #%d: ", i + 1);
            int NumFlag = 0;
            for (int j = 0; j < input[i].length(); j++) {
                char currentChar = input[i].charAt(j);
                if (Character.isSpaceChar(currentChar)) {
                    if (NumFlag != 0) {
                        writer.printf("0");
                        NumFlag = 0;
                    } else {
                        writer.printf(" 0");
                        NumFlag = 0;
                    }
                } else {
                    int ASCII = (int) currentChar;
                    int output = Integer.parseInt(numPad[ASCII]);
                    if (NumFlag != output % 10) {
                        writer.print(output);
                        NumFlag = output % 10;
                    } else {
                        writer.printf(" %s", numPad[ASCII]);
                        NumFlag = output % 10;
                    }
                }
            }
            writer.println();
        }
        writer.flush();
    }
}