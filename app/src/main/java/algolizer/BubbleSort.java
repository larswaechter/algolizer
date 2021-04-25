package algolizer;

class BubbleSort extends AbsSorter {
    private int lastSwitchIdx;
    private int sortedCount;

    BubbleSort(int[] numbers) {
        super(numbers);
    }

    @Override
    public void step() {
        for (int i = lastSwitchIdx; i < numbers.length - 1; i++) {
            if (numbers[i] > numbers[i + 1]) {
                int tmp = numbers[i];
                numbers[i] = numbers[i + 1];
                numbers[i + 1] = tmp;

                if (i == numbers.length - 2 - sortedCount) {
                    lastSwitchIdx = 0;
                    sortedCount++;
                } else lastSwitchIdx = i;

                return;
            }
        }
    }
}
