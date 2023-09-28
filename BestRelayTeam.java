import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class BestRelayTeam {

    static class Runner {
        private final String name;
        private final double firstLapTiming;
        private final double flyStartTiming;

        Runner(String name, double firstLapTiming, double flyStartTiming) {
            this.name = name;
            this.firstLapTiming = firstLapTiming;
            this.flyStartTiming = flyStartTiming;
        }
        
        public String getName() {
            return this.name;
        }

        public double getFirstLapTiming() {
            return this.firstLapTiming;
        }

        public double getFlyLapTiming() {
            return this.flyStartTiming;
        }
    }

    static class RunnerFirstLapComparator implements Comparator<Runner> {
        @Override
        public int compare(Runner r1, Runner r2) {
            return Double.compare(r1.firstLapTiming, r2.firstLapTiming);
        }
    }

    static class RunnerFlyLapComparator implements Comparator<Runner> {
        public int compare(Runner r1, Runner r2) {
            return Double.compare(r1.flyStartTiming, r2.flyStartTiming);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numOfRunners = sc.nextInt();
        List<Runner> listOfRunners = new ArrayList<>();
        sc.nextLine();

        //add runners detail into a list
        for(int i = 0; i < numOfRunners; i++) {
            listOfRunners.add(new Runner(sc.next(), sc.nextDouble(), sc.nextDouble()));
        }

        sc.close();
        List<Runner> topFirstLapRunners = new ArrayList<>(listOfRunners);
        Collections.sort(topFirstLapRunners, new RunnerFirstLapComparator());
        List<Runner> topFlyRunners = new ArrayList<>(listOfRunners);
        Collections.sort(topFlyRunners, new RunnerFlyLapComparator());

        double bestTiming = Double.MAX_VALUE;
        String[] bestRelayTeam = new String[4];
        String [] tempRelayTeam = new String[4];

        for (int i = 0; i < numOfRunners; i++) {
            double tempTiming = topFirstLapRunners.get(i).getFirstLapTiming();
            tempRelayTeam[0] = topFirstLapRunners.get(i).getName();
            int count = 1;
            
            for (int j = 0; j < 4; j++) {
                if (!topFirstLapRunners.get(i).getName().equals(topFlyRunners.get(j).getName())) {
                    tempTiming += topFlyRunners.get(j).getFlyLapTiming();
                    tempRelayTeam[count] = topFlyRunners.get(j).getName();
                    count++;
                }
                if (count == 4) {
                    break; 
                }
            }
            if (tempTiming < bestTiming) {
                bestTiming = tempTiming;
                bestRelayTeam[0] = tempRelayTeam[0];
                bestRelayTeam[1] = tempRelayTeam[1];
                bestRelayTeam[2] = tempRelayTeam[2];
                bestRelayTeam[3] = tempRelayTeam[3];
            }
        }

        System.out.println(bestTiming);
        for(int i = 0; i < bestRelayTeam.length; i++) {
            System.out.println(bestRelayTeam[i]);
        }
    }
}
