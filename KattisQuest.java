import java.util.Collections;
import java.util.PriorityQueue;
import java.util.TreeMap;

class KattisQuest {
    public static void main(String[] args) {
        Kattio kat = new Kattio(System.in, System.out);
        int numOfQueries = kat.getInt();
        TreeMap<Integer, PriorityQueue<Long>> listOfQuest = new TreeMap<>();
        for (int i = 0; i < numOfQueries; i++) {
            if(kat.getWord().equals("add")) {
                int energy = kat.getInt();
                Long gold = kat.getLong();
                if (listOfQuest.get(energy) == null) {
                    PriorityQueue<Long> listOfSameEnergy = new PriorityQueue<>(Collections.reverseOrder());
                    listOfSameEnergy.add(gold);
                    listOfQuest.put(energy, listOfSameEnergy);
                } else {
                    PriorityQueue<Long> newListOfSameEnergy = listOfQuest.get(energy);
                    newListOfSameEnergy.add(gold);
                    listOfQuest.put(energy, newListOfSameEnergy);
                }
            } else {
                int energyConsumption = kat.getInt();
                //System.out.println("energy consumption is" + energyConsumption);
                Long goldAchieved = (long) 0;
                while (energyConsumption > 0) {
                    Integer tempKey = listOfQuest.floorKey(energyConsumption);
                    if (tempKey == null) {
                        //System.out.println("Break");
                        break;
                    }
                    PriorityQueue<Long> sameGoldQuest = listOfQuest.get(tempKey);
                    goldAchieved += sameGoldQuest.poll();
                    energyConsumption -= tempKey;
                    if (sameGoldQuest.size() == 0) {
                        listOfQuest.remove(tempKey);
                    } else {
                        listOfQuest.put(tempKey, sameGoldQuest);
                    }
                }
                kat.println(goldAchieved);
            }
        }
        kat.flush();
    }
}