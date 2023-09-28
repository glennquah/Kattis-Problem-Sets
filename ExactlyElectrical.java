import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class ExactlyElectrical {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String startCoordinate[] = br.readLine().split(" ");
        int startX = Integer.parseInt(startCoordinate[0]);
        int startY = Integer.parseInt(startCoordinate[1]);
        String endCoordinate[] = br.readLine().split(" ");
        int endX = Integer.parseInt(endCoordinate[0]);
        int endY = Integer.parseInt(endCoordinate[1]);

        int electricalUnitNeeded = Integer.parseInt(br.readLine());
        int sum = Math.abs(endX - startX)  + Math.abs(endY - startY);
        if (sum > electricalUnitNeeded) {
            System.out.print("N");
        } else if (electricalUnitNeeded % 2 == sum % 2) {
            System.out.print("Y");
        } else {
            System.out.println("N");
        }
    }    
}
