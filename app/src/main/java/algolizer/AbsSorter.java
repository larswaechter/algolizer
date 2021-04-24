package algolizer;

import java.util.Arrays;
import java.util.HashMap;

public abstract class AbsSorter implements ISorter {
    final int[] arr;
    final int arrSum;

    HashMap<Integer, Float> weights = new HashMap<>();

    AbsSorter(int[] arr) {
        this.arr = arr;
        this.arrSum = Arrays.stream(arr).sum();
        for (int n : arr) this.weights.put(n, ((float) n / this.arrSum) * 100);
    }

    boolean isSorted() {
        for (int i = 0; i < arr.length - 1; i++)
            if (arr[i] > arr[i + 1])
                return false;
        return true;
    }
}
