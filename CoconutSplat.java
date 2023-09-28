import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class CoconutSplat {
    static abstract class Player {
        private final int playerNumber;

        Player (int playerNumber) {
            this.playerNumber = playerNumber;
        }

        public int getPlayerNumber() {
            return this.playerNumber;
        }

        abstract  Player getNextState();
    } 

    static class PlayerFoldedHands extends Player {
        PlayerFoldedHands(int playerNumber) {
            super(playerNumber);
        }

        @Override
        public Player getNextState() {
            //System.out.println("Player" + this.getPlayerNumber() + " folded hands got splat");
            return new PlayerFist(this.getPlayerNumber());
        }
    }

    static class PlayerFist extends Player {
        PlayerFist(int playerNumber) {
            super(playerNumber);
        }

        @Override
        public Player getNextState() {
            //System.out.println("Player" + this.getPlayerNumber() + " fisted becomes flat");
            return new PlayerHandFlat(this.getPlayerNumber());
        }
    }

    static class PlayerHandFlat extends Player {
        PlayerHandFlat(int playerNumber) {
            super(playerNumber);
        }

        @Override
        public Player getNextState() {
            //System.out.println("Player" + this.getPlayerNumber() + " HAND is removed");
            return new PlayerBehindBack(this.getPlayerNumber());
        }
    }

    static class PlayerBehindBack extends Player {
        PlayerBehindBack(int playerNumber) {
            super(playerNumber);
        }
        
        @Override
        public Player getNextState() {
            //System.out.println("Player" + this.getPlayerNumber() + " hands behind back got splat, he is removed");
            return new PlayerBehindBack(0);
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] firstLine = br.readLine().split(" ");
        int numOfSyllabus = Integer.parseInt(firstLine[0]);
        int numOfPlayers = Integer.parseInt(firstLine[1]);

        ArrayList<Player> listOfPlayers = new ArrayList<>();
        for (int i = 0; i < numOfPlayers; i ++) {
            listOfPlayers.add(new PlayerFoldedHands(i + 1));
        }
        int lastLeftOf = 0;
        while (listOfPlayers.size() > 1) {
            int splatPlayerNum = (numOfSyllabus  + lastLeftOf) % (listOfPlayers.size());
            if (splatPlayerNum == 0) {
                splatPlayerNum = listOfPlayers.size();
            }
            //System.out.println("player " + splatPlayerNum + " called splat");
            if (listOfPlayers.get(splatPlayerNum - 1) instanceof PlayerFoldedHands) {
                listOfPlayers.set(splatPlayerNum - 1, listOfPlayers.get(splatPlayerNum - 1).getNextState());
                listOfPlayers.add(splatPlayerNum - 1, listOfPlayers.get(splatPlayerNum - 1));
                lastLeftOf = splatPlayerNum - 1;
            } else {
                listOfPlayers.set(splatPlayerNum - 1, listOfPlayers.get(splatPlayerNum - 1).getNextState());
                lastLeftOf = splatPlayerNum;
            }
            if (listOfPlayers.get(splatPlayerNum - 1) instanceof PlayerBehindBack) {
            //if statement if player behind back == 0, remove player
                listOfPlayers.remove(splatPlayerNum - 1);
                lastLeftOf = splatPlayerNum - 1;
            }
        }
        //return last player number
        System.out.println(listOfPlayers.get(0).playerNumber);
    }    
}
