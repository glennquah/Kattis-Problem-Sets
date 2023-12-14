import java.util.Comparator;
import java.util.PriorityQueue;

class AssigningWorkstations {

    static class Researcher {
        private final int startTime;
        private final int stayTime;

        Researcher(int startTime, int stayTime) {
            this.startTime = startTime;
            this.stayTime = stayTime;
        }

        int getEndTime() {
            return this.startTime + this.stayTime;
        }

        int getStartTime() {
            return this.startTime;
        }
    }
    public static void main(String[] args) {
        Kattio kat = new Kattio(System.in, System.out);
        int numOfResearchers = kat.getInt();
        int lockTime = kat.getInt();
        PriorityQueue<Researcher> orderedResearcher = new PriorityQueue<>(numOfResearchers, new Comparator<Researcher>() {
            @Override
            public int compare(Researcher r1, Researcher r2) {
                if (r1.startTime < r2.startTime) {
                    return -1;
                } else if (r1.startTime > r2.startTime) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        PriorityQueue<Integer> roomEndTiming = new PriorityQueue<Integer>();
        for (int i = 0; i < numOfResearchers; i++) {
            orderedResearcher.add(new Researcher(kat.getInt(), kat.getInt()));
        }
        int count = 0;
        for (int i = 0; i < numOfResearchers; i++) {
            Researcher nextResearcher = orderedResearcher.poll();
            int nextResearcherStartTime = nextResearcher.getStartTime();
            int nextResearcherEndTime = nextResearcher.getEndTime();

            //remove idle from orderedResearch
            while (roomEndTiming.size() != 0 && roomEndTiming.peek() + lockTime < nextResearcherStartTime) {
                roomEndTiming.poll();
            }
            //start 
            if (roomEndTiming.isEmpty()) {
                count++;
            //if need to open bcos it is filled
            } else if (nextResearcherStartTime < roomEndTiming.peek()) {
                count++;
            } else if (nextResearcherStartTime >= roomEndTiming.peek()) {
                roomEndTiming.poll();
            }

            roomEndTiming.add(nextResearcherEndTime);
        }
        System.out.println(numOfResearchers - count);
    }
}