import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class HumanCannonballRun {
    public static void main(String[] args) {
        Kattio kat = new Kattio(System.in, System.out);
        Double hereX = kat.getDouble();
        Double hereY = kat.getDouble();
        Coordinates me = new Coordinates(hereX, hereY, 0);
        Double destX = kat.getDouble();
        Double destY = kat.getDouble();

        
        List<Coordinates> listOfCannonCoordinates = new ArrayList<>();
        List<CannonEdges> listOfEdges = new ArrayList<>();

        int numOfCannons = kat.getInt();
        Coordinates dest = new Coordinates(destX, destY, numOfCannons + 1);
        listOfCannonCoordinates.add(me);
        for (int i = 1; i < numOfCannons + 1; i ++) {
            Double coordX = kat.getDouble();
            Double coordY = kat.getDouble();
            Coordinates c = new Coordinates(coordX, coordY, i);
            listOfCannonCoordinates.add(c);
        }
        listOfCannonCoordinates.add(dest);

        for (int i = 0; i < listOfCannonCoordinates.size(); i++) {
            for (int j = 0; j < listOfCannonCoordinates.size(); j++) {
                Coordinates a = listOfCannonCoordinates.get(i);
                Coordinates b = listOfCannonCoordinates.get(j);

                double distanceAtoB = Math.sqrt(Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2));
                double TimeAtoB = 0.0;
                if (i != j) {
                    if (a.cannonNum == 0 || a.cannonNum == listOfCannonCoordinates.size() - 1) {
                        TimeAtoB = distanceAtoB / 5.0;
                    } else {
                        if (distanceAtoB < 30.0) {
                            TimeAtoB = distanceAtoB / 5.0;
                        } else if (distanceAtoB > 50.0) {
                            TimeAtoB = 2 + ((distanceAtoB - 50.0) / 5.0);
                        } else {
                            TimeAtoB = 2 + ((50.0 - distanceAtoB) / 5.0);
                        }
                    }
                }
                listOfEdges.add(new CannonEdges(listOfCannonCoordinates.get(i), listOfCannonCoordinates.get(j), TimeAtoB));
            }
        }
        
        //mark this as infinity
        double INF = 100000000000.0;
        HashMap<Coordinates, Double> timeTaken = new HashMap<>();
        //mark starting as 0
        timeTaken.put(listOfCannonCoordinates.get(0), 0.0);
        for (int i = 1; i < listOfCannonCoordinates.size(); i ++) {
            timeTaken.put(listOfCannonCoordinates.get(i), INF);
        }
        // int count = 0;
        // Run Bellman-Ford algorithm
        for (int i = 0; i < listOfCannonCoordinates.size() - 1; i++) {
            for (int j = 0; j < listOfEdges.size(); j++) {
                Coordinates a = listOfEdges.get(j).coord1;
                Coordinates b = listOfEdges.get(j).coord2;
                double timeFromSourceToA = timeTaken.get(a);
                double timeFromAtoB = listOfEdges.get(j).duration;
                double timeFromSourceToB = timeTaken.get(b);
                if (timeFromSourceToA + timeFromAtoB < timeFromSourceToB) {
                    timeTaken.put(b, timeFromSourceToA + timeFromAtoB);
                    // count++;
                    // System.out.println("count =" + count);
                }
            }
        }

        
        double time = timeTaken.get(dest);
        kat.print(time);
        kat.flush();
    }

    static class Coordinates {
        private Double x;
        private Double y;
        private int cannonNum;

        public Coordinates() {
        }

        public Coordinates(Double x, Double y, int cannonNum) {
            this.x = x;
            this.y = y;
            this.cannonNum = cannonNum;
        }
    }

    static class CannonEdges {
        private Coordinates coord1;
        private Coordinates coord2;
        private double duration;

        public CannonEdges(Coordinates coord1, Coordinates coord2, double duration) {
            this.coord1 = coord1;
            this.coord2 = coord2;
            this.duration = duration;
        }
    }
}
