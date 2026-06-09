import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

class Main {
    static final int DATA_SIZE = 20;
    static int MAGIC_NUMBER = -1;

    static void main(String[] args) {
//        List<Integer> example = List.of(1,2,2,2,3); // static list for quick test.
//        generateRandomIntList();
//        generateRandomCharList();
        int[] one = generateRandomIntArray(); // randomized array
        Arrays.sort(one, 0, DATA_SIZE); // convert array to lis of Integers
        ArrayList<Integer> numsList = Arrays.stream(one)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println("numList is:" + numsList);
        List<Integer> listForTraverse = new ArrayList<>(numsList);
        List<Integer> listForMovingWindow = new ArrayList<>(numsList);
        System.out.println("output is:" + removeDuplicatesFullTraverse(listForTraverse));
        System.out.println("output is:" + removeDuplicatesWithMovingWindow(listForMovingWindow));
    }

    private static List<Integer> removeDuplicatesFullTraverse(List<Integer> input) {
        List<Integer> output;
        output = input;
        int pointer1;
        int pointer2;
        int k = output.size();
        for (pointer1 = 0; pointer1 < output.size(); pointer1++) {
            pointer2 = pointer1 + 1;
            for (; pointer2 < output.size(); pointer2++) {
                if (output.get(pointer2) == MAGIC_NUMBER) {
                    break;
                }
                if (output.get(pointer2).equals(output.get(pointer1))) {
                    output.set(pointer2, MAGIC_NUMBER);
                    k--;
                }


            }
        }
        for (int i = 0; i < k; i++) {
            if (output.get(i).equals(MAGIC_NUMBER)) {
                int temp = output.remove(i);
                output.add(temp);
                i--;
            }

        }
        System.out.println("K is: " + k);
        return output;
    }
    private static List<Integer> removeDuplicatesWithMovingWindow(List<Integer> input) {
        List<Integer> output;
        output = new LinkedList<>(input);
        int pointer1 = 0;
        int pointer2;
        int k = output.size();
        for (; pointer1 < output.size(); pointer1++) {
            pointer2 = pointer1 + 1;
            for (;pointer2 < output.size() && output.get(pointer2).equals(output.get(pointer1)); pointer2++) {
                if (output.get(pointer2) == MAGIC_NUMBER) {
                    break;
                }
                output.set(pointer2, MAGIC_NUMBER);
                k--;
            }
            pointer1 = pointer2-1;
        }
        //  move empty cells to the end of listRR
        for (int i = 0; i < k; i++) {
            if (output.get(i).equals(MAGIC_NUMBER)) {
                int temp = output.remove(i);
                output.add(temp);
                i--;
            }

        }
        System.out.println("K is: " + k);
        return output;
    }

    private static char[] generateRandomCharArray() {
        final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
        ThreadLocalRandom random = ThreadLocalRandom.current();
        char[] randomChars = new char[DATA_SIZE];
        for (int i = 0; i < randomChars.length; i++) {
            randomChars[i] = ALPHABET.charAt(random.nextInt(ALPHABET.length()));
        }
        System.out.println("randomized chars: " + randomChars);
        return randomChars;
    }

    private static int[] generateRandomIntArray() {
        int[] randomNums = new int[DATA_SIZE];
        for (int i = 0; i < randomNums.length; i++) {
            randomNums[i] = ThreadLocalRandom.current().nextInt(0, 30);
        }
        return randomNums;
    }
}
