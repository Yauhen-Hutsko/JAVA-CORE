import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

class Main {
    static final int DATA_SIZE = 20;

    public static void main(String[] args) {
//        generateRandomIntList();
//        generateRandomCharList();
        int[] one = generateRandomIntArray();
        Arrays.sort(one, 0, DATA_SIZE);
        ArrayList<Integer> numsList = Arrays.stream(one)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println("numList is:" + numsList);
        System.out.println("output is:" + removeDuplicates(numsList));

    }

    private static List<Integer> removeDuplicates(List<Integer> input) {
        List<Integer> output;
        output = input;
        int pointer1;
        int pointer2;
        int k = input.size();
        for (pointer1 = 0; pointer1 < output.size(); pointer1++) {
            pointer2 = pointer1 + 1;
            for (; pointer2 < output.size(); pointer2++) {
                if (output.get(pointer2) == 999) {
                    break;
                }
                if (output.get(pointer2).equals(output.get(pointer1))) {
                    output.set(pointer2, 999);
                    k--;
                }


            }
        }
        for (int i = 0; i < output.size(); i++) {
            if (output.get(i).equals(999)) {
                int temp = output.remove(i);
                output.add(temp);
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
