import java.util.HashMap;

public class TestHash {

	public static void main(String[] args) {
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		// placing items into the hashmap
		int a = 46;
		hm.put(3, 52);
		hm.put(2, 13);
		hm.put(-1, a);
		// retrieving item from the hashmap
		System.out.println("Janet => " + hm.get(-1));
	}
}
