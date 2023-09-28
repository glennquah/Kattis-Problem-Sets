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


class AClassyProblem {

    static class PersonClass  {
        public final String name;
        public final String[] numClass;

        PersonClass(String name, String[] numClass) {
            this.name = name;
            this.numClass = numClass;
        }

        public String[] getNumClass() {
            return this.numClass;
        }

        public String getName() {
            return this.name;
        }

        public int[] getClassNumber() {
            int[] classNum = new int[10];
            for (int i = 0; i < numClass.length; i++) {
                if (numClass[i].equals("upper")) {
                    classNum[i] = 3;
                } else if (numClass[i].equals("middle")) {
                    classNum[i] = 2;
                } else {
                    classNum[i] = 1;
                } 
            }
            for (int i = numClass.length; i < 10; i++) {
                classNum[i] = 2;
            }
            return classNum;
        }

    }

    static class classComparator implements Comparator<PersonClass> {
        @Override
        public int compare(PersonClass p1, PersonClass p2) {
            int[] p1class = p1.getClassNumber();
            int[] p2class = p2.getClassNumber();
            for (int i = 0; i < 10; i ++) {
                if (p1class[i] > p2class[i]) {
                    return -1;
                } else if (p1class[i] < p2class[i]) {
                    return 1;
                } 
            } 
            return p1.getName().compareTo(p2.getName());
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numCases = Integer.parseInt(br.readLine());
        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < numCases; i ++) {
            int numPeople = Integer.parseInt(br.readLine());
            List<PersonClass> listOfPersonClasses = new ArrayList<PersonClass>();
            for (int j = 0; j < numPeople; j++) {
                String[] classDetails = br.readLine().split(" ");
                String[] indeptClassDetails = classDetails[1].split("-");
                listOfPersonClasses.add(new PersonClass(classDetails[0].replace(":", ""), indeptClassDetails));
            }
            Collections.sort(listOfPersonClasses, new classComparator());
            for (int j = 0; j < listOfPersonClasses.size(); j++) {
                output.append(listOfPersonClasses.get(j).getName()).append("\n");
            }
            output.append("==============================\n");
        }
        writer.print(output.toString());
        writer.flush();
    }    
}
