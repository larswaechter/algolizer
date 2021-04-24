package algolizer;

class BubbleSort extends AbsSorter {
    int lastSwitchIdx = 0;
    int sortedCount = 0;

    BubbleSort(int[] arr) {
        super(arr);
    }

    @Override
    public void step() {
        for (int i = lastSwitchIdx; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                int tmp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = tmp;

                if(i == arr.length - 2 - sortedCount) {
                    lastSwitchIdx = 0;
                    sortedCount++;
                } else lastSwitchIdx = i;

                return;
            }
        }
    }
}
