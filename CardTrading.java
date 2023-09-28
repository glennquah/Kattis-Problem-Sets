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

class CardTrading {

    static class Card {
        private final int cardType;
        private final int buyPrice;
        private final int sellPrice;

        Card(int cardType, int buyPrice, int sellPrice) {
            this.cardType = cardType;
            this.buyPrice = buyPrice;
            this.sellPrice = sellPrice;
        }

        public int getCardType() {
            return this.cardType;
        }

        public int getBuyPrice() {
            return this.buyPrice;
        }

        public int getSellPrice() {
            return this.sellPrice;
        }

        public int getTotal() {
            return this.buyPrice + this.sellPrice;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputOne = br.readLine().split(" ");
        int NumOfCards = Integer.parseInt(inputOne[0]);
        int NumOfCardTypes = Integer.parseInt(inputOne[1]);
        int MinimumCombosRequired = Integer.parseInt(inputOne[2]);

        int[] cards = new int[1000001];
        String[] inputTwo = br.readLine().split(" ");
        for (int i = 0; i < NumOfCards; i++) {
            cards[Integer.parseInt(inputTwo[i])]++;
        }

        List<Card> cardsPricingList = new ArrayList<>();
        for (int i = 1; i <= NumOfCardTypes; i++) {
            String[] inputPrice = br.readLine().split(" ");
            int buy = Integer.parseInt(inputPrice[0]) * (2 - cards[i]);
            int sell = Integer.parseInt(inputPrice[1]) * cards[i];
            cardsPricingList.add(new Card(i, buy, sell));
        }
        

        Collections.sort(cardsPricingList, new Comparator<Card>() {
            @Override
            public int compare(Card c1, Card c2) {
                //sort in ascending order in terms of total price bcos it calculates how much u NEED to buy and how much u are selling
                //so naturally the lowest in order will be what u should buy
                //e.g. If buying costs very little but selling also very little, hence it will be the first few (which u should buy)
                //If buying costs alot but selling is very little, you will also buy it since the profit u earn will be very little
                int p1 = c1.getTotal();
                int p2 = c2.getTotal();
                int p1Buy = c1.getBuyPrice();
                int p2Buy = c2.getBuyPrice();
                if (p1 - p2 > 0) {
                    return 1;
                } else if (p1 - p2 < 0) {
                    return -1;
                } else {
                    if (p1Buy - p2Buy> 0) {
                        return 1;
                    } else if (p1Buy - p2Buy < 0) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            }
        });

        long profit = 0;
        for (int i = 0; i < cardsPricingList.size(); i++) {
            if (i < MinimumCombosRequired) {
                //bcos buy price already calculates 2 - what u have
                profit -= cardsPricingList.get(i).getBuyPrice();
            } else {
                profit += cardsPricingList.get(i).getSellPrice();
            }
        }

        PrintWriter writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        writer.print(profit);
        writer.flush();
    }
}
