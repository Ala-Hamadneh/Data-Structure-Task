import java.util.*;

public class FindMissingNumber {

    public static int findMissingNumber(int[] array, int n) {
        int expectedSum = n * (n + 1) / 2;
        int actualSum = 0;
        for (int num : array) {
            actualSum += num;
        }
        return expectedSum - actualSum;
    }



    public static void main(String[] args) {
//    Creating an array from 1 to 100
        int[] array ;
        array=new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] =i+1;
        }
//    replacing number 9 with 0 , total sum 5041
        array[8]=0;
//     creating a list to use the shuffling , to randomize the sort of the array
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
             list.add(array[i]);
        }

        Collections.shuffle(list, new Random());
//     copy the unsorted list to the array
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
//     testing the method findMissingNumber
        System.out.println("The missing number in the array is :" +findMissingNumber(array,array.length));



    }
}

