import java.util.Scanner;

class apaxiaaans {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String name = sc.next();
        char tempChar = name.charAt(0);
        String newName = String.valueOf(tempChar);
        for(int i = 1; i < name.length(); i++) {
            if(tempChar != name.charAt(i)) {
                newName = newName + name.charAt(i);
                tempChar = name.charAt(i);
            }
        }
        System.out.print(newName);
    }
}
