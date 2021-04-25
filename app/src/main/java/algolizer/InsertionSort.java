package algolizer;

class InsertionSort extends AbsSorter {
    private int lastSwitchIdx;

    InsertionSort(int[] numbers) {
        super(numbers);
    }

    @Override
    public void step() {
        int i = lastSwitchIdx - 1;
        int val = numbers[lastSwitchIdx++];

        // Move smaller values to the right
        while (i >= 0 && val < numbers[i])
            numbers[i + 1] = numbers[i--];

        numbers[i + 1] = val;
    }
}
