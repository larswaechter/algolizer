package algolizer;

import java.util.Arrays;
import java.util.HashMap;

public abstract class AbsSorter implements ISorter {
    final int[] numbers;

    HashMap<Integer, Float> weights = new HashMap<>();

    AbsSorter(int[] numbers) {
        this.numbers = numbers;
        int sum = Arrays.stream(numbers).sum();
        for (int n : numbers) this.weights.put(n, ((float) n / sum) * 100);
    }

    boolean isSorted() {
        for (int i = 0; i < numbers.length - 1; i++)
            if (numbers[i] > numbers[i + 1])
                return false;
        return true;
    }
}
