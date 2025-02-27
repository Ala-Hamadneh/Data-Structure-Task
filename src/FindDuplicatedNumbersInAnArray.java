import java.util.*;
public class FindDuplicatedNumbersInAnArray {
    public static void findDuplicates(int[] array) {
        Map<Integer, Integer> hashMap = new HashMap<>();

        for (int num : array) {
            if (hashMap.containsKey(num)) {
                hashMap.put(num, hashMap.get(num) + 1);
            } else {
                hashMap.put(num, 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.println("Number " + entry.getKey() + " is duplicated " + entry.getValue() + " times.");
            }
        }
    }

    public static void main(String[] args) {
        // Example array with duplicates
        int[] array = {1, 2, 3, 4, 2, 5, 6, 3, 7, 8, 3, 9, 2};
        findDuplicates(array);
    }
}
