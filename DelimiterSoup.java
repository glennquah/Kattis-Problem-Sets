import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Stack;

class DelimiterSoup {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int num = Integer.parseInt(br.readLine());

        int wrongNum = -1;
        char wrongChar = 0;
        Stack<Character> bracketStack = new Stack();
        String line = br.readLine();
        for (int i = 0; i < line.length(); i++) {
            //ASKII Value for { : 123
            //ASKII Value for } : 125
            //ASKII Value for [ : 91
            //ASKII Value for ] : 93
            //ASKII Value for ( : 40
            //ASKII Value for ) : 41
            char tempChar = line.charAt(i);
            if (tempChar == 123 || tempChar == 91 || tempChar == 40) {
                bracketStack.add(tempChar);
            } else if (bracketStack.size() != 0 && tempChar != ' ') {
                if (tempChar == 125) {
                    if (bracketStack.peek() == 123) {
                        bracketStack.pop();
                    } else {
                        wrongChar = tempChar;
                        wrongNum = i;
                        break;
                    }
                } else if (tempChar == 93) {
                    if (bracketStack.peek() == 91) {
                        bracketStack.pop();
                    } else {
                        wrongChar = tempChar;
                        wrongNum = i;
                        break;
                    }
                } else if (tempChar == 41) {
                    if (bracketStack.peek() == 40) {
                        bracketStack.pop();
                    } else {
                        wrongChar = tempChar;
                        wrongNum = i;
                        break;
                    }
                }
            } else if (bracketStack.size() == 0 && tempChar != ' ') {
                wrongChar = tempChar;
                wrongNum = i;
                break;
            }
        }
        if (wrongNum == -1) {
            writer.print("ok so far");
        } else {
            writer.printf("%s %s", wrongChar, wrongNum);
        }
        writer.flush();
    }
}
 

