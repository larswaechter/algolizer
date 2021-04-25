package algolizer;

class SelectionSort extends AbsSorter {
    private int lastSwitchIdx;

    SelectionSort(int[] numbers) {
        super(numbers);
    }

    @Override
    public void step() {
        int min = lastSwitchIdx;

        // Find minimum
        for (int i = lastSwitchIdx + 1; i < numbers.length; i++)
            if (numbers[i] < numbers[min]) min = i;

        // Switch minimum
        int tmp = numbers[lastSwitchIdx];
        numbers[lastSwitchIdx++] = numbers[min];
        numbers[min] = tmp;
    }
}
