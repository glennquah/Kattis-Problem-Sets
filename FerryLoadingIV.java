import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

class FerryLoadingIV {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int num = Integer.parseInt(br.readLine());
        for (int i = 0; i < num; i ++) {
            String[] line1 = br.readLine().split(" ");
            int ferryLength = Integer.parseInt(line1[0]) * 100;
            int numOfCars = Integer.parseInt(line1[1]);
            Queue<Integer> leftQueue = new LinkedList<Integer>();
            Queue<Integer> rightQueue = new LinkedList<Integer>();
            for (int j = 0; j < numOfCars; j++) {
                String[] line2 = br.readLine().split(" ");
                int carLength = Integer.parseInt(line2[0]);
                String direction = line2[1];
                if (direction.equals("left")) {
                    leftQueue.add(carLength);
                } else {
                    rightQueue.add(carLength);
                }
            }
            boolean left = true;
            int count = 0;
            while (!leftQueue.isEmpty() || !rightQueue.isEmpty()) {
                int capacity = 0;
                if (left && !leftQueue.isEmpty()) {
                    capacity += leftQueue.peek();
                } else if (!left && !rightQueue.isEmpty()) {
                    capacity += rightQueue.peek();
                }
                while (capacity < ferryLength) {
                    if ( (left && leftQueue.isEmpty()) || (!left && rightQueue.isEmpty()) ) {
                        //System.out.println("break");
                        break;
                    } else if (left && !leftQueue.isEmpty()) {
                        //System.out.println("deleting left" + leftQueue.peek());
                        //System.out.println("count is " + count);
                        leftQueue.poll();
                        if (!leftQueue.isEmpty())  {
                            capacity += leftQueue.peek();
                        } else {
                            break;
                        }
                    } else if (!left && !rightQueue.isEmpty()) {
                        //System.out.println("deleting right" + rightQueue.peek());
                        //System.out.println("count is " + count);
                        rightQueue.poll();
                        if (!rightQueue.isEmpty()) {
                            capacity += rightQueue.peek();
                        } else {
                            break;
                        }
                    }
                }
                count++;
                left = !left;
            }
            writer.println(count);
        }
        writer.flush();
    }    
}
