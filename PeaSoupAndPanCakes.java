import java.util.Scanner;

class PeaSoupAndPanCakes {
public static void main(String[] args) {
    
    Scanner sc = new Scanner(System.in);
    int numOfRestaurants = sc.nextInt();
    String firstRestaurantName = "";

    for (int i = 0; i < numOfRestaurants; i ++) {
        int numberOfDishes = sc.nextInt();
        sc.nextLine();
        String tempRestName = sc.nextLine();
        String[] restDishes = new String[numberOfDishes];
        
        for (int j = 0; j < numberOfDishes; j++) {
            restDishes[j] = sc.nextLine();
        }

        if (hasOrder(restDishes) && firstRestaurantName.length() == 0) {
            firstRestaurantName = tempRestName;
        }
    }

    if (firstRestaurantName.length() == 0) {
        System.out.println("Anywhere is fine I guess");
    } else {
        System.out.println(firstRestaurantName);
    }

}

    public static Boolean hasOrder(String[] dishes) {
        boolean hasSoup = false;
        boolean hasPancakes = false;
        for (int i = 0; i < dishes.length; i++) {
            if (dishes[i].equals("pea soup")) {
                hasSoup = true;
            } else if (dishes[i].equals("pancakes")) {
                hasPancakes = true;
            }

        }

        return hasSoup && hasPancakes;
    }
}
